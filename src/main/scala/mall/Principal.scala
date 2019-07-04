package mall
import java.util.UUID.randomUUID

import mall.cigars.City.{Barcelona, CityMapper, Mallorca}
import mall.cigars.Pack.{CigarettesPack, CigarsPack, PackStock}
import mall.cigars.VendingMachine.{VendingMachine, VendingMachineChain}

object Principal {

  def main (args: Array[String]):Unit= {
    val packCigarettes = "Cigarettes"
    val packCigars = "Cigars"
    val stock = Seq(PackStock(CigarsPack(), 30, 30), PackStock(CigarettesPack(),100, 100))
    val lowStock = Seq(PackStock(CigarsPack(), 10, 30), PackStock(CigarettesPack(),5, 100))
    val uuid = randomUUID().toString
    val vm1 = VendingMachine("vm1", uuid, stock)
    val (vm2, prod) = vm1.copy(name = "vm2", id = randomUUID().toString).buy(packCigarettes)._1.buy(packCigarettes)
    val (vm3, prod3) = vm1.copy(name = "vm3", id = randomUUID().toString).buy(packCigars)._1.buy(packCigarettes)
    val (vm4, prod5) = vm1.copy(name = "vm4", id = randomUUID().toString).buy(packCigars)
    val (vm5, prod4) = vm1.copy(name="vm5", id = randomUUID().toString).buy(packCigarettes)._1.buy(packCigars)
    val (vm6, prod6) = vm1.copy(name="vm6", id = randomUUID().toString, lowStock).buy(packCigarettes)
    val (vm7, prod7) = vm1.copy(name="vm7", id = randomUUID().toString).buy(packCigarettes)._1.buy(packCigars)._1.buy(packCigars)

    val mall_1 = VendingMachineChain("Glories", Seq(vm1,vm6,vm4), jitActivated = true)
    val mall_2 = VendingMachineChain("Maquinista", Seq(vm5,vm2,vm3))
    val mall_3 = VendingMachineChain("Diagonal Mar", Seq(vm7))

    println(s"some of the bought packs: \n $prod \n $prod3 \n $prod4 \n $prod7 \n $prod5 \n $prod6")

    closeDay(mall_1)
    closeDay(mall_2)

    val mapper = CityMapper().add(Barcelona,Seq(mall_1, mall_2)).add(Mallorca,Seq(mall_3))

    println(s"find vending machine by UUID from City panel: ${mapper.getMachine(uuid)}")
    println(s"\nProfit from Barcelona:   ${mapper.getTotalProfit(Barcelona)}€")
    println(s"\nProfit from Mallorca:   ${mapper.getTotalProfit(Mallorca)}€")

    checkStockAndRequestIfNeeded(mall_1)
    checkStockAndRequestIfNeeded(mall_2)
  }

  def closeDay(chain: VendingMachineChain): Unit ={
    println(s"Results of Day 1 for machine ${chain.name}")
    chain.vendingMachines.foreach(m => println(s"${m.name}  ---  profit: ${m.profit}€ --- remaining packs: ${m.packsStocks}  \n"))
    println( s"Total profits: ${chain.getTotalProfit()}€\n" )
    println("Fill up machines for Day 2")
    chain.vendingMachines.foreach(m => println(m.fillUp().retrieveProfit()))
    println("\n")
  }

  def checkStockAndRequestIfNeeded(chain: VendingMachineChain): Unit ={
    if (!chain.checkStockAndRequestIfNeeded())
        println(s"JIT is not implemented for this mall (${chain.name})")
  }


}
