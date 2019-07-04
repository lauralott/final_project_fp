package mall.cigars.VendingMachine

import mall.Cost
import mall.cigars.Pack.{Pack, PackStock}
import mall.cigars.System.VMSystem

case class VendingMachine(name: String, id : String, packsStocks:Seq[PackStock], profit: Cost=0 ) {

  def buy(i:String): (VendingMachine, Pack )= {
    val packStock : PackStock = getPackStock(i).getOrElse(throw new Exception("not found"))
    val newPackStock = packStock.buy()
    (this.copy(packsStocks = packsStocks.patch(packsStocks.indexOf(packStock), Seq(newPackStock), 1),
      profit = profit + packStock.pack.price), packStock.pack)
  }

  private def getPackStock(name: String) : Option[PackStock] = this.packsStocks.find(p => p.pack.name == name)

  def fillUp() : VendingMachine = {
    this.copy(packsStocks = packsStocks.map(_.refill()))
  }

  def retrieveProfit() : VendingMachine = {
    this.copy(profit = 0)
  }

  def requestStock(units: Int): Unit ={
    VMSystem().initProcess(id, units)
  }

  def needRefill(pack: Pack): Boolean ={
    packsStocks.filter(_.pack == pack).head.count < 5
  }

}
