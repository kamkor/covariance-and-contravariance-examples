package kamkor.covariance.mutable

import scala.collection._

/**
 * Mutable Vending Machine.
 *
 * 1. It shows that you can use covariant type parameter in a mutable field 
 * if you set scope of this field to private[this]. Let me explain with 
 * quote from Programming In Scala:
 *
 * Object private members can be accessed only from within the object in
 * which they are defined. It turns out that accesses to variables from the
 * same object in which they are defined do not cause problems with variance.
 * The intuitive explanation is that, in order to construct a case where
 * variance would lead to type errors, you need to have a reference to
 * a containing object that has a statically weaker type than the type the
 * object was defined with. For accesses to object private values, however,
 * this is impossible.
 *
 * 2. Also shows that if you use a lower bound for a type parameter, you can
 * use it as a type of a method argument.
 */
class VendingMachine[+A](val initItems: List[A]) {

  private[this] val items: mutable.Stack[A] = mutable.Stack(initItems: _*)

  private[this] var _current: Option[A] = None

  /** Returns item currently in the slot. */
  def current: Option[A] = _current

  /**
   * Drops next item to the slot.
   *
   * @return true if item was dropped or false if there are no items left.
   */
  def dropNext: Boolean = {
    _current = if (items.isEmpty) None else Some(items.pop())
    current.isDefined
  }

  /** Returns a new instance of VendingMachine with added elements. */
  def addAll[B >: A](newItems: List[B]): VendingMachine[B] = {
    new VendingMachine(items.toList ++ newItems)
  }

}
