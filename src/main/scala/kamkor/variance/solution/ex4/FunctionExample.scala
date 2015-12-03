package kamkor.variance.solution.ex4

trait MusicInstrument {
  val productionYear: Int
}

case class Guitar(productionYear: Int) extends MusicInstrument

case class Piano(productionYear: Int) extends MusicInstrument

object FunctionExample extends App {

  // implement function that can be used to filter List[Guitar], List[Piano]
  // and in general of any subtype of MusicInstrument

  val isVintage: (MusicInstrument) => Boolean = _.productionYear < 1978

  val guitars = List(new Guitar(1977), new Guitar(2005), new Guitar(1965))
  val pianos = List(new Piano(1950), new Piano(2010), new Piano(1977))

  println("Vintage guitars and pianos:")

  // below code compiles, because Function1 is contravariant in its input type parameter
  // Function1[-T, +R]
  // we can pass isVintage to filter of both Lists, because we use property called contravariant subtyping
  println(guitars.filter(isVintage).mkString(","))
  println(pianos.filter(isVintage).mkString(","))

  println("Vintage guitars and pianos:")
  // if Function1 wasn't contravariant in its input type parameter then we would have to implement two isVintage functions:
  val isVintageGuitar: (Guitar) => Boolean = _.productionYear < 1978
  val isVintagePiano: (Piano) => Boolean = _.productionYear < 1978
  println(guitars.filter(isVintageGuitar).mkString(","))
  println(pianos.filter(isVintagePiano).mkString(","))

}
