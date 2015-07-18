package kamkor.contravariance.garbagecan

object GarbageCan {

  def apply[A](): GarbageCan[A] = new DefaultGarbageCan

}

trait GarbageCan[-A] {

  def put(item: A): Unit

  def putAll(items: List[A]): Unit

  val size: Int

}

private class DefaultGarbageCan[-A] extends GarbageCan[A] {

  private var _size: Int = 0
  val size = _size

  def put(item: A): Unit = {
    _size += 1
    println(s"putting ${item.getClass().getSimpleName} to trash")
  }

  def putAll(items: List[A]): Unit = {
    _size += items.size
    println(s"putting ${items.map(_.getClass().getSimpleName)} to trash")
  }

}
