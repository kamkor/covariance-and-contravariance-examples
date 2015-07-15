package kamkor.contravariance

import kamkor.UnitSpec
import kamkor.DrinksModel._

class GarbageCanSpec extends UnitSpec {

  "A GarbageCan" should {

    "accept items of type the same as garbage can type parameter" in {
      val juiceGC: GarbageCan[Juice] = GarbageCan()
      // should compile
      val juice: Juice = new AppleJuice()
      juiceGC.put(juice)
      juiceGC.putAll(List(juice))
    }

    "accept items of type that is a childtype of garbage can type parameter" in {
      val juiceGC: GarbageCan[Juice] = GarbageCan()
      juiceGC.put(new AppleJuice())
      //code below wont compile      
      //val drink: Drink = new Cola()
      //juiceGC.put(drink)
    }

  }

}