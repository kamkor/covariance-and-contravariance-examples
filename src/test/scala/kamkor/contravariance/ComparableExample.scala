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
  case class Piano(val productionYear: Int) extends MusicInstrument

  test("test Comparable") {
    val guitar: Guitar = new Guitar(1950)
    val piano: Piano = new Piano(1950)
    
    Comparable.equal(guitar, piano)
  }

}
