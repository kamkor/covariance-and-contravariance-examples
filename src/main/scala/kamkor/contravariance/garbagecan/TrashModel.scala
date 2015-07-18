package kamkor.contravariance.garbagecan

object TrashModel {
  
  trait Trash
  
  trait Plastic extends Trash
  class PlasticBottle extends Plastic
  
  trait Paper extends Trash
  class Newspaper extends Paper

}