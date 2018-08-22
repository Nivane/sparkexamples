package thisisnobody.standardRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * distinct(): RDD[T]
  * 去重功能，不需参数
  */
object distinct {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("distinct").setMaster("local")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 0, 0, 2, 1, 2, 1, 1, 1, 2, 3, 3, 4, 5, 7))
    //filter()中函数必须返回Boolean以过滤信息
    val filterRDD = listRDD.filter(x => (x % 2 == 0))
    filterRDD.foreach(x => println(x))

    //去重
    val distinctRDD = filterRDD.distinct()
    distinctRDD.foreach(x => println(x))

  }
}
