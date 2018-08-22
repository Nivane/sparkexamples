package thisisnobody.pairRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

object countByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("countByKey")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))

    val countByKeyResult = pairRDD.countByKey()

    println(countByKeyResult) //Map(1 -> 2, 3 -> 1, 2 -> 2)

  }

}
