package kamkor.covariance.vendingmachine

object DrinksModel {

  trait Drink

  trait Juice extends Drink
  class OrangeJuice extends Juice
  class AppleJuice extends Juice

  trait SoftDrink extends Drink
  class Cola extends SoftDrink
  class TonicWater extends SoftDrink

}
