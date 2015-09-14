package kamkor.covariance.vendingmachine

import kamkor.UnitSpec
import kamkor.covariance.gun.BulletsModel._
import kamkor.covariance.gun.AmmoMagazine
import kamkor.covariance.gun.Gun

class GunSpec extends UnitSpec {

  "A Gun" should {
    "accept AmmoMagazine of any subtype of Bullet" in {      
      // should compile, covariant subtyping      
      val gun = new Gun(AmmoMagazine.newNormalBulletsMag)
      gun.reload(AmmoMagazine.newExplosiveBulletsMag)
    }
  }

  "A Gun with no ammo" when {
    val emptyAmmoMag = new AmmoMagazine(List.empty)

    "attempting to shoot" should {
      "not succeed" in {
        val gun = new Gun(emptyAmmoMag)

        gun.hasAmmo() shouldBe false
        gun.shoot().isDefined shouldBe false
      }
    }

    "reloaded with ammo" should {
      "shoot with bullets until no ammo left" in {
        val gun = new Gun(emptyAmmoMag)
        gun.reload(AmmoMagazine.newExplosiveBulletsMag)

        gun.hasAmmo() shouldBe true
        while (gun.hasAmmo()) {
          gun.shoot().isDefined shouldBe true
        }
        gun.shoot().isDefined shouldBe false
      }
    }
  }

}
