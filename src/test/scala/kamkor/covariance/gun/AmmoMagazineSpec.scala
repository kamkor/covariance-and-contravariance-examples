package kamkor.covariance.vendingmachine

import kamkor.UnitSpec
import kamkor.covariance.gun.BulletsModel._
import kamkor.covariance.gun.AmmoMagazine

class AmmoMagazineSpec extends UnitSpec {

  "A AmmoMagazine" when {

    "empty" should {
      "not give bullets" in {
        val ammoMag = new AmmoMagazine(List.empty)
        ammoMag.isEmpty shouldBe true
        ammoMag.giveNextBullet.isDefined shouldBe false
      }
    }

    "not empty" should {
      "give bullets" in {
        val bullets = List.range(0, 10) map (i => new NormalBullet)
        val ammoMag = new AmmoMagazine(bullets)
        for (_ <- 0 until 10) {
          ammoMag.isEmpty shouldBe false
          ammoMag.giveNextBullet.isDefined shouldBe true
        }
        ammoMag.isEmpty shouldBe true
        ammoMag.giveNextBullet.isDefined shouldBe false
      }
    }

  }

}