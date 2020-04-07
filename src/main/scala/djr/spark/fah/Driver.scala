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
    val sparkConfig = new SparkConf()
    sparkConfig.setMaster(configs.getString("spark.master"))
    val sc = SparkContext.getOrCreate(sparkConfig)

    val fahArgs = parseFAHArgs()
    // mock a large input to get it to run forever
    // TODO finalize this design
    val mockRDD = sc.parallelize(1.to(100000)).repartition(100000)

    mockRDD.map(int => {
      System.out.println("pwd".!)
      new ConfiguredClient(fahArgs).execute()
      int + 1
    }).reduce((_, _) => 1)
  }

  def parseFAHArgs(): String = {
   Source.fromFile("fah.properties")
      .getLines()
      .filterNot(_.startsWith("#"))
      .mkString(" ")
  }
}
