package kamkor.variance.solution.ex3

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
// We can save money if we use contravariant subtyping. That is, instead of buying special plastic and paper garbage cans,
// we can buy generic trash garbage cans and label them with "plastic" and "paper" trash.
// To add this property, we must add - prefix to type parameter A. This makes GarbageCan contravariant in its type parameter A.
class GarbageCan[-A] {

  // when using object private scope, that is, private[this], we can use contravariant parameter in this field however we want.
  // there are no restrictions. The same rule applies for covariant type parameter. Only 'this' object can directly affect
  // items field, so for Scala compiler, this is always safe.
  private[this] var items: List[A] = List.empty

  // method argument type (input type of GarbageCan) is natural position of contravariant type parameter
  def put(item: A): Unit = items = item :: items

  // method argument type (input type of GarbageCan) is natural position of contravariant type parameter
  // However, there are exceptions. Like when you use parametrized type like List:
  // - ParametrizedType[+A] is OK
  // - ParametrizedType[A] is NOT OK
  // - ParametrizedType[-A] is NOT OK
  def putAll(newItems: List[A]): Unit = items = items ++ newItems

  def itemsCount: Int = items.size

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

  // This costs us 6$ :)
  // contravariant subtyping, works because of - prefix, that is GarbageCan[-A]
  frontYard.setGarbageCanForPlastic(new GarbageCan[Item])
  frontYard.setGarbageCanForPaper(new GarbageCan[Item])

  // try to put things into the garbage can
  val plasticGC: GarbageCan[PlasticItem] = new GarbageCan[Item]

  plasticGC.put(new PlasticBottle)
  plasticGC.putAll(List(new PlasticBottle, new PlasticBottle))

  println(plasticGC.itemsCount)

}
