package kamkor.contravariance

import kamkor.UnitSpec
import kamkor.contravariance.garbagecan.GarbageCan
import kamkor.contravariance.garbagecan.TrashModel._

class GarbageCanSpec extends UnitSpec {

  "A GarbageCan" should {

    "accept items of type the same as GarbageCan type parameter" in {
      val plasticGC: GarbageCan[Plastic] = GarbageCan()
      // should compile
      val plastic: Plastic = new PlasticBottle()
      plasticGC.put(plastic)
      plasticGC.putAll(List(plastic))
    }

    "accept items of type that is a childtype of garbage can type parameter" in {
      val plasticGC: GarbageCan[Plastic] = GarbageCan()
      // should compile
      val plasticBottle: PlasticBottle = new PlasticBottle()
      plasticGC.put(plasticBottle)
      plasticGC.putAll(List(plasticBottle))
    }
    
    "lets have fun" in {
      val trashGC: GarbageCan[Trash] = GarbageCan()
      var plasticBottleGC: GarbageCan[PlasticBottle] = GarbageCan()
      
      trashGC.put(new Trash)
      trashGC.put(new Newspaper)
      
      plasticBottleGC = trashGC
    }

  }

}