package thisisnobody.basicRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * intersection(other: RDD[T]): RDD[T]
  * 求交集，去重
  */
object intersection {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("intersection")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("hello", "kitty", "bye"))
    val rdd2 = sc.parallelize(List("hello", "neko", "bye"))

    val interRDD = rdd1.intersection(rdd2)

    println(interRDD.count()) //2
    interRDD.foreach(println)

  }
}
