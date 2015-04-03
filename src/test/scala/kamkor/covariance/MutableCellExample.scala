package kamkor.covariance

import kamkor.UnitSpec

class MutableCellExample extends UnitSpec {

  // From Programming in Scala:
  //
  // Scala compiler will check any variance annotations (+ and -)
  // you place on type parameters. For example, if you try to declare
  // a type parameter to be covariant (by adding a +), but that could
  // lead to potential runtime errors, your program won't compile.
  test("Covariance and mutable types don't fit well together") {

    class Cell[T](init: T) {
      private[this] var current = init
      def get = current
      def set(x: T) = { current = x }
    }

    val c1 = new Cell[String]("abc")

    // Compile error!! Because Cell is invariant in type parameter T.
    //val c2: Cell[Any] = c1

    // If Cell was defined as covariant in type parameter T, ie.
    // Cell[+T], you could assign c1 to c2, but then the line below would
    // produce a runtime exception. Reason is, that c2 is declared as Cell[Any],
    // so you could pass an Int value to the set method, but then it would have thrown
    // runtime exception, because c2 is actually an instance of Cell[String].
    //
    //c2.set(1)

    // To summarize, covariance and mutable types don't fit well together.
    // Scala compiler prevents you from using covariant type parameter in positions
    // that could lead to potential runtime exception.
  }

}
