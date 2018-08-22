package thisisnobody.pairRDD.transformations.doubleRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * subtractByKey[W: ClassTag](other: RDD[(K, W)]): RDD[(K, V)]
  * 获取左边RDD键不在右边RDD的键值对
  * 即如果右边的RDD中存在左边RDD的键，则从左边RDD删除所有该键的键值对，返回剩下的左边键值对
  */
object subtractByKey {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("subtractByKey")
    val sc = new SparkContext(conf)

    val pairRDD1 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD2 = sc.parallelize(List((1, 2), (1, 3), (2, 2), (2, 4), (3, 4)))
    val pairRDD3 = sc.parallelize(List((1, 4), (1, 5), (2, 3), (2, 5), (3, 7)))
    val pairRDD4 = sc.parallelize(List((1, 4), (11, 5), (12, 3), (12, 5), (13, 7)))

    val result1 = pairRDD1.subtractByKey(pairRDD2)
    println(result1.count()) //0
    result1.foreach(println)



    val result2 = pairRDD2.subtractByKey(pairRDD3)
    println(result2.count()) //0
    result2.foreach(println)


    val result3 = pairRDD3.subtractByKey(pairRDD4)
    result3.foreach(println)
    /**
      * (2,3)
      * (2,5)
      * (3,7)
      */
  }
}
