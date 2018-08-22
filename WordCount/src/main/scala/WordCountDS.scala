import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

object WordCountDS {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("WordCountDS").master("local").getOrCreate()

    val df = spark.read.textFile("shakespeare.txt")

    import spark.implicits._

    //路径是否存在
    val count = new Path("c:/users/zlp/desktop/wc/DataSet/")
    val conf = new Configuration()
    val fs = FileSystem.get(conf)
    if (fs.exists(count)) {
      fs.delete(count, true)
    }

    //会默认按照200分区shuffle
    df.flatMap(_.split(" ")).groupBy("value").count().write.format("json").save(count.toString)

  }
}
