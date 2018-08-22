import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

object WordCountRDD {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local").appName("wordcount").getOrCreate()

    //读取文件为RDD
    val rdd = spark.sparkContext.textFile("shakespeare.txt")

    //处理RDD
    val res1 = rdd.flatMap(x => x.split(" ")).map((_, 1))
    res1.persist()
    val res2 = res1.reduceByKey(_ + _)

    //路径是否存在
    val split = new Path("c:/users/zlp/desktop/wc/rdd/split")
    val count = new Path("c:/users/zlp/desktop/wc/rdd/count")
    val conf = new Configuration()
    val fs = FileSystem.get(conf)
    if (fs.exists(count)) {
      fs.delete(count, true)
    }

    if (fs.exists(split)) {
      fs.delete(split, true)
    }

    //保存
    res1.saveAsTextFile(split.toString)
    res2.saveAsTextFile(count.toString)

  }

}
