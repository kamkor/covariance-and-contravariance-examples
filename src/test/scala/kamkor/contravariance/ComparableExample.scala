package kamkor.contravariance

import kamkor.UnitSuite
import kamkor.contravariance.garbagecan.TrashModel.Item
import kamkor.contravariance.garbagecan.TrashModel.PlasticItem

class ComparableExample extends UnitSuite {

  trait Comparable[-A] {
    def compareTo(that: A): Int
  }

  object Comparable {
    def equal[A <: Comparable[A]](a: A, b: A): Boolean = a.compareTo(b) == 0
  }

  trait MusicInstrument extends Comparable[MusicInstrument] {
    val productionYear: Int
    def compareTo(that: MusicInstrument): Int = productionYear - that.productionYear
  }
  case class Guitar(val productionYear: Int) extends MusicInstrument

  test("test contravariant Comparable") {
    // Compiles, because Comparable is contravariant in its type parameter.
    Comparable.equal(new Guitar(1950), new Guitar(1950)) shouldBe true

    // Comparable.equal expects A <: Comparable[A] and in this case Guitar <: Comparable[Guitar],
    // However, what it gets is MusicInstrument <: Comparable[MusicInstrument]. 
    // 
    // This is fine because of contravariant subtyping:
    // 
    //            Guitar           <:            MusicInstrument
    // Comparable[MusicInstrument] <: Comparable[Guitar]   
  }

}
