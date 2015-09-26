package kamkor.contravariance.garbagecan

object TrashModel {
  
  trait Item
  
  trait PlasticItem extends Item
  class PlasticBottle extends PlasticItem
  
  trait PaperItem extends Item
  class Newspaper extends PaperItem

}