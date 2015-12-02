package kamkor.variance.exercise.ex5

import kamkor.variance.exercise.ex2.{Cola, SoftDrink}

class Publisher[A] {

  private var subscribers: List[Subscriber[A]] = List.empty

  def subscribe(subscriber: Subscriber[A]): Unit =  {
    this.subscribers = subscriber :: subscribers
    println(s"total amount of subscribers is ${subscribers.size}")
  }

}

class Subscriber[A] {

  def onNext(el: A): Unit = {

  }

}


object PublisherSubscriberExample extends App {

  val publisher: Publisher[Cola] = new Publisher[Cola];

  publisher.subscribe(new Subscriber[Cola])

  // But somewhere we already have cool subscriber of type Subscriber[SoftDrink]..
  // It would be cool if we could use it instead of implementing the new specific Subscriber[Cola]
  val softDrinkSubscriber: Subscriber[SoftDrink] = new Subscriber[SoftDrink]
  //publisher.subscribe(softDrinkSubscriber)

  // If you managed to get commented code above to compile, please try to explain the reason why it compiles
}
