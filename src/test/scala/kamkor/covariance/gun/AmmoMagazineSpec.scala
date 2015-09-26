package kamkor.covariance.gun

import kamkor.UnitSpec
import kamkor.covariance.gun.BulletsModel._


class AmmoMagazineSpec extends UnitSpec {

  "A AmmoMagazine" when {
    "has no bullets" should {
      "not return bullets" in {
        val emptyAmmoMag = AmmoMagazine.empty

        emptyAmmoMag.hasBullets shouldBe false
        emptyAmmoMag.giveNextBullet().isDefined shouldBe false
      }
    }

    "has bullets" should {
      "return bullets until there are no left" in {        
        val ammoMag = AmmoMagazine.newNormalBulletsMag()

        ammoMag.hasBullets shouldBe true
        while (ammoMag.hasBullets) {
          ammoMag.giveNextBullet().isDefined shouldBe true
        }
        ammoMag.giveNextBullet().isDefined shouldBe false
      }
    }

  }

}
