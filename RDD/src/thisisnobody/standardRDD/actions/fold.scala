package thisisnobody.standardRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * fold(zeroValue: T)(op: (T, T) => T): T
  * 提供初始值，并行整合RDD所有数据
  * zeroValue：
  * the initial value for the accumulated result of each partition for the op operator,
  * and also the initial value for the combine results from different partitions for
  * the op operator - this will typically be the neutral element (e.g. Nil for list concatenation or 0 for summation)
  * 两个初始值
  * 每个分区操作的并行整合结果的初始值
  * 不同分区合并结果的初始值
  */
object fold {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("fold")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 2, 3, 4, 5))
    val zeroValue = 1000

    println(listRDD.getNumPartitions)

    val sumRDD = listRDD.fold(zeroValue)(sum: (Int, Int) => Int)
    println(sumRDD) //2015 每个分区自己合并初始值 + 不同分区合并初始值

  }

  def sum(a: Int, b: Int): Int = {

    return a + b
  }

}
