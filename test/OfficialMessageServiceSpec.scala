import modules.{WebModule, UserModule}
import play.api.GlobalSettings
import play.api.test.{FakeApplication, PlaySpecification}
import scaldi.Injectable
import scaldi.play.ScaldiSupport
import service.OfficialMessageService

class OfficialMessageServiceSpec extends PlaySpecification with Injectable {

  object TestGlobal extends GlobalSettings with ScaldiSupport {
    def applicationModule = new WebModule :: new UserModule
  }

  implicit def injector = TestGlobal.injector

  "Message Service" should {
    "provide greeting message" in {
      running(FakeApplication(withGlobal = Some(TestGlobal))) {

        // Implicit injector is needed here
        val messageService = new OfficialMessageService
        val expectedMessage = "Welcome, test!"
        val message = messageService.getGreetMessage("test")

        message must beEqualTo (expectedMessage)
      }
    }
  }

}
