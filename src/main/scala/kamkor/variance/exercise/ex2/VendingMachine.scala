package kamkor.variance.exercise.ex2

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
class VendingMachine[A] {

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
  cafeteria.installVendingMachine(new VendingMachine[SoftDrink])

  // try to take some soft drinks from the vending machine

  // try to supply the vending machine with new soft drinks

  // what if you have a cola vending machine and supply it with TonicWater ?
}




