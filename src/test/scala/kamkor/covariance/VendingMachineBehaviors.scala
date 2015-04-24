package kamkor.covariance

import org.scalatest._

import kamkor.DrinksModel.AppleJuice
import kamkor.DrinksModel.Cola
import kamkor.DrinksModel.Drink

trait VendingMachineBehaviors { this: WordSpec with Matchers with OptionValues with Inside with Inspectors =>

  def vendingMachine(newVM: List[Cola] => VendingMachine[Drink]) {
    "return instance of VendingMachine with added elements" in {
      val colas = List(new Cola, new Cola)

      // Instance with first Cola
      val colaVM: VendingMachine[Drink] = newVM(colas.take(1))
      // Get another instance of VendingMachine with added second Cola
      val anotherColaVM = colaVM.addAll(colas.drop(1))

      // there is only first Cola in colaVM
      shouldGiveAllDrinks(colaVM, colas.take(1))

      // there are both Colas in anotherColaVM
      shouldGiveAllDrinks(anotherColaVM, colas)
    }

    "accept items of type the same as vending machine type parameter" in {
      val colaVM: VendingMachine[Drink] = newVM(List(new Cola))
      // should compile
      val anotherColaVM: VendingMachine[Drink] = colaVM.addAll(List(new Cola))
    }

    "accept items of type that is a supertype of vending machine type parameter" in {
      val colaVM: VendingMachine[Drink] = newVM(List(new Cola))
      // should compile, AppleJuice and Cola have common supertype - a Drink.
      val anotherColaVM: VendingMachine[Drink] = colaVM.addAll(List(new AppleJuice))
    }
  }

  final def shouldGiveAllDrinks(vm: VendingMachine[Drink], drinks: List[Drink]): Unit = {
    val vmWithoutNextItems =
      drinks.foldLeft(vm) { (vm, drink) =>
        val vmWithNextItem = vm.nextItem()
        vmWithNextItem.currentItem.value shouldBe theSameInstanceAs(drink)
        vmWithNextItem
      }

    vmWithoutNextItems.nextItem().currentItem shouldBe None
  }

}
