package kamkor.contravariance.garbagecan

import kamkor.UnitSpec
import kamkor.contravariance.garbagecan.TrashModel._

class GarbageCanSpec extends UnitSpec {

  "A GarbageCan" should {

    "accept items of type the same as GarbageCan type parameter" in {
      val plasticGC: GarbageCan[PlasticItem] = GarbageCan()
      // should compile
      val plastic: PlasticItem = new PlasticBottle()
      plasticGC.put(plastic)
      plasticGC.putAll(List(plastic))
    }

    "accept items of type that is a childtype of garbage can type parameter" in {
      val plasticGC: GarbageCan[PlasticItem] = GarbageCan()
      // should compile
      val plasticBottle: PlasticBottle = new PlasticBottle()
      plasticGC.put(plasticBottle)
      plasticGC.putAll(List(plasticBottle))
    }

  }

}