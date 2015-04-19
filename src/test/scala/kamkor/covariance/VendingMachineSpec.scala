package kamkor.covariance

import kamkor.UnitSpec
import kamkor.DrinksModel.Cola
import kamkor.DrinksModel.TonicWater

class VendingMachineSpec extends UnitSpec with VendingMachineBehaviors {

  "A mutable VendingMachine" should {
    behave like vendingMachine(VendingMachine.mutable)

    "mutate its state with nextItem() method" in {
      val drinks = List(new Cola, new TonicWater)
      val vm = VendingMachine.mutable(drinks)
      for (drink <- drinks) {
        vm.nextItem()
        vm.currentItem shouldBe Some(drink)
      }
      vm.nextItem()
      vm.currentItem shouldBe None
    }
  }

  "A immutable VendingMachine" should {
    behave like vendingMachine(VendingMachine.immutable)

    "NOT mutate its state with nextItem() method" in {
      val drinks = List(new Cola, new TonicWater)
      val vm = VendingMachine.immutable(drinks)

      vm.currentItem shouldBe None
      vm.nextItem()
      vm.currentItem shouldBe None
    }
  }

}