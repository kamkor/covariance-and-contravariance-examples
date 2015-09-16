package kamkor.covariance

import kamkor.UnitSuite
import kamkor.covariance.vendingmachine.DrinksModel._

class FunctionExample extends UnitSuite {

  trait MusicInstrument {
    val productionYear: Int
  }
  case class Guitar(val productionYear: Int) extends MusicInstrument
  case class Piano(val productionYear: Int) extends MusicInstrument

  val isVintage: MusicInstrument => Boolean = _.productionYear < 1980

  test("should filter vintage guitars") {
    // given
    val guitars: List[Guitar] = List(new Guitar(1966), new Guitar(1988))
    // when
    val vintageGuitars: List[Guitar] = guitars.filter(isVintage)
    // then
    assert(List(new Guitar(1966)) === vintageGuitars)
  }

  test("should filter vintage pianos") {
    // given
    val pianos: List[Piano] = List(new Piano(1975), new Piano(1985))
    // when
    val vintagePianos: List[Piano] = pianos.filter(isVintage)
    // then
    assert(List(new Piano(1975)) === vintagePianos)
  }

}