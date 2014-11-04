package scaldi.play

import play.api.inject.{Injector => PlayInjector, BindingKey}
import scala.reflect.ClassTag

import scaldi._
import scaldi.Injectable.{noBindingFound, injectWithDefault}

class ScaldiInjector(implicit inj: Injector) extends PlayInjector {
  def instanceOf[T](implicit ct: ClassTag[T]) =
    instanceOf(ct.runtimeClass.asInstanceOf[Class[T]])

  def instanceOf[T](clazz: Class[T]) =
    instanceOf(BindingKey(clazz))

  def instanceOf[T](key: BindingKey[T]) = {
    val (_, identifiers) = ScaldiApplicationLoader.identifiersForKey(key)

    injectWithDefault[T](inj, noBindingFound(identifiers))(identifiers)
  }
}