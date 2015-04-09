package kamkor.covariance

import kamkor.DrinksModel.Cola
import kamkor.DrinksModel.SoftDrink
import kamkor.DrinksModel.TonicWater
import kamkor.UnitSuite

class CovarianceIntroductionSuite extends UnitSuite {

  test("Covariant subtyping using declaration-site variance") {
    /**
     * Prefixing a formal type parameter with a + indicates that subtyping
     * is covariant in that parameter. Alternatively, we can say that class
     * VendingMachine is covariant in its type parameter A.
     */
    class VendingMachine[+A] {
      // .. don't worry about implementation yet
    }

    /**
     * Accepts a Vending Machine of type SoftDrink or subtypes of SoftDrink
     * (Cola or TonicWater).
     */
    def install(softDrinkVM: VendingMachine[SoftDrink]): Unit = {
      // Installs the soft drink vending machine
    }

    // covariant subtyping
    install(new VendingMachine[Cola])
    install(new VendingMachine[TonicWater])
    // invariant
    install(new VendingMachine[SoftDrink])

    // Compile error ! contravariant subtyping
    // install(new VendingMachine[Drink])

    /*  To sum up:
     *   
     *                 A  <:                B
     *  VendingMachine[A] <: VendingMachine[B]
     *  
     *  If A is a subtype of B then VendingMachine[A] should be a subtype 
     *  of VendingMachine[B]. This property is called covariant subtyping.
     */
  }

  test("Covariant subtyping using use-site variance") {
    class VendingMachine[A] {
      // Vending Machine is invariant in type parameter A, so
      // you can that type parameter however you want.
    }

    /**
     * Use-site covariance using bounds. Accepts a Vending Machine
     *  of type SoftDrink or subtypes of SoftDrink (Cola or TonicWater).
     */
    def install(vendingMachine: VendingMachine[_ <: SoftDrink]): Unit = {
      // Installs soft drink vending machine
    }

    install(new VendingMachine[Cola])
    install(new VendingMachine[TonicWater])
    install(new VendingMachine[SoftDrink])

    // Compile error ! contravariant subtyping
    // install(new VendingMachine[Drink])
  }

  test("You can't directly use covariant type parameter as a type of a method argument") {
    fail("todo")
  }

  test("If you use a lower bound for a type parameter, you can use it as a type of a method argument") {
    fail("todo")
  }

  test("Under most circumstances, you can't use a covariant type parameter in a mutable field") {
    fail("todo")
  }

  test("You can use covariant type parameter in a mutable field if you set scope of this field to private[this]") {
    fail("todo")
  }

}
