package thisisnobody.basicRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * reduce(f: (T, T) => T): T
  * 并行整合RDD中所有数据
  *
  */
object reduce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 2, 3, 4, 5))
    val sumRDD = listRDD.reduce((x, y) => x + y)
    val multiplyRDD = listRDD.reduce(f: (Int, Int) => Int)
    println(sumRDD)
    println(multiplyRDD)

  }

  def f(a: Int, b: Int): Int = {

    return a * b

  }
}
