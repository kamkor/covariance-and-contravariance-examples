package kamkor.contravariance.garbagecan

import kamkor.UnitSpec
import kamkor.contravariance.garbagecan.TrashModel._

class GarbageCanSpec extends UnitSpec {

  "A GarbageCan" should {

    "consume plastic items" in {
      val plasticGC: GarbageCan[PlasticItem] = new GarbageCan()
      // should compile      
      plasticGC.put(new PlasticBottle())
      plasticGC.putAll(List(new PlasticBottle(), new PlasticBottle()))

      plasticGC.itemsCount shouldBe 3
    }

  }

}