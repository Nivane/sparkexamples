package thisisnobody.wordcount

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val in = "c:/users/zlp/desktop/hdfs-site.xml"
    val input = sc.textFile(in)
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey{ case (x, y) => x + y }
    counts.saveAsTextFile("c:/users/zlp/desktop/sparkwordcount")
  }
}
