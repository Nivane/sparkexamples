package thisisnobody.standardRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * aggregate[U: ClassTag](zeroValue: U)(seqOp: (U, T) => U, combOp: (U, U) => U): U
  * 返回不同类型的RDD
  */
object aggregate {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("aggregate")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 2, 3, 4, 5))
    val zeroValue = (1, 1)
    val pairRDD = listRDD.aggregate(zeroValue)((acc, value) => (acc._1 + value, acc._2 + 1), (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    println(pairRDD._1)
    println(pairRDD._2)

    val avg = pairRDD._1 / pairRDD._2.toDouble
    println(avg)
  }

}
