package thisisnobody.pairRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * reduceByKey(func: (V, V) => V): RDD[(K, V)]
  * 按键并行整合pairRDD的值
  */
object reduceByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("reduceByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val pairRDD = sc.parallelize(List((1, 2), (3, 4), (1, 3)))

    val reduceByKeyRDD = pairRDD.reduceByKey(((val1, val2) => (val1 + val2)))

    //标准RDD处理二元组Tuple2(elem1, elem2)，把二元组当作整体，使用._1和._2访问元素
    val reduceRDD = pairRDD.reduce((tuple1, tuple2) => (tuple1._1 + tuple2._1, tuple1._2 + tuple2._2))
    //val reduceRDD = pairRDD.reduce(fun: ((Int, Int), (Int, Int)) => (Int, Int))
    //reduce并行整合所有RDD数据
    println(reduceRDD)
    println(reduceRDD._1)
    println(reduceRDD._2)

    //reduceByKey按键并行整合所有RDD
    println(reduceByKeyRDD.count())
    reduceByKeyRDD.foreach(println)


  }
}
