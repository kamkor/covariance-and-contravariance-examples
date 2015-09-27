package kamkor.covariance.vendingmachine

object VendingMachine {

  /** Creates default implementation of VendingMachine. */
  def apply[A](items: List[A]): VendingMachine[A] = new VendingMachine(items)

}

/**
 * VendingMachine that is covariant in its type parameter.
 *
 * Covariant subtyping:
 *                A  <:                B
 * VendingMachine[A] <: VendingMachine[B]
 *
 * @param currentItem item currently in the slot
 */
class VendingMachine[+A](val currentItem: Option[A], items: List[A]) {

  def this(items: List[A]) = this(None, items)

  /**
   *  Returns a vending machine with next item in the currentItem or
   *  with none if there are no items left.
   */
  def dispenseNext(): VendingMachine[A] =
    items match {
      case Nil =>
        if (currentItem.isDefined) 
          new VendingMachine(None, Nil)
        else 
          this
      case t :: ts =>
        new VendingMachine(Some(t), ts)
    }

  /* If you use a lower bound for a type parameter,
   * you can use it as a type of a method argument. */
  /** Returns a vending machine with added items. */
  def addAll[B >: A](newItems: List[B]): VendingMachine[B] =
    new VendingMachine(items ++ newItems)

}
