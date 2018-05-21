package test

import org.specs2.mutable._
import play.api.inject.BuiltinModule
import play.api.test._
import play.api.test.Helpers._
import scaldi.play.{ControllerInjector, ScaldiApplicationBuilder}
import scaldi.{Injectable, Module}
import service.MessageService
import modules.{ServerModule, UserModule}
import scaldi.play.condition._
import ScaldiApplicationBuilder._
import controllers.AssetsModule
import play.api.{Application, Configuration, Environment, Logger}
import play.api.cache.ehcache.EhCacheModule
import play.api.http.HeaderNames
import play.api.i18n.I18nModule
import play.api.mvc.{AnyContentAsEmpty, CookiesModule}
import play.filters.csrf.CSRFModule
import play.filters.hosts.{AllowedHostsConfig, AllowedHostsModule}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification with Injectable {

  private val logger = Logger(this.getClass)

  "Application" should {
    
    "send 404 on a bad request" in {
      withScaldiInj() { implicit inj =>
        val application = inject[Application]
        status(route(application, FakeRequest(GET, "/boum")).get) must equalTo(NOT_FOUND)
      }
    }

    class TestModule extends Module {
      bind [MessageService] when (inDevMode or inTestMode) to new MessageService {
        def getGreetMessage(name: String) = "Test Message"
      }
    }

    "render the index page (with full control over the module composition)" in {
      val module = new TestModule :: new ServerModule :: new UserModule :: new ControllerInjector

      withScaldiInj(
        modules = Seq(module, new AllowedHostsModule, new CookiesModule, new AssetsModule, new CSRFModule, new I18nModule, new EhCacheModule, new BuiltinModule),
        loadModules = (_, _) => Seq.empty
      ) { implicit inj =>

        val application = inject[Application]

        val home =  route(application, FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }

    "render the index page (with just override module)" in {
      withScaldiInj(modules = Seq(new TestModule)) { implicit inj =>

        val application = inject[Application]

        val home =  route(application, FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }

    "render the index page (with explicit `ScaldiApplicationBuilder` usage)" in {
      val application = new ScaldiApplicationBuilder().prependModule(new TestModule).build()

      running(application) {
        val home =  route(application, FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        contentAsString(home) must contain ("Test Message")
      }
    }
  }
}