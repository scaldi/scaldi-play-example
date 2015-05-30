package test

import org.specs2.mutable._
import play.api.mvc.{Action, Results}

import play.api.test._
import play.api.test.Helpers._
import scaldi.play.{FakeRouterModule, ScaldiApplicationBuilder}

class IntegrationSpec extends Specification {
  
  "Application" should {
    
    "work from within a browser" in {
      running(TestServer(3333, new ScaldiApplicationBuilder().build()), HTMLUNIT) { browser =>
        browser.goTo("http://localhost:3333/")

        browser.pageSource must contain("Test Page")
      }
    }

    "work from within a browser with fake routes" in {
      val fakeRotes = FakeRouterModule {
        case ("GET", "/some-url") => Action {
          Results.Ok("everything is fine")
        }
      }

      running(TestServer(3333, new ScaldiApplicationBuilder(modules = Seq(fakeRotes)).build()), HTMLUNIT) { browser =>
        browser.goTo("http://localhost:3333/some-url")

        browser.pageSource must contain("everything is fine")
      }
    }

  }
  
}