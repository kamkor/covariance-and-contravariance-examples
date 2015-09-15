package kamkor.covariance.gun

import kamkor.covariance.gun.BulletsModel._

final class Gun(private var ammoMag: AmmoMagazine[Bullet]) {

  def reload(ammoMag: AmmoMagazine[Bullet]): Unit = this.ammoMag = ammoMag

  def hasAmmo: Boolean = ammoMag.hasBullets

  /** Returns Bullet that was shoot or None if there is no ammo left */
  def shoot(): Option[Bullet] = ammoMag.giveNextBullet()

}
