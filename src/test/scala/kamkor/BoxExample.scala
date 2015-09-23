package kamkor

import kamkor.covariance.vendingmachine.DrinksModel._

class BoxExample extends UnitSuite {

  class Box[A]() {
    private var _thing: A = _

    def retrieve: A = _thing

    // explicit setter for the sake of example    
    def put(thing: A) = this._thing = thing
  }

  def setInRoom[A <: SoftDrink](softDrinkBox: Box[A]): Unit = {
    val s: SoftDrink = new Cola
    val s2: SoftDrink = softDrinkBox.retrieve
  }

  test("invariant box") {
    val box: Box[SoftDrink] = new Box()
    box.put(new Cola)
    val thing: SoftDrink = box.retrieve
  }

  test("covariant box") {
    val box: Box[_ <: SoftDrink] = new Box()
    // type mismatch; found : Cola required: _$4
    // box.put(new Cola)
    val thing: SoftDrink = box.retrieve
  }

  test("contravariant box") {
    val box: Box[_ >: SoftDrink] = new Box()
    box.put(new Cola)
    // type mismatch; found : _$2 required: SoftDrink    
    // val thing: SoftDrink = box.retrieve
  }

}