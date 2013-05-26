package modules

import service.{OfficialMessageService, SimpleMessageService, MessageService}
import scaldi.Module
import scaldi.play.condition._

class UserModule extends Module {
  bind [MessageService] when (inDevMode or inTestMode) to new SimpleMessageService
  bind [MessageService] when inProdMode to new OfficialMessageService
}
