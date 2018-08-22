package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  * groupByKey(): RDD[(K, Iterable[V])]
  */
object groupByKey {
  def main(args: Array[String]): Unit = {

    def tuple(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = {
      return (t1._1 * t2._1, t1._2 + t2._2)
    }

    val conf = new SparkConf().setAppName("groupByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (1, 2), (1, 4), (1, 5), (2, 2), (2, 3), (2, 4)))
    val groupByKeyRDD = pairRDD.groupByKey()
    val countByValueResult = pairRDD.countByValue()
    //计算所有键的积和所有值的和生成一个键值对结果,可以使用方法也可以使用匿名函数
    //val reduceResult = pairRDD.reduce((tuple1, tuple2) => (tuple1._1 * tuple2._1, tuple1._2 + tuple2._2))
    val reduceResult = pairRDD.reduce(tuple)
    println(groupByKeyRDD.count())
    groupByKeyRDD.foreach(println)
    println(countByValueResult)
    println(reduceResult)

    /**
      * 2
      * (1,CompactBuffer(2, 2, 4, 5))
      * (2,CompactBuffer(2, 3, 4))
      *
      * Map((1,5) -> 1, (1,4) -> 1, (2,2) -> 1, (2,4) -> 1, (2,3) -> 1, (1,2) -> 2)
      *
      * (8,22)
      */

  }
}
