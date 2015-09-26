package kamkor.contravariance.garbagecan

import kamkor.UnitSpec
import kamkor.contravariance.garbagecan.TrashModel._

class GarbageCanSpec extends UnitSpec {

  "A GarbageCan" should {

    "accept items of type the same as GarbageCan type parameter" in {
      val plasticGC: GarbageCan[PlasticItem] = GarbageCan()
      // should compile
      plasticGC.put(new PlasticBottle())
      plasticGC.putAll(List(new PlasticBottle()))

      plasticGC.itemsCount shouldBe 2
    }

    "accept items of type that is a childtype of garbage can type parameter" in {
      val plasticGC: GarbageCan[PlasticItem] = GarbageCan()
      // should compile      
      plasticGC.put(new PlasticBottle())
      plasticGC.putAll(List(new PlasticBottle()))

      plasticGC.itemsCount shouldBe 2
    }

  }

}