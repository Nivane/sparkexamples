package thisisnobody.sharedVariables

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 累加器只能在驱动器程序中访问，不可以在工作节点访问，是只写的
  */
object accumulator {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("broadcast").setMaster("local")
    val sc = new SparkContext(conf)


    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 1))
    val num = sc.longAccumulator("HowMany1")

    val newRDD = rdd.filter(x => {
      if (x == 1) {
        num.add(1L)
        println("a:==============" + num.value)
      }
      x == 1
    })


    println("c:==============" + newRDD.count())
    newRDD.collect().foreach(println)
    println(num.value)

    /**
      * 只有执行action操作才开始计算transformation中的操作
      * a:==============1
      * a:==============2
      * c:==============2
      * a:==============1
      * a:==============2
      * 1
      * 1
      * 4
      */
  }
}
