package kamkor.covariance.vendingmachine

import kamkor.covariance.vendingmachine.DrinksModel._

class VendingMachine[A] {

}


class Cafeteria {
  def installVendingMachine(vm: VendingMachine[SoftDrink]): Unit = {}
}

object VendingMachineExamples extends App {

  val cafeteria = new Cafeteria

}
