package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}
/**
  * 不去重
 */
object keys {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("key").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (2, 3), (3, 4), (4, 5), (1, 6)))
    val keysRDD = pairRDD.keys

    println(pairRDD.countByValue())
    println(keysRDD.count())
    println(keysRDD.countByValue())
    keysRDD.foreach(println)


  }
}
