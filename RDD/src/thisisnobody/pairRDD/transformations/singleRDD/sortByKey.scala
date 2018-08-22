package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

object sortByKey {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("sortByKey")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (2, 3), (3, 4), (4, 5), (1, 6)))
    val sortByKeyRDD = pairRDD.sortByKey()

    println(sortByKeyRDD.countByValue())
    println(sortByKeyRDD.count())
    sortByKeyRDD.foreach(println)

  }
}
