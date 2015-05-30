package modules

import scaldi._
import service.ServerStatusService

class ServerModule extends Module {
  binding to new ServerStatusService
}
