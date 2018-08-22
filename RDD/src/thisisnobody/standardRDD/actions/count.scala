package thisisnobody.standardRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * count()
  * 返回RDD元素个数
  * countByValue()
  * 返回Map类型各个元素出现次数，Map(elem -> count)
  *
  */
object count {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 0, 0, 2, 1, 2, 1, 1, 1, 2, 3, 3, 4, 5, 7))

    println(listRDD.count()) //15
    println(listRDD.countByValue()) //Map(0 -> 2, 5 -> 1, 1 -> 5, 2 -> 3, 7 -> 1, 3 -> 2, 4 -> 1)

  }
}
