package scaldi.play

import play.api.mvc.Controller
import play.api.inject.{Injector => PlayInjector, BindingKey}
import scala.reflect.ClassTag
import scala.collection.concurrent.TrieMap
import scaldi.Injector

class ControllerCachingPlayInjector(delegate: PlayInjector)(implicit inj: Injector) extends PlayInjector {
  private val controllerCache = TrieMap[Class[_], Any]()

  def instanceOf[T](implicit ct: ClassTag[T]) =
    if (classOf[Controller].isAssignableFrom(ct.runtimeClass))
      controllerCache.getOrElseUpdate(ct.runtimeClass, delegate.instanceOf[T](ct)).asInstanceOf[T]
    else
      delegate.instanceOf[T](ct)


  def instanceOf[T](clazz: Class[T]) =
    if (classOf[Controller].isAssignableFrom(clazz))
      controllerCache.getOrElseUpdate(clazz, delegate.instanceOf[T](clazz)).asInstanceOf[T]
    else
      delegate.instanceOf[T](clazz)


  def instanceOf[T](key: BindingKey[T]) =
    if (classOf[Controller].isAssignableFrom(key.clazz) && key.qualifier.isEmpty)
      controllerCache.getOrElseUpdate(key.clazz, delegate.instanceOf[T](key)).asInstanceOf[T]
    else
      delegate.instanceOf[T](key)
}
