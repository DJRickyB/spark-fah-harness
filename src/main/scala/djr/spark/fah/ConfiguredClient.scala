package djr.spark.fah

import scala.sys.process._

class ConfiguredClient(args: String) {


  //TODO implement detection or house this in a docker container
  //private val os = "rhel"

  def execute(): Unit = {
    System.out.println("pwd".!)
    System.out.println("ls FAH".!)
    val command = "./FAH/FAH/usr/bin/FAHClient" +
      " " +
      args
    System.out.println(s"executing command ${command}")
    command.!
  }
}
