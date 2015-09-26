package kamkor.covariance.gun

import kamkor.covariance.gun.BulletsModel._

object AmmoMagazine {
  
  def empty[A <: Bullet]: AmmoMagazine[A] = new AmmoMagazine(List.empty)

  def newNormalBulletsMag(): AmmoMagazine[NormalBullet] = {
    val bullets = List.range(0, 10) map (_ => new NormalBullet)
    new AmmoMagazine(bullets)
  }

  def newExplosiveBulletsMag(): AmmoMagazine[ExplosiveBullet] = {
    val explosiveBullets = List.range(0, 10) map (_ => new ExplosiveBullet)
    new AmmoMagazine(explosiveBullets)
  }
  
}

/*
 * Covariant type parameter used in a mutable field. Compiles only because
 * of private[this] scope. If you remove [this], code below will not compile.
 */
final class AmmoMagazine[+A <: Bullet](private[this] var bullets: List[A]) {

  def hasBullets: Boolean = !bullets.isEmpty

  def giveNextBullet(): Option[A] =
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
