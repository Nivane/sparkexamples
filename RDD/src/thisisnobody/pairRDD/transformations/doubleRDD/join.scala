package thisisnobody.pairRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * join[W](other: RDD[(K, W)]): RDD[(K, (V, W))]
  * 内连接，两个pairRDD都存在的键
  */
object join {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("join")
    val sc = new SparkContext(conf)

    val pairRDD1 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD2 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD3 = sc.parallelize(List((1, 4), (1, 5), (2, 3), (2, 5), (3, 7)))
    val pairRDD4 = sc.parallelize(List((1, 4), (11, 5), (12, 3), (12, 5), (13, 7)))

    val result1 = pairRDD1.join(pairRDD2)
    result1.foreach(println)
    /**
      * (1,(2,2))
      * (1,(2,3))
      * (1,(3,2))
      * (1,(3,3))
      * (3,(4,4))
      * (2,(2,2))
      * (2,(2,4))
      * (2,(4,2))
      * (2,(4,4))
      */


    val result2 = pairRDD2.join(pairRDD3)
    result2.foreach(println)

    /**
      * (1,(2,4))
      * (1,(2,5))
      * (1,(3,4))
      * (1,(3,5))
      * (3,(4,7))
      * (2,(2,3))
      * (2,(2,5))
      * (2,(4,3))
      * (2,(4,5))
      */


    val result3 = pairRDD3.join(pairRDD4)
    result3.foreach(println)

    /**
      * (1,(4,4))
      * (1,(5,4))
      */
  }
}
