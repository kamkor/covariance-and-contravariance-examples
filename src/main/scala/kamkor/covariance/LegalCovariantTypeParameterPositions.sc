package kamkor.covariance

object LegalCovariantTypeParameterPositions {
  class Contr[-A] {}
  class Inv[A] {}

  class Covar[+A](
    val field1: A,
    val field2: Covar[A],
    private[this] var mutableField1: A,
    private[this] var mutableField2: Covar[A],
    private[this] var mutableField3: Contr[A],
    private[this] var mutableField4: Inv[A],
    // possibly mutable, because Contr type parameter has
    // contravariance annotation (prefix -)
    private[this] val mutableField5: Contr[A],
    // possibly mutable, because Inv type parameter has no variance annotation
    private[this] val mutableField6: Inv[A],
    constructorParam1: A,
    constructorParam2: Covar[A]) {

    def produce1(): A = { ??? }
    def produce2(): Covar[A] = { ??? }

    // Must use type parameter with lower bound
    // All methods can return Covar[B], Contr[B] or Inv[B]
    def consume1[B >: A](arg: B): Covar[B] = { ??? }
    def consume2[B >: A](arg: Covar[B]): Covar[B] = { ??? }
    def consume3[B >: A](arg: Contr[B]): Covar[B] = { ??? }
    def consume4[B >: A](arg: Inv[B]): Covar[B] = { ??? }

    // flipped classification
    def consume5(arg: Contr[A]): Covar[A] = { ??? }
  }
}