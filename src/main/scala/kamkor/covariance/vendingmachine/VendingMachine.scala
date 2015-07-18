package kamkor.covariance.vendingmachine

object VendingMachine {

  /** Completely immutable vending machine. */
  def apply[A](items: List[A]): VendingMachine[A] = new DefaultVendingMachine(items)

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

  /**
   *  Returns a vending machine with next item in the currentItem or
   *  with none if there are no items left.
   */
  def nextItem(): VendingMachine[A]

  /* If you use a lower bound for a type parameter,
   * you can use it as a type of a method argument. */
  /** Returns a vending machine with added items. */
  def addAll[B >: A](newItems: List[B]): VendingMachine[B]

}

/**
 * Default Vending Machine implementation
 */
private class DefaultVendingMachine[+A](val currentItem: Option[A], items: List[A])
  extends VendingMachine[A] {

  def this(items: List[A]) = this(None, items)

  def nextItem: DefaultVendingMachine[A] = items match {
    case Nil     => if (currentItem.isDefined) new DefaultVendingMachine(None, Nil) else this
    case t :: ts => new DefaultVendingMachine(Some(t), ts)
  }

  def addAll[B >: A](newItems: List[B]): DefaultVendingMachine[B] = {
    new DefaultVendingMachine(items ++ newItems)
  }

}
