package kamkor.variance.exercise.ex3

/**
 * According to new regulations, we must set up in the front yard two garbage cans. One for plastic trash
 * and another for paper trash.
 *
 * Garbage can for plastic costs 10$
 * Garbage can for paper costs 8$
 *
 * Some cheap generic garbage can for any Item, costs only 3$
 *
 * How would you implement GarbageCan to save the most money?
 *
 * Additionally, it must of course be possible to put things into GarbageCan.
 *
 */
class GarbageCan[A] {

}

trait Item

trait PlasticItem extends Item

class PlasticBottle extends PlasticItem

trait PaperItem extends Item

class Newspaper extends PaperItem

class FrontYard {
  def setGarbageCanForPlastic(plasticItemGC: GarbageCan[PlasticItem]): Unit = {

  }

  def setGarbageCanForPaper(paperItemGC: GarbageCan[PaperItem]): Unit = {

  }
}

object GarbageCanExample extends App {
  val frontYard = new FrontYard
  // This costs us 18$ :(
  frontYard.setGarbageCanForPlastic(new GarbageCan[PlasticItem])
  frontYard.setGarbageCanForPaper(new GarbageCan[PaperItem])

  // try to put things into the garbage can
}
