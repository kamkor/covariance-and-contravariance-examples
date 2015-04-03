package kamkor.covariance

import scala.annotation.migration
import scala.collection._

import kamkor.DrinksModel.AppleJuice
import kamkor.DrinksModel.Cola
import kamkor.DrinksModel.Drink
import kamkor.DrinksModel.SoftDrink
import kamkor.DrinksModel.TonicWater
import kamkor.UnitSpec

/**
 * Example of Covariance. Generally covariance is used for producers. VendingMachine
 * is such a producer. It produces drinks.
 */
class VendingMachineExample extends UnitSpec {

  //                A  <:                B
  // VendingMachine[A] <: VendingMachine[B]
  //
  // If A is a subtype of B then VendingMachine[A] should be a subtype of VendingMachine[B].
  // This property is called covariant subtyping.
  //
  // Declaration-site variance means that annotation is added by the creator of the class/trait.
  test("covariant subtyping using declaration-site variance") {

    // Prefixing a formal type parameter with a + indicates
    // that subtyping is covariant in that parameter.
    //
    // Alternatively, we can say that class VendingMachine is covariant in its type parameter A.
    class VendingMachine[+A] {
      // .. don't worry about implementation yet
    }

    def install(vendingMachine: VendingMachine[SoftDrink]): Unit = {
      // Installs soft drink vending machine
    }

    // Covariant subtyping because Cola and Tonic Water are subtypes of SoftDrink
    install(new VendingMachine[Cola])
    install(new VendingMachine[TonicWater])

    // nonvariant or invariant, because the types match
    install(new VendingMachine[SoftDrink])

    // Compile error!!
    // This is contravariant subtyping because Drink is a supertype of SoftDrink
    // install(new VendingMachine[Drink])
  }

  // Use-site variance means that variance is added by user of the class/trait
  // using bounds:
  // _ <: A for covariance     or in Java: ? extends A
  // _ >: A for contravariance or in Java: ? super A
  test("covariant subtyping using use-site variance") {

    class VendingMachine[A] {
      // Vending Machine is invariant in type parameter A, so
      // you can that type parameter hovewer you want.
    }

    // use-site variance using bounds
    def install(vendingMachine: VendingMachine[_ <: SoftDrink]): Unit = {
      // Installs soft drink vending machine
    }

    install(new VendingMachine[Cola])
    install(new VendingMachine[TonicWater])
    install(new VendingMachine[SoftDrink])

    // Compile error!! 
    // Install expects VendingMachines of SoftDrink or subtype of SoftDrink
    // install(new VendingMachine[Drink])
  }

  // Scala compiler prevents you from using covariant type parameter
  // in positions that could lead to potential runtime errors.
  test("Legal usage of covariant type parameter") {

    class VendingMachine[+A](initDrinks: List[A]) {

      // when defined with [this], drinks can be accessed
      // only from within this object. Access to variables from
      // the same object in which they are defined do not cause problems
      // with variance. If you remove [this], you will get a Compile Error.
      private[this] val drinks: mutable.Stack[A] = mutable.Stack(initDrinks: _*)

      private[this] var _current: Option[A] = None

      // Item currently in the slot
      def current: Option[A] = _current

      // Drops next item to the slot 
      def dropNext: Boolean = {
        _current = if (!drinks.isEmpty) Some(drinks.pop()) else None
        current.isDefined
      }

      // If you define lower bound for covariant type parameter, you can use
      // it in method argument
      def addAll[B >: A](newDrinks: List[B]): VendingMachine[B] = {
        new VendingMachine(newDrinks ++ drinks.toList)
      }

    }

    var colaVM: VendingMachine[Cola] = new VendingMachine(List(new Cola))
    // Get a new instance of VendingMachine with another Cola
    colaVM = colaVM.addAll(List(new Cola))

    // SoftDrink is the common type of Cola and TonicWater,
    // so a soft drink VendingMachine is returned.
    val softDrinkVM: VendingMachine[SoftDrink] = colaVM.addAll(List(new TonicWater))

    // Drink is the common type of Cola, TonicWater and AppleJuice,
    // so a drink VendingMachine is returned.
    val drinkVM: VendingMachine[Drink] = softDrinkVM.addAll(List(new AppleJuice))

    drinkVM.dropNext shouldBe (true)
    drinkVM.current.value shouldBe a[AppleJuice]

    drinkVM.dropNext shouldBe (true)
    drinkVM.current.value shouldBe a[TonicWater]

    drinkVM.dropNext shouldBe (true)
    drinkVM.current.value shouldBe a[Cola]

    drinkVM.dropNext shouldBe (true)
    drinkVM.current.value shouldBe a[Cola]

    drinkVM.dropNext shouldBe (false)
    drinkVM.current shouldBe None
  }

  test("Illegal usage of covariant type parameter. Also see the MutableCellExample.scala") {
    // When type parameter is used in illegal position, compile error is reported: 
    // covariant type A occurs in invariant position in type

    // you can't use covariant type parameter as type parameter of invariant types
    // class VendingMachine[+A](val initDrinks: mutable.Stack[A])]

    // if you define field as private[this] then it is safe. See MutableCellExample.scala    
    class VendingMachine[+A](private[this] val initDrinks: mutable.Stack[A])    

    // you can't have a mutable field that is accessible from object other than this
    // class VendingMachine[+A](var initDrinks: immutable.List[A])

    // if you define field as private[this] then it is safe. See MutableCellExample.scala    
    class VendingMachine2[+A](private[this] var initDrinks: immutable.List[A])    

    // can't use in method
    // TODO
  }

}
