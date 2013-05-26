package controllers

import play.api.mvc._
import scaldi.{Injectable, Injector}
import service.MessageService

class Application(implicit inj: Injector) extends Controller with Injectable {
  val messageService = inject [MessageService]

  def index = Action {
    Ok(views.html.index(messageService.getGreetMessage("Test User")))
  }
  
}