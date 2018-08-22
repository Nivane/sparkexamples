package thisisnobody.pairRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * cogroup[W](other: RDD[(K, W)]): RDD[(K, (Iterable[V], Iterable[W]))]
  * 相同键分组，包含两个RDD中所有的键
  */
object cogroup {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("cogroup")
    val sc = new SparkContext(conf)

    val pairRDD1 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD2 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD3 = sc.parallelize(List((1, 4), (1, 5), (2, 3), (2, 5), (3, 7)))
    val pairRDD4 = sc.parallelize(List((1, 4), (11, 5), (12, 3), (12, 5), (13, 7)))

    val result1 = pairRDD1.cogroup(pairRDD2)
    result1.foreach(println)
    /**
      * (1,(CompactBuffer(2, 3),CompactBuffer(2, 3)))
      * (3,(CompactBuffer(4),CompactBuffer(4)))
      * (2,(CompactBuffer(2, 4),CompactBuffer(2, 4)))
      */


    val result2 = pairRDD2.cogroup(pairRDD3)
    result2.foreach(println)
    /**
      * (1,(CompactBuffer(2, 3),CompactBuffer(4, 5)))
      * (3,(CompactBuffer(4),CompactBuffer(7)))
      * (2,(CompactBuffer(2, 4),CompactBuffer(3, 5)))
      */


    val result3 = pairRDD3.cogroup(pairRDD4)
    result3.foreach(println)

    /**
      * (13,(CompactBuffer(),CompactBuffer(7)))
      * (11,(CompactBuffer(),CompactBuffer(5)))
      * (1,(CompactBuffer(4, 5),CompactBuffer(4)))
      * (3,(CompactBuffer(7),CompactBuffer()))
      * (12,(CompactBuffer(),CompactBuffer(3, 5)))
      * (2,(CompactBuffer(3, 5),CompactBuffer()))
      */

  }
}
