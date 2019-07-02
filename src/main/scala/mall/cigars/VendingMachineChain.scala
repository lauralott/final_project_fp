package mall.cigars

import mall.Cost
import mall.utils.SequenceUtils

case class VendingMachineChain(vendingMachines: Seq[VendingMachine]) {

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


}
