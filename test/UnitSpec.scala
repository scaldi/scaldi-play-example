import org.specs2.mutable.Specification
import scaldi.DynamicModule
import service.{OfficialMessageService, MessageService}

class UnitSpec extends Specification {
  "OfficialMessageService" should {
    "produce correct greeting" in {
      val messageService = new OfficialMessageService()(DynamicModule(
        _.binding identifiedBy "greeting.official" to "Howdy"
      ))

      messageService.getGreetMessage("Test") should_== "Howdy, Test!"
    }
  }
}