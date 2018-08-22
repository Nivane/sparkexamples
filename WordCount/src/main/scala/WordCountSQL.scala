import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

object WordCountSQL {

  def main(args: Array[String]): Unit = {



    val spark = SparkSession.builder().master("local").appName("WordCountSQL").getOrCreate()

    import spark.implicits._
    val ds = spark.read.textFile("shakespeare.txt").flatMap(x => x.split(" "))

    ds.createOrReplaceTempView("shakespeare")

    //路径是否存在
    val path = new Path("c:/users/zlp/desktop/wc/SQL/")
    val conf = new Configuration()
    val fs = FileSystem.get(conf)
    if (fs.exists(path)) {
      fs.delete(path, true)
    }

    val path1 = new Path("c:/users/zlp/desktop/wc/UntypedAPI/")
    if (fs.exists(path1)) {
      fs.delete(path1, true)
    }

    //SQL
    spark.sql("select value, count(value) from shakespeare group by value").write.format("json").save(path.toString)
    //Untyped API
    ds.groupBy("value").agg("value" -> "count").write.format("json").save(path1.toString)
  }
}
