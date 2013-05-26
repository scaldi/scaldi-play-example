import modules.{WebModule, UserModule}
import play.api.GlobalSettings
import scaldi.play.ScaldiSupport

object Global extends GlobalSettings with ScaldiSupport {
  def applicationModule = new WebModule :: new UserModule
}
