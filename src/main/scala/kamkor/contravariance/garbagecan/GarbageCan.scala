package kamkor.contravariance.garbagecan

/**
 * GarbageCan that is contravariant in its type parameter.
 *
 * Contravariant subtyping:
 *            A  <:            B
 * GarbageCan[B] <: GarbageCan[A]
 */
class GarbageCan[-A] {

  // below compiles if lower bound is used. items can be put into this list
  //type B >: A
  //private var items: List[B] = _ 

  // compiles because of object private scope
  private[this] var items: List[A] = List.empty

  /** Puts item into this garbage can */
  def put(item: A): Unit = this.items :+= item

  /** Puts items into this garbage can */
  def putAll(items: List[A]): Unit = this.items ++= items

  /** Returns current number of items in the garbage can */
  def itemsCount: Int = this.items.size

}
