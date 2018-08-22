package thisisnobody.pairRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

object lookup {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("countByKey")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))

    val collectResult = pairRDD.lookup(1)

    println(collectResult) //WrappedArray(2, 3)


  }
}
