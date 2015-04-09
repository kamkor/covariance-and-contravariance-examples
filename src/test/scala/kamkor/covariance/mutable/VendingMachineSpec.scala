package kamkor.covariance.mutable

import kamkor.UnitSuite
import kamkor.DrinksModel._
import kamkor.UnitSpec

/**
 * Mutable VendingMachine spec.
 */
class VendingMachineSpec extends UnitSpec {

  "A mutable.VendingMachine addAll function" should {
    "return a new instance of VendingMachine with added elements" in {
      val cola = new Cola
      val colaVM: VendingMachine[Cola] = new VendingMachine(List(cola))

      // Get a new instance of VendingMachine with another Cola
      val anotherCola = new Cola
      val anotherColaVM = colaVM.addAll(List(anotherCola))
      anotherColaVM should not be theSameInstanceAs(colaVM)

      // there is one cola in colaVM
      colaVM.dropNext shouldBe (true)
      colaVM.current.value shouldBe theSameInstanceAs(cola)
      colaVM.dropNext shouldBe (false)

      // there are two colas in anotherColaVM
      anotherColaVM.dropNext shouldBe (true)
      anotherColaVM.current.value shouldBe theSameInstanceAs(cola)
      anotherColaVM.dropNext shouldBe (true)
      anotherColaVM.current.value shouldBe theSameInstanceAs(anotherCola)
      anotherColaVM.dropNext shouldBe (false)
    }

    "accept items with type the same as vending machine type parameter" in {
      val colaVM: VendingMachine[Cola] = new VendingMachine(List(new Cola))
      // should compile
      val anotherColaVM: VendingMachine[Cola] = colaVM.addAll(List(new Cola))
    }

    "accept items with type that is a supertype of vending machine type parameter" in {
      val colaVM: VendingMachine[Cola] = new VendingMachine(List(new Cola))
      // should compile, AppleJuice and Cola have common supertype - a Drink.
      val anotherColaVM: VendingMachine[Drink] = colaVM.addAll(List(new AppleJuice))
    }
  }

  "A mutable.VendingMachine" should {
    "drop (produce) items until its empty" in {
      val drinks = List(new Cola, new Cola, new TonicWater, new AppleJuice)
      val drinkVM: VendingMachine[Drink] = new VendingMachine(drinks)

      // Code below shows that VendingMachine is mutable
      drinkVM.dropNext shouldBe (true)
      drinkVM.current.value shouldBe a[Cola]

      drinkVM.dropNext shouldBe (true)
      drinkVM.current.value shouldBe a[Cola]

      drinkVM.dropNext shouldBe (true)
      drinkVM.current.value shouldBe a[TonicWater]

      drinkVM.dropNext shouldBe (true)
      drinkVM.current.value shouldBe a[AppleJuice]

      drinkVM.dropNext shouldBe (false)
      drinkVM.current shouldBe None
    }
  }

}
