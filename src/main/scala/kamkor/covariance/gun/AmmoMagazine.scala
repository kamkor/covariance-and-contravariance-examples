package kamkor.covariance.gun

import kamkor.covariance.gun.BulletsModel._

object AmmoMagazine {
  def newNormalBulletsMag: AmmoMagazine[NormalBullet] = {
    val bullets = List.range(0, 10) map (i => new NormalBullet)
    new AmmoMagazine(bullets)
  }

  def newExplosiveBulletsMag: AmmoMagazine[ExplosiveBullet] = {
    val explosiveBullets = List.range(0, 10) map (i => new ExplosiveBullet)
    new AmmoMagazine(explosiveBullets)
  }
}

/*
 * Covariant type parameter used in a mutable field, works only because
 * of private[this] scope. If you remove [this], code below will not
 * compile.
 */
class AmmoMagazine[+A <: Bullet](private[this] var bullets: List[A]) {

  def isEmpty: Boolean = bullets.isEmpty

  def giveNextBullet: Option[A] =
    bullets match {
      case Nil => {
        None
      }
      case t :: ts => {
        bullets = ts
        Some(t)
      }
    }

}
