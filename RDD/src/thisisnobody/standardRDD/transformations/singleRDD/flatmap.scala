package thisisnobody.standardRDD.transformations.singleRDD

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * flatMap[U: ClassTag](f: T => TraversableOnce[U]): RDD[U]
  * 将函数应用到RDD的每个元素，每个元素可能有多个返回值，所有返回值共同组成一个新的RDD
  */
object flatmap {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("flatmap")
    val sc = new SparkContext(conf)

    //读取外部数据集生成RDD,sc.textFile()也是惰性求值的
    val listRDD = sc.textFile("flatmap.txt").persist(StorageLevel.MEMORY_ONLY)
    //按行读取文本文件flatmap.txt
    listRDD.foreach( x => println(x))
    //对listRDD每个元素执行split()生成新的RDD并持久化缓存
    val flatRDD = listRDD.flatMap( x => x.split(" ")).persist(StorageLevel.MEMORY_ONLY)

    flatRDD.foreach( x => println(x))


  }
}
