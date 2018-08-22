package thisisnobody.standardRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * union(other: RDD[T]): RDD[T]
  * 求并集,不去重
  */
object union {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("union").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("hello", "kitty", "bye"))
    val rdd2 = sc.parallelize(List("hello", "neko", "bye"))

    val unionRDD = rdd1.union(rdd2)
    println(unionRDD.count()) //6
    unionRDD.foreach(println)
    unionRDD.top(10).foreach(println)
    unionRDD.collect().foreach(x => println(x))

  }

}
