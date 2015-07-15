package kamkor.contravariance

object GarbageCan {

  def apply[A](): GarbageCan[A] = new DefaultGarbageCan

}

trait GarbageCan[-A] {

  def put(item: A): Unit

  def putAll(items: List[A]): Unit

}

private class DefaultGarbageCan[-A] extends GarbageCan[A] {

  def put(item: A): Unit = {
    // only side effects
    println(s"putting ${item.getClass().getSimpleName} to trash")
  }

  def putAll(items: List[A]): Unit = {
    // only side effects   
    println(s"putting ${items.map(_.getClass().getSimpleName)} to trash")
  }

}
