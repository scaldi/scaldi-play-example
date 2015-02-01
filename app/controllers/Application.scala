package controllers

import play.api.mvc._
import scaldi.{Injectable, Injector}
import service.{ServerStatusService, MessageService}

class Application(implicit inj: Injector) extends Controller with Injectable {
  val messageService = inject [MessageService]

  val serverStatus = new ServerStatusService

  def index = Action {
    Ok(views.html.index(messageService.getGreetMessage("Test User") + ". " + serverStatus.status))
  }
}