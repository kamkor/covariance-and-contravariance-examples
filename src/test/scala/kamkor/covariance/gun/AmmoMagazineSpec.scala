package kamkor.covariance.vendingmachine

import kamkor.UnitSpec
import kamkor.covariance.gun.BulletsModel._
import kamkor.covariance.gun.AmmoMagazine

class AmmoMagazineSpec extends UnitSpec {

  "A AmmoMagazine" when {
    "has no bullets" should {
      "not return bullets" in {
        val ammoMag = new AmmoMagazine(List.empty)

        ammoMag.hasBullets shouldBe false
        ammoMag.giveNextBullet().isDefined shouldBe false
      }
    }

    "has bullets" should {
      "return bullets until there are no left" in {
        val bullets = List.range(0, 10) map (i => new NormalBullet)
        val ammoMag = new AmmoMagazine(bullets)

        ammoMag.hasBullets shouldBe true
        while (ammoMag.hasBullets) {
          ammoMag.giveNextBullet().isDefined shouldBe true
        }
        ammoMag.giveNextBullet().isDefined shouldBe false
      }
    }

  }

}
