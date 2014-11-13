import modules.{WebModule, UserModule}
import play.api.GlobalSettings
import play.api.test.{FakeApplication, PlaySpecification}
import scaldi.Injectable
import scaldi.play.ScaldiSupport
import service.MessageService

class MessageServiceSpec extends PlaySpecification with Injectable {

  object TestGlobal extends GlobalSettings with ScaldiSupport {
    def applicationModule = new WebModule :: new UserModule
  }

  // As this class is declared as Injectable, we should provide implicit injector
  implicit lazy val injector = TestGlobal.injector

  "Message Service" should {
    "provide greeting message" in {
      running(FakeApplication(withGlobal = Some(TestGlobal))) {

        // Injecting the service
        val messageService = inject[MessageService]

        val expectedMessage = "Hi, test"
        val message = messageService.getGreetMessage("test")

        message must beEqualTo(expectedMessage)
      }
    }
  }

}
