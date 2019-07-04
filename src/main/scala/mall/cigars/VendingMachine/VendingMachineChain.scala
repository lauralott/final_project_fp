package mall.cigars.VendingMachine

import mall.Cost
import mall.cigars.Pack.CigarettesPack
import mall.utils.SequenceUtils

case class VendingMachineChain(name : String, vendingMachines: Seq[VendingMachine],
                               jitActivated: Boolean = false) {

  def add(i:VendingMachine):VendingMachineChain = {
    this.copy(vendingMachines=this.vendingMachines :+ i)
  }

  def remove(i:VendingMachine): VendingMachineChain = {
    val newVM = SequenceUtils.removeElement(this.vendingMachines, i)
    this.copy(vendingMachines = newVM)
  }

  def getTotalProfit(): Cost ={
    vendingMachines.map(_.profit).sum
  }

  def getMachine(id: String): Option[VendingMachine] = vendingMachines.find(_.id == id)

  def checkStockAndRequestIfNeeded(): Boolean ={
    if (jitActivated){
      vendingMachines.foreach(vm => vm.needRefill(CigarettesPack()) -> vm.requestStock(100))
      true
    }else
      false
  }
}
