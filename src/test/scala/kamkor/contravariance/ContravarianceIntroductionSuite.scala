package kamkor.covariance

import kamkor.contravariance.garbagecan.TrashModel._
import kamkor.UnitSuite

class ContravarianceIntroductionSuite extends UnitSuite {

  test("Contravariant subtyping using declaration-site variance") {
    /**
     * FIXME
     */
    class GarbageCan[-A] {
      // .. don't worry about implementation yet
    }

    /**
     * FIXME
     */
    def install(plasticBottleGC: GarbageCan[PlasticBottle]): Unit = {
      // Installs the soft drink garbage can
    }

    // contravariant subtyping
    install(new GarbageCan[Plastic])
    install(new GarbageCan[Trash])        

    // Compile error ! covariant subtyping
    //install(new VendingMachine[TonicWater])
    //install(new VendingMachine[AppleJuice])

    /*  To sum up:
     *   
     *                 A  >:                B
     *  VendingMachine[B] <: VendingMachine[A]
     *  
     *  FIXME  
     */
  }

  test("Contravariant subtyping using use-site variance") {
    class GarbageCan[A] {
      // Vending Machine is invariant in type parameter A, so
      // you can use that type parameter however you want.
    }

    /**
     * FIXME
     */
    def install(plasticGC: GarbageCan[_ >: Plastic]): Unit = {
      // Installs soft drink vending machine
    }

    // contravariant subtyping
    install(new GarbageCan[Plastic])
    install(new GarbageCan[Trash])

    // Compile error ! covariant subtyping
    //install(new VendingMachine[PlasticBottle])
  }

}
