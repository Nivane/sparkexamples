package thisisnobody.pairRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

object collectAsMap {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("countByKey")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))

    val collectResult = pairRDD.collectAsMap() //Map(2 -> 4, 1 -> 3, 3 -> 4) map一个键只能有一个值，所以后者覆盖前者

    println(collectResult)


  }
}
