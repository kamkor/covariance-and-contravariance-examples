package kamkor.contravariance.garbagecan

object TrashModel {
  
  class Item
  
  class PlasticItem extends Item
  class PlasticBottle extends PlasticItem
  
  class PaperItem extends Item
  class Newspaper extends PaperItem

}