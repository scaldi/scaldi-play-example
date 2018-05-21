package controllers

import net.sf.ehcache.CacheManager
import play.api.mvc._
import scaldi.{Injectable, Injector}
import service.{ServerStatusService, MessageService}
import play.api.cache.AsyncCacheApi

class Application(implicit inj: Injector) extends InjectedController with Injectable {
  val cache = inject [AsyncCacheApi]

  val messageService = inject [MessageService]

  val serverStatus = inject [ServerStatusService]
  val cacheManager = inject [CacheManager]

  def index = Action {
    Ok(views.html.index(messageService.getGreetMessage("Test User") + ". " + serverStatus.status))
  }
}
