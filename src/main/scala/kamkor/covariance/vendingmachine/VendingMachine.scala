package kamkor.covariance.vendingmachine

import kamkor.covariance.vendingmachine.DrinksModel._

class VendingMachine[A]() {

}


object VendingMachineExamples extends App {

  class Cafeteria {
    def installVendingMachine(vm: VendingMachine[SoftDrink]): Unit = {}
  }

//  def covariantSubtyping: Unit = {
//    val cafeteria = new Cafeteria
//
//    // covariant subtyping
//    val colaVM: VendingMachine[Cola] = new VendingMachine(List(new Cola))
//    cafeteria.installVendingMachine(colaVM)
//
//    // invariant
//    val softDrinkVM: VendingMachine[SoftDrink] = new VendingMachine(List(new Cola, new TonicWater))
//    cafeteria.installVendingMachine(softDrinkVM)
//
//    // contravariant subtyping, won't compile
//    val juiceVM: VendingMachine[Juice] = new VendingMachine(List(new AppleJuice, new OrangeJuice))
//    //cafeteria.installVendingMachine(juiceVM)
//  }
//
//  def vendingMachineIsImmutable: Unit = {
//    // vending machine is immutable !
//    var vm: VendingMachine[Cola] = new VendingMachine(List(new Cola))
//    vm = vm.dispenseNext
//    vm.currentItem.foreach(println) // prints cola
//    println(vm.dispenseNext.currentItem.isEmpty) // prints true
//  }
//
//  def lowerBoundCanBePowerfull: Unit = {
//    val colaVM: VendingMachine[Cola] = new VendingMachine(List(new Cola))
//    val tonics: List[TonicWater] = List(new TonicWater)
//
//    // type changed to more generic
//    val softDrinkVM: VendingMachine[SoftDrink] = colaVM.addAll(tonics)
//  }

//  covariantSubtyping
//  vendingMachineIsImmutable
//  lowerBoundCanBePowerfull

}
