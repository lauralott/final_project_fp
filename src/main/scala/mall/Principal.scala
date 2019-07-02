package mall

import mall.cigars.{Cigarettes, Cigars, PackStock, VendingMachine, VendingMachineChain}

object Principal {

  def main (args: Array[String]):Unit= {

    val packCigarettes = "Cigarettes"
    val packCigars = "Cigars"
    val stock = Seq(PackStock(Cigars(), 30), PackStock(Cigarettes(),100))

    val vm1 = VendingMachine("vm1",stock)
    val vm2 = vm1.copy(name = "vm2").buy(packCigarettes).buy(packCigarettes)
    val vm3 = vm1.copy(name = "vm3").buy(packCigars).buy(packCigarettes)
    val vm4 = vm1.copy(name = "vm4").buy(packCigars)

    val vmChain = VendingMachineChain( Seq(vm1,vm2,vm3,vm4))

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
