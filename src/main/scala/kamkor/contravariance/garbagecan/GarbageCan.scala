package kamkor.contravariance.garbagecan

object GarbageCan {

  def apply[A](): GarbageCan[A] = new DefaultGarbageCan

}

/**
 * GarbageCan that is contravariant in its type parameter.
 *
 * Contravariant subtyping:
 *            A  <:            B
 * GarbageCan[B] <: GarbageCan[A]
 */
trait GarbageCan[-A] {

  /** Puts item into this garbage can */
  def put(item: A): Unit

  /** Puts items into this garbage can */
  def putAll(items: List[A]): Unit

  /** Returns current number of items in the garbage can */
  def itemsCount: Int

}

private class DefaultGarbageCan[-A]() extends GarbageCan[A] {

  // below compiles if lower bound is used. items can be put into this list
  //type B >: A
  //private var items: List[B] = _ 
  
  // compiles because of object private scope
  private[this] var items: List[A] = List.empty

  def itemsCount: Int = items.size

  def put(item: A): Unit = {    
    this.items :+= item
    println(s"putting ${item.getClass().getSimpleName} to trash")
  }

  def putAll(items: List[A]): Unit = {
    this.items ++= items
    println(s"putting ${items.map(_.getClass().getSimpleName)} to trash")
  }

}
