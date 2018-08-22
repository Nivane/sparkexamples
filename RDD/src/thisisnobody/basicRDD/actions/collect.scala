package thisisnobody.basicRDD.actions


import org.apache.spark.{SparkConf, SparkContext}

/**
  * collect()
  * 把整个RDD的内容返回驱动器程序,要求所有数据都必须能放入单台机器的内存中
  * take(num)
  * 返回RDD的num个元素，尝试访问尽量少的分区，所以数据会不均衡
  * takeOrdered(num)(ordering)
  * 按照提供的顺序返回前面的num个元素
  * top(num)
  * 使用数据默认顺序，返回最前面的num个元素
  *
  */
object collect {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1, 0, 0, 2, 1, 2, 1, 1, 1, 2, 3, 3, 4, 5, 7))

    //将RDD所有数据返回给驱动器程序内存中
    val result = listRDD.collect()
    result.foreach(println) // 1, 0, 0, 2, 1, 2, 1, 1, 1, 2, 3, 3, 4, 5, 7
    listRDD.take(5).foreach(println) //1, 0, 0, 2, 1
    listRDD.takeOrdered(5).foreach(println) // 0, 0, 1, 1, 1
    listRDD.top(5).foreach(println) // 7, 5, 4, 3, 3
  }
}
