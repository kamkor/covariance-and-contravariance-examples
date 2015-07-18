package kamkor.covariance.gun

object BulletsModel {

  trait Bullet

  class NormalBullet extends Bullet
  class ExplosiveBullet extends Bullet

}