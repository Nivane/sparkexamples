package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * mapValues[U](f: V => U): RDD[(K, U)]
  * 将函数应用到键值对中的值，返回新的键值对
  */
object mapValues {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("mapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1,2),(2,3),(3,4),(4,5),(1,6)))

    val mapValuesRDD = pairRDD.mapValues( x => x * x)

    println(mapValuesRDD.count())
    println(mapValuesRDD.countByValue())
    println(mapValuesRDD.groupByKey().count())
    println(mapValuesRDD.groupByKey().countByValue())
    mapValuesRDD.foreach(println)

    /**
      * (1,4)
      * (2,9)
      * (3,16)
      * (4,25)
      * (1,36)
      */
  }
}
