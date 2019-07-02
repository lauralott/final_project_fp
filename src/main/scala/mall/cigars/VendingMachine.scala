package mall.cigars
import mall.Cost

case class VendingMachine(name: String, packsStocks:Seq[PackStock], profit: Cost=0 ) {

  def buy(i:String): VendingMachine = {
    val packStock : PackStock = getPackStock(i).getOrElse(throw new Exception("not found"))
    val newPackStock = packStock.buy()
    this.copy(packsStocks = packsStocks.patch(packsStocks.indexOf(packStock), Seq(newPackStock), 1),
      profit = profit + packStock.pack.price)
  }

  private def getPackStock(name: String) : Option[PackStock] = this.packsStocks.find(p => p.pack.name == name)

  def fillUp() : VendingMachine = {
    this.copy(packsStocks = packsStocks.map(_.refill()))
  }

  def retrieveProfit() : VendingMachine = {
    this.copy(profit = 0)
  }
}