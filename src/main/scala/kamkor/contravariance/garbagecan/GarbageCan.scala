package kamkor.contravariance.garbagecan

import kamkor.contravariance.garbagecan.TrashModel.Paper

object GarbageCan {

  def apply[A](): GarbageCan[A] = new DefaultGarbageCan

}

/**
 * GarbageCan that is contravariant in its type parameter.
 *
 * Contravariant subtyping:
 *                A  <:                B
 * VendingMachine[B] <: VendingMachine[A]
 */
trait GarbageCan[-A] {

  /** Puts item into this garbage can */
  def put(item: A): Unit

  /** Puts items into this garbage can */
  def putAll(items: List[A]): Unit

  /** Returns current number of items in the garbage can */
  val itemsCount: Int

}

private class DefaultGarbageCan[-A] extends GarbageCan[A] {
  
  // below compiles if upper bound is used. items can be put into this list
  //type B >: A
  //private var items: List[B] = _ 
  
  private var _itemsCount: Int = _
  val itemsCount: Int = _itemsCount

  def put(item: A): Unit = {    
    _itemsCount += 1
    println(s"putting ${item.getClass().getSimpleName} to trash")
  }

  def putAll(items: List[A]): Unit = {
    _itemsCount += items.size
    println(s"putting ${items.map(_.getClass().getSimpleName)} to trash")
  }

}
