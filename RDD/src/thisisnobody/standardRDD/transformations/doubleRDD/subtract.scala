package thisisnobody.standardRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * subtract(other: RDD[T]): RDD[T]
  * 求差集
  * a.subtract(b) 在a中不在b中的元素
  * b.subtract(a) 在b中不在a中的元素
  */
object subtract {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("subtract")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("hello", "kitty", "bye"))
    val rdd2 = sc.parallelize(List("hello", "neko", "bye"))

    val subRDD1 = rdd1.subtract(rdd2)
    subRDD1.foreach(println)

    val subRDD2 = rdd2.subtract(rdd1)
    subRDD2.foreach(println)
  }


}
