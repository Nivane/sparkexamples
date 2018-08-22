package thisisnobody.standardRDD.transformations.singleRDD

import org.apache.spark.{SparkConf, SparkContext}


/**
  * filter(f: T => Boolean): RDD[T]
  * 将函数应用到RDD中每个元素，函数返回值类型为Boolean,函数返回值为true时，则保留元素
  */
object filter {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)

    val listRDD = sc.parallelize(List(1,0,0,2,1,2,1,1,1,2,3,3,4,5,7))
    //filter()中函数必须返回Boolean以过滤信息
    val filterRDD = listRDD.filter( x => (x % 2 == 0))

    filterRDD.foreach( x => println(x))

  }
}
