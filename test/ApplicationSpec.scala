package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.GlobalSettings
import scaldi.play.ScaldiSupport
import scaldi.Module
import service.MessageService
import modules.{WebModule, UserModule}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {
  
  "Application" should {
    
    "send 404 on a bad request" in {
      running(FakeApplication()) {
        route(FakeRequest(GET, "/boum")) must beNone        
      }
    }
    
    "render the index page" in {
      class TestModule extends Module {
        bind [MessageService] to new MessageService {
          def getGreetMessage(name: String) = "Test Message"
        }
      }

      object TestGlobal extends GlobalSettings with ScaldiSupport {

        // test module will override `MessageService`
        def applicationModule = new TestModule :: new WebModule :: new UserModule
      }

      running(FakeApplication(withGlobal = Some(TestGlobal))) {
        val home = route(FakeRequest(GET, "/")).get
        
        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")

        println(contentAsString(home))

        contentAsString(home) must contain ("Test Message")
      }
    }
  }
}