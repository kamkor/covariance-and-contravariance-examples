package kamkor.variance.exercise.ex4


trait MusicInstrument {
  val productionYear: Int
}
case class Guitar(productionYear: Int) extends MusicInstrument
case class Piano(productionYear: Int) extends MusicInstrument

object FunctionExample extends App {

  // implement function that can be used to filter List[Guitar], List[Piano]
  // and in general of any subtype of MusicInstrument

  //val isVintage = ???

  val guitars = List(new Guitar(1977), new Guitar(2005))
  val pianos = List(new Piano(1950), new Piano(2010))

  println("Vintage guitars and pianos:")
  //guitars.filter(isVintage).foreach(println)
  //pianos.filter(isVintage).foreach(println)

}
