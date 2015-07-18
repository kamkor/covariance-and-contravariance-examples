package kamkor.covariance

import kamkor.UnitSuite
import kamkor.covariance.gun.AmmoMagazine
import kamkor.covariance.gun.BulletsModel._
import kamkor.covariance.gun.Gun

class AmmoMagazineExample extends UnitSuite {

  /* Covariant type parameter can be used in a mutable field if you set scope of this
   * field to private[this]. Let me explain with quote from Programming In Scala:
   *
   * Object private members can be accessed only from within the object in
   * which they are defined. It turns out that accesses to variables from the
   * same object in which they are defined do not cause problems with variance.
   * The intuitive explanation is that, in order to construct a case where
   * variance would lead to type errors, you need to have a reference to
   * a containing object that has a statically weaker type than the type the
   * object was defined with. For accesses to object private values, however,
   * this is impossible. 
   */
  test("Covariant type parameter in a mutable field") {
    val normalBulletsMag: AmmoMagazine[NormalBullet] = AmmoMagazine.newNormalBulletsMag
    val gun = new Gun(normalBulletsMag)

    gun.shootAllBullets() // should shoot with normal Bullets
    gun.shoot() // should print CLACK CLACK

    val explosiveBulletsMag: AmmoMagazine[ExplosiveBullet] = AmmoMagazine.newExplosiveBulletsMag
    // Because AmmoMagazine is covariant in type parameter A, AmmoMagazine[ExplosiveBullet] can be
    // passed to reload method, which in signature accepts AmmoMagazine[Bullet]
    // Again, this is covariant subtyping: 
    //                 ExplosiveBullet  <:              Bullet
    //    AmmoMagazine[ExplosiveBullet] <: AmmoMagazine[Bullet]
    gun.reload(explosiveBulletsMag)

    gun.shootAllBullets() // should shoot with normal Bullets
    gun.shoot() // should print CLACK CLACK
  }

}