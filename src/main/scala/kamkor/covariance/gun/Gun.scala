package kamkor.covariance.gun

import kamkor.covariance.gun.BulletsModel._

class Gun(private var ammoMag: AmmoMagazine[Bullet]) {

  def reload(ammoMag: AmmoMagazine[Bullet]): Unit = {
    this.ammoMag = ammoMag
  }

  def shoot(): Unit = {
    if (ammoMag.isEmpty) {
      println("CLACK CLACK [no more bullets!]")
    } else {
      println("BAM !!!!! " + ammoMag.giveNextBullet)
    }
  }

  def shootAllBullets(): Unit =
    while (!ammoMag.isEmpty) {
      shoot()
    }

}