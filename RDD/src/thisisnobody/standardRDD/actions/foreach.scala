package thisisnobody.standardRDD.actions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * foreach(f: T => Unit): Unit
  * 对RDD每个元素执行一次函数
  */
object foreach {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("foreach")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 2, 3, 4, 5))
    val hello = listRDD.foreach(println) //返回Unit空值
    println(hello)

  }

}
