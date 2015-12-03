package kamkor.variance.solution.ex5

import kamkor.variance.exercise.ex2.{Cola, SoftDrink}

// #1 Make publisher covariant, so you can use covariant subtyping for Publishers that is, Publisher[Cola] <: Publisher[SoftDrink]
class Publisher[+A] {

  // #2 mutable field has a setter method:
  // def subscribers_= (subscribers: A):Unit = _subscribers = subscribers
  // As was mentioned before, method argument type (input type of Publisher) is illegal position of covariant parameter,
  // because it could lead to potential runtime errors. We can overcome this with object private scope as explained before.
  private[this] var subscribers: List[Subscriber[A]] = List.empty

  // #4 After adding #3, subscribe method will start to compile
  // Normally it is not allowed to use covariant type parameter as method argument type, but there are exceptions.
  // One of the exceptions is when you use other parametrized type, like Subscriber etc. It compiles in only one case:
  // - ParametrizedType[+A] is NOT OK
  // - ParametrizedType[A] is NOT OK
  // - ParametrizedType[-A] is OK, because flipped classification happens for this case. Read more here: http://www.artima.com/pins1ed/type-parameterization.html
  // Things become more complicated when you do things like Subscriber[Subscriber[A]] etc...
  def subscribe(subscriber: Subscriber[A]): Unit =  {
    this.subscribers = subscriber :: subscribers
    println(s"total amount of subscribers is ${subscribers.size}")
  }

}

// #3 A in subscriber is used as input type, so it can naturally be contravariant
class Subscriber[-A] {

  def onNext(el: A): Unit = {

  }

}


object PublisherSubscriberExample extends App {

  val publisher: Publisher[Cola] = new Publisher[Cola];

  publisher.subscribe(new Subscriber[Cola])

  // But somewhere we already have cool subscriber of type Subscriber[SoftDrink]..
  // It would be cool if we could use it instead of implementing the new specific Subscriber[Cola]
  val softDrinkSubscriber: Subscriber[SoftDrink] = new Subscriber[SoftDrink]
  // contravariant subtyping
  // Cola <: SoftDrink
  // Subscriber[SoftDrink] <: Subscriber[Cola]
  publisher.subscribe(softDrinkSubscriber)
}
