package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

object values {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("key").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (2, 3), (3, 4), (4, 5), (1, 6)))
    val valuesRDD = pairRDD.values

    println(pairRDD.countByValue())
    println(valuesRDD.count())
    println(valuesRDD.countByValue())
    valuesRDD.foreach(println)

  }
}
