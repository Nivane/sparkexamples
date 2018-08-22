package thisisnobody.sharedVariables

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 广播变量是只读的
  * 高效分发大对象
  */
object broadcast {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("broadcast").setMaster("local")
    val sc = new SparkContext(conf)

    val broadcast1 = sc.broadcast(Array(1,2,3))

    broadcast1.value.foreach(println)


  }

}
