package mall.cigars.Pack

case class PackStock(pack: Pack, count:Int, maxCount : Int){
  def buy(): PackStock = count match {
    case 0 => throw new RuntimeException("no units available")
    case _ => this.copy(count = count - 1)
  }

  def refill() : PackStock = this.copy(count = maxCount)
}
