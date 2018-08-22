package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * combineByKey[C](
  * createCombiner: V => C,
  * mergeValue: (C, V) => C,
  * mergeCombiners: (C, C) => C): RDD[(K, C)]
  *
  */
object combineByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("combineByKey")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 8)))
    val result = pairRDD.combineByKey(v => (v, 1), (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1), (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2))

    println(result.count())
    result.foreach(println)

    val result1 = result.map{ case( key, value) => (key, value._1 / value._2.toFloat)}
    println(result1.count())
    result1.foreach(println)
    result1.collectAsMap().map(println)
    result1.collectAsMap().map(println(_))

  }


}
