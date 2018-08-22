package thisisnobody.basicRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * cartesian[U: ClassTag](other: RDD[U]): RDD[(T, U)]
  * 两个RDD所有元素的笛卡儿积
  */
object cartesian {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("cartesian")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("hello", "kitty", "bye"))
    val rdd2 = sc.parallelize(List(1, 2, 3))

    val cartesian = rdd1.cartesian(rdd2)
    println(cartesian.count()) //9
    cartesian.foreach(println)

    /**
      *
      * (hello,1)
      * (hello,2)
      * (hello,3)
      * (kitty,1)
      * (kitty,2)
      * (kitty,3)
      * (bye,1)
      * (bye,2)
      * (bye,3)
      *
      */
  }
}
