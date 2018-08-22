package thisisnobody.pairRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * rightOuterJoin[W](other: RDD[(K, W)]): RDD[(K, (Option[V], W))]
  * 右边的RDD保留所有键匹配左边，没有则为None
  *
  */
object rightOuterJoin {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("rightOuterJoin")
    val sc = new SparkContext(conf)

    val pairRDD1 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD2 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD3 = sc.parallelize(List((1, 4), (1, 5), (2, 3), (2, 5), (3, 7)))
    val pairRDD4 = sc.parallelize(List((1, 4), (11, 5), (12, 3), (12, 5), (13, 7)))

    val result1 = pairRDD1.rightOuterJoin(pairRDD2)
    result1.foreach(println)
    /**
      * (1,(Some(2),2))
      * (1,(Some(2),3))
      * (1,(Some(3),2))
      * (1,(Some(3),3))
      * (3,(Some(4),4))
      * (2,(Some(2),2))
      * (2,(Some(2),4))
      * (2,(Some(4),2))
      * (2,(Some(4),4))
      */


    val result2 = pairRDD2.rightOuterJoin(pairRDD3)
    result2.foreach(println)
    /**
      * (1,(Some(2),4))
      * (1,(Some(2),5))
      * (1,(Some(3),4))
      * (1,(Some(3),5))
      * (3,(Some(4),7))
      * (2,(Some(2),3))
      * (2,(Some(2),5))
      * (2,(Some(4),3))
      * (2,(Some(4),5))
      */


    val result3 = pairRDD3.rightOuterJoin(pairRDD4)
    result3.foreach(println)

    /**
      * (13,(None,7))
      * (11,(None,5))
      * (1,(Some(4),4))
      * (1,(Some(5),4))
      * (12,(None,3))
      * (12,(None,5))
      */

  }

}
