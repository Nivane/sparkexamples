package thisisnobody.pairRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * leftOuterJoin[W](other: RDD[(K, W)]): RDD[(K, (V, Option[W]))]
  * 左边的RDD保留所有键匹配右边，没有则为None
  *
  */
object leftOuterJoin {
    def main(args: Array[String]): Unit = {

      val conf = new SparkConf().setMaster("local").setAppName("leftOuterJoin")
      val sc = new SparkContext(conf)

      val pairRDD1 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
      val pairRDD2 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
      val pairRDD3 = sc.parallelize(List((1, 4), (1, 5), (2, 3), (2, 5), (3, 7)))
      val pairRDD4 = sc.parallelize(List((1, 4), (11, 5), (12, 3), (12, 5), (13, 7)))

      val result1 = pairRDD1.leftOuterJoin(pairRDD2)
      result1.foreach(println)
      /**
        * (1,(2,Some(2)))
        * (1,(2,Some(3)))
        * (1,(3,Some(2)))
        * (1,(3,Some(3)))
        * (3,(4,Some(4)))
        * (2,(2,Some(2)))
        * (2,(2,Some(4)))
        * (2,(4,Some(2)))
        * (2,(4,Some(4)))
        */


      val result2 = pairRDD2.leftOuterJoin(pairRDD3)
      result2.foreach(println)
      /**
        * (1,(2,Some(4)))
        * (1,(2,Some(5)))
        * (1,(3,Some(4)))
        * (1,(3,Some(5)))
        * (3,(4,Some(7)))
        * (2,(2,Some(3)))
        * (2,(2,Some(5)))
        * (2,(4,Some(3)))
        * (2,(4,Some(5)))
        */


      val result3 = pairRDD3.leftOuterJoin(pairRDD4)
      result3.foreach(println)

      /**
        * (1,(4,Some(4)))
        * (1,(5,Some(4)))
        * (3,(7,None))
        * (2,(3,None))
        * (2,(5,None))
        */

    }

}
