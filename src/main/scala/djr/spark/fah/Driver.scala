package djr.spark.fah

import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory
import org.apache.commons.configuration.PropertiesConfiguration

import scala.io.Source
import scala.sys.process._

object Driver {
  def main(args: Array[String]): Unit = {
    val log = LoggerFactory.getLogger(Driver.getClass)
    val configs = new PropertiesConfiguration("app.properties")
    val cpus = configs.getString("fah.cpus")
    val sparkConfig = new SparkConf()
    sparkConfig.setAppName(configs.getString("spark.appName"))
    sparkConfig.setMaster(configs.getString("spark.master"))
    sparkConfig.set("spark.task.cpus", cpus)
    sparkConfig.set("spark.executor.cores", cpus)
    sparkConfig.set("spark.yarn.queue", configs.getString("spark.yarn.queue"))
    val fahArgs = parseFAHArgs(cpus)

    val sc = SparkContext.getOrCreate(sparkConfig)

    // mock a large input to get it to run forever
    // TODO finalize this design
    val mockRDD = sc.parallelize(1.to(100000)).repartition(100000)

    mockRDD.map(int => {
      System.out.println("pwd".!)
      new ConfiguredClient(fahArgs).execute()
      int + 1
    }).reduce((_, _) => 1)
  }

  def parseFAHArgs(cpus: String): String = {
   Source.fromFile("fah.properties")
      .getLines()
     // ignore comments
      .filterNot(_.startsWith("#"))
     // ignore extra CPUs param
      .filterNot(_.startsWith("--cpus"))
     // ignore extra User param
      .filterNot(_.startsWith("--user"))
      .mkString(" ") ++
    s" --cpus=${cpus}" ++
    " --user=SparkFAH"
  }
}
