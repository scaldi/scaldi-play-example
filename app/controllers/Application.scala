package controllers

import net.sf.ehcache.CacheManager
import play.api.mvc._
import scaldi.{Injectable, Injector}
import service.{ServerStatusService, MessageService}
import play.api.cache.CacheApi

class Application(implicit inj: Injector) extends Controller with Injectable {
  val cache = inject [CacheApi]

  val messageService = inject [MessageService]

  val serverStatus = inject [ServerStatusService]
  val cacheManager = inject [CacheManager]

  def index = Action {
    require(cacheManager.getCache("foo") == null) // here to check play-cache usage

    Ok(views.html.index(messageService.getGreetMessage("Test User") + ". " + serverStatus.status))
  }
}