package djr.spark.fah

import scala.sys.process._

class ConfiguredClient(args: String) {

  def execute(): Unit = {
    System.out.println("pwd".!)
    System.out.println("ls FAH".!)
    val command = "./FAH/fah/usr/bin/FAHClient" +
      " " +
      args
    System.out.println(s"executing command ${command}")
    command.!
  }
}
