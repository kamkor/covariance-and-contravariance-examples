package kamkor.covariance

object VendingMachine {

  /** Completely immutable vending machine. */
  def immutable[A](items: List[A]): VendingMachine[A] = new ImmutableVendingMachine[A](items)

  /** Returns a partly mutable vending machine. nextItem() mutates vendingMachine. */
  def mutable[A](items: List[A]): VendingMachine[A] = new MutableVendingMachine[A](items)

}

/**
 * VendingMachine that is covariant in its type parameter.
 *
 * Covariant subtyping:
 *                A  <:                B
 * VendingMachine[A] <: VendingMachine[B]
 */
trait VendingMachine[+A] {

  /** Item currently in the slot. */
  def currentItem: Option[A]

  /** Returns a vending machine with next item in the currentItem. */
  def nextItem(): VendingMachine[A]

  /* If you use a lower bound for a type parameter,
   * you can use it as a type of a method argument. */
  /** Returns a new instance of ImmutableVendingMachine with added items. */
  def addAll[B >: A](newItems: List[B]): VendingMachine[B]

}

/**
 * Immutable Vending Machine.
 */
private class ImmutableVendingMachine[+A](val currentItem: Option[A], items: List[A])
  extends VendingMachine[A] {

  def this(items: List[A]) = this(None, items)

  /**
   * Returns a new ImmutableVendingMachine instance with next item in the current slot
   * or the same instance if there were no more items left.
   */
  def nextItem: ImmutableVendingMachine[A] = items match {
    case Nil     => if (currentItem.isDefined) new ImmutableVendingMachine(None, Nil) else this
    case t :: ts => new ImmutableVendingMachine(Some(t), ts)
  }
  /*
    if (items.size > 1) {
      new ImmutableVendingMachine(items.headOption, items.tail)
    } else if (items.size == 1) {
      new ImmutableVendingMachine(items.headOption, Nil)
    } else if (currentItem.isDefined && items.isEmpty) {
      new ImmutableVendingMachine(None, Nil)
    } else {
      this
    }*/

  /** Returns a new instance of ImmutableVendingMachine with added items. */
  def addAll[B >: A](newItems: List[B]): ImmutableVendingMachine[B] = {
    new ImmutableVendingMachine(items ++ newItems)
  }

}

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
private class MutableVendingMachine[+A](private[this] var items: List[A])
  extends VendingMachine[A] {

  private[this] var _current: Option[A] = None

  /** Returns item currently in the slot. */
  def currentItem: Option[A] = _current

  /**
   * Drops next item to the slot.
   *
   * @return true if item was dropped or false if there are no items left.
   */
  def nextItem(): MutableVendingMachine[A] = {
    items match {
      case Nil => {
        _current = None
      }
      case t :: ts => {
        _current = Some(t)
        items = ts
      }
    }
    this
  }

  /** Returns a new instance of MutableVendingMachine with added items. */
  def addAll[B >: A](newItems: List[B]): MutableVendingMachine[B] = {
    new MutableVendingMachine(items ++ newItems)
  }

}


