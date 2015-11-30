package kamkor.contravariance.garbagecan

import kamkor.contravariance.garbagecan.TrashModel._

class GarbageCan[-A] {

  // compiles because of object private scope
  private[this] var items: List[A] = List.empty

  def put(item: A): Unit = this.items :+= item

  def putAll(items: List[A]): Unit = this.items ++= items

  def itemsCount: Int = this.items.size

}

object GarbageCanExamples extends App {

  class FrontYard {
    def setGarbageCan(plasticItemGC: GarbageCan[PlasticItem]): Unit = {

    }
  }

  def contravariantSubtyping: Unit = {
    val frontYard = new FrontYard

    // contravariant subtyping
    val itemGC: GarbageCan[Item] = new GarbageCan
    frontYard.setGarbageCan(itemGC)

    // invariant
    val plasticItemGC: GarbageCan[PlasticItem] = new GarbageCan
    frontYard.setGarbageCan(plasticItemGC)

    // covariant subtyping, won't compile
    val plasticBottleGC: GarbageCan[PlasticBottle] = new GarbageCan
    //frontYard.setGarbageCan(plasticBottleGC)
  }

  contravariantSubtyping

}
