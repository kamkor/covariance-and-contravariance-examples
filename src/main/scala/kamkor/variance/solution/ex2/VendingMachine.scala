package kamkor.variance.solution.ex2

/**
 * As boss I would expect to be able to install in Cafeteria, in place of vending
 * machine for soft drinks, a vending machine for cola, tonic water or any other
 * kind of soft drink.
 *
 * I want employees to be able to get soft drinks from this machine.
 *
 * I want employees to be able to supply this vending machine with new soft drinks. (tip: immutable)
 *
 */
// Adding prefix + to type A, makes VendingMachine covariant in its type parameter, so boss can install in Cafeteria, in place
// of vending machine for soft drinks, a vending machine for cola, tonic water or any other kind of soft drink.
class VendingMachine[+A](val currentItem: Option[A], items: List[A]) {

  def this(items: List[A]) = this(None, items)

  // Return type (output type of VendingMachine) is natural position of covariant type parameter.
  // One of the exceptions is when you use other parametrized type, like VendingMachine, List etc. It compiles in only one case:
  // - ParametrizedType[+A] is OK
  // - ParametrizedType[A] is NOT OK
  // - ParametrizedType[-A] is NOT OK
  def dispenseNext: VendingMachine[A] = items match {
    case Nil => if (currentItem.isDefined) new VendingMachine(None, Nil) else this
    case t :: ts => new VendingMachine(Some(t), ts)
  }

  // method argument type is illegal position for covariant type parameter
  // one of the exceptions is, when you use lower bound (B >: A). So you can use A or super type of A.
  def add[B >: A](item: B): VendingMachine[B] = new VendingMachine(currentItem, item :: items)

  // explanation same as for method add
  def addAll[B >: A](items: List[B]): VendingMachine[B] = new VendingMachine(currentItem, this.items ++ items)

}

trait Drink

trait Juice extends Drink

class OrangeJuice extends Juice

class AppleJuice extends Juice

trait SoftDrink extends Drink

class Cola extends SoftDrink

class TonicWater extends SoftDrink

class Cafeteria {
  def installVendingMachine(vm: VendingMachine[SoftDrink]): Unit = {}
}

object VendingMachineExample extends App {
  val cafeteria = new Cafeteria

  // where the hell from am I going to find a vending machine that dispenses any kind of Soft Drink?
  // It would be nice if I could install something more specific..
  val softDrinkVM: VendingMachine[SoftDrink] = new VendingMachine[SoftDrink](List(new Cola, new TonicWater))
  cafeteria.installVendingMachine(softDrinkVM)

  var colaVM: VendingMachine[Cola] = new VendingMachine[Cola](List(new Cola))
  // Compiles, because we have made VendingMachine covariant in its type parameter (added + prefix to type parameter A)
  // covariant subtyping
  cafeteria.installVendingMachine(colaVM)

  // try to take some soft drinks from the vending machine, vending machine is immutable
  colaVM = colaVM.dispenseNext
  colaVM.currentItem.foreach(println)

  // try to supply the vending machine with new soft drinks
  colaVM = colaVM.add(new Cola).addAll(List(new Cola, new Cola))

  // what if you have a cola vending machine and supply it with TonicWater ?
  // SoftDrink is the common super type of Cola and TonicWater. Smart compiler ! :)
  val anotherSoftDrinkVM: VendingMachine[SoftDrink] = colaVM.add(new TonicWater)

  dispenseAllDrinks(anotherSoftDrinkVM)

  def dispenseAllDrinks(vm: VendingMachine[Drink]): Unit = {
    val dispensedVM = vm.dispenseNext

    dispensedVM.currentItem.foreach(item => {
      println(item)
      dispenseAllDrinks(dispensedVM)
    })
  }

}




