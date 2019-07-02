package mall

import mall.cigars.{Cigarettes, Cigars, PackStock, VendingMachine, VendingMachineChain}

object Principal {

  def main (args: Array[String]):Unit= {

    val packCigarettes = "Cigarettes"
    val packCigars = "Cigars"
    val stock = Seq(PackStock(Cigars(), 30), PackStock(Cigarettes(),100))

    val vm1 = VendingMachine("vm1",stock)
    val (vm2, prod) = vm1.copy(name = "vm2").buy(packCigarettes)
    val (vm21, prod2) = vm2.buy(packCigarettes)
    val (vm3, prod3) = vm1.copy(name = "vm3").buy(packCigars)
    val (vm31, prod4) = vm3.buy(packCigarettes)
    val (vm4, prod5) = vm1.copy(name = "vm4").buy(packCigars)

    val vmChain = VendingMachineChain( Seq(vm1,vm21,vm31,vm4))

    println(s"bought packs: \n $prod \n $prod2 \n $prod3 \n $prod4 \n $prod5")

    closeDay(vmChain)

  }

  def closeDay(chain: VendingMachineChain): Unit ={
    println("Results of Day 1")
    chain.vendingMachines.foreach(m => println(s"${m.name}  ---  profit: ${m.profit} --- remaining packs: ${m.packsStocks}  \n"))
    println( s"Total profits: ${chain.getTotalProfit()}\n" )
    println("Fill up machines for Day 2")
    chain.vendingMachines.foreach(m => println(m.fillUp().retrieveProfit()))
  }


}
