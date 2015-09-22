package kamkor.contravariance

import kamkor.contravariance.garbagecan.TrashModel._
import kamkor.UnitSuite

class ContravarianceIntroductionSuite extends UnitSuite {

  test("Contravariant subtyping using declaration-site variance") {

    /**
     * Prefixing a formal type parameter with a - indicates that subtyping
     * is contravariant in that parameter. Alternatively, we can say that class
     * GarbageCan is contravariant in its type parameter A.
     */
    class GarbageCan[-A] {
      // .. don't worry about implementation yet
    }

    /**
     * Accepts a Garbage Can of type PlasticItemItem or supertypes of PlasticItemItem.
     */
    def setGarbageCanForPlastic(plasticItemsGC: GarbageCan[PlasticItem]): Unit = {
      // Installs garbage can for PlasticItem trash
    }

    // contravariant subtyping
    setGarbageCanForPlastic(new GarbageCan[Item])

    // invariant
    setGarbageCanForPlastic(new GarbageCan[PlasticItem])

    // Compile error ! covariant subtyping
    //setGarbageCanForPlastic(new GarbageCan[PlasticBottle])
  }

  test("Contravariant subtyping using use-site variance") {
    class GarbageCan[A] {
      // Garbage Can is invariant in type parameter A, so
      // you can use that type parameter however you want.
    }

    def setGarbageCanForPlastic(plasticItemsGC: GarbageCan[_ >: PlasticItem]): Unit = {
      // Installs garbage can for PlasticItem trash
    }

    // contravariant subtyping
    setGarbageCanForPlastic(new GarbageCan[Item])

    // invariant
    setGarbageCanForPlastic(new GarbageCan[PlasticItem])

    // Compile error ! covariant subtyping
    //setGarbageCanForPlastic(new GarbageCan[PlasticBottle])
  }

}
