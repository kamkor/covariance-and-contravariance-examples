package kamkor.covariance

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
     * Accepts a Garbage Can of type Plastic or supertypes of Plastic.
     */
    def installPlasticGarbageCan(gc: GarbageCan[Plastic]): Unit = {
      // Installs garbage can for plastic trash
    }

    /**
     * Accepts a Garbage Can of type Paper or supertypes of Paper.
     */
    def installPaperGarbageCan(gc: GarbageCan[Paper]): Unit = {
      // Installs garbage can for paper trash
    }

    // contravariant subtyping
    installPlasticGarbageCan(new GarbageCan[Trash])
    installPaperGarbageCan(new GarbageCan[Trash])

    // invariant
    installPaperGarbageCan(new GarbageCan[Paper])

    // Compile error ! covariant subtyping
    //installPaperGarbageCan(new GarbageCan[Newspaper])
  }

  test("Contravariant subtyping using use-site variance") {
    class GarbageCan[A] {
      // Garbage Can is invariant in type parameter A, so
      // you can use that type parameter however you want.
    }

    def installPlasticGarbageCan(gc: GarbageCan[_ >: Plastic]): Unit = {
      // Installs garbage can for plastic trash
    }

    def installPaperGarbageCan(gc: GarbageCan[_ >: Paper]): Unit = {
      // Installs garbage can for paper trash
    }

    // contravariant subtyping
    installPlasticGarbageCan(new GarbageCan[Trash])
    installPaperGarbageCan(new GarbageCan[Trash])

    // invariant
    installPaperGarbageCan(new GarbageCan[Paper])

    // Compile error ! covariant subtyping
    //installPlasticGarbageCan(new GarbageCan[PlasticBottle])
  }

}
