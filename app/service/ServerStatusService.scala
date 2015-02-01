package service

import scaldi.{Injectable, Injector}

class ServerStatusService(implicit inj: Injector) extends Injectable {
  val configNumbers = inject [List[Int]] ("test.numbers")

  def status: String = s"OK (${configNumbers mkString ", "})"
}
