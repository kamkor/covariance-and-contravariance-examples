package kamkor.covariance.vendingmachine

import kamkor.UnitSpec
import kamkor.covariance.vendingmachine.DrinksModel._

class VendingMachineSpec extends UnitSpec {

  "A VendingMachine" should {
    "return all colas passed to its constructor method" in {
      val colas = List(new Cola, new Cola)
      val colaVM: VendingMachine[Cola] = VendingMachine(colas)
      shouldGiveAllDrinks(colaVM, colas)
    }

    "return all softDrinks added to it with addAll" in {
      val initialColas = List(new Cola)
      val addedColas = List(new Cola, new Cola)
      val addedTonicWaters = List(new TonicWater, new TonicWater, new TonicWater)

      val softDrinkVM: VendingMachine[SoftDrink] =
        VendingMachine
          .apply(initialColas)
          .addAll(addedColas)
          .addAll(addedTonicWaters)

      shouldGiveAllDrinks(softDrinkVM, initialColas ::: addedColas ::: addedTonicWaters)
    }

    "accept items of type the same as vending machine type parameter" in {
      val colaVM: VendingMachine[Cola] = VendingMachine(List(new Cola))
      // should compile
      val anotherColaVM: VendingMachine[Cola] = colaVM.addAll(List(new Cola))
    }

    "accept items of type that is a supertype of vending machine type parameter" in {
      val colaVM: VendingMachine[Drink] = VendingMachine(List(new Cola))
      // should compile, AppleJuice and Cola have common supertype - a Drink.
      val drinkVM: VendingMachine[Drink] = colaVM.addAll(List(new AppleJuice))
    }
  }

  final def shouldGiveAllDrinks(vm: VendingMachine[Drink], drinks: List[Drink]): Unit = {
    val vmWithoutNextItems =
      drinks.foldLeft(vm) { (vm, drink) =>
        val vmWithNextItem = vm.dispenseNext()
        vmWithNextItem.currentItem.value shouldBe theSameInstanceAs(drink)
        vmWithNextItem
      }

    vmWithoutNextItems.dispenseNext().currentItem shouldBe None
  }

}