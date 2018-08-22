package thisisnobody.basicRDD.transformations.singleRDD

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * map[U: ClassTag](f: T => U): RDD[U]
  * 将函数应用于RDD的每个元素，返回值构成新的RDD
  */
object map {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("map")
    val sc = new SparkContext(conf)

    //在驱动器程序中对一个集合并行化以创建RDD
    val listRDD = sc.parallelize(List(1,2,3,4,5))

    //转化操作
    //求RDD所有值平方，构成新的RDD，且转化操作的结果持久化到内存中，执行多次行动操作时不需要重新计算相同的转换操作RDD
    val squareListRDD = listRDD.map( x => x * x).persist(StorageLevel.MEMORY_ONLY)

    //行动操作
    println(squareListRDD.count()) //返回RDD元素的个数
    squareListRDD.collect().foreach(x => println(x)) //返回RDD中所有元素并对每个元素执行foreach中的函数


  }
}
