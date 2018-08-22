package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object flatMapValues {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("flatMapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (2, 3), (3, 4), (4, 5), (5, 6)))

    val flatMapValuesRDD = pairRDD.flatMapValues(x => (x to 4))

    println(flatMapValuesRDD.countByValue())
    //Map((3,4) -> 1, (1,4) -> 1, (1,3) -> 1, (2,4) -> 1, (2,3) -> 1, (1,2) -> 1)

    flatMapValuesRDD.foreach(println)

    /**
      * (1,2)
      * (1,3)
      * (1,4)
      * (2,3)
      * (2,4)
      * (3,4)
      */
  }
}
