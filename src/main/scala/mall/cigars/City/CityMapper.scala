package mall.cigars.City

import mall.Cost
import mall.cigars.VendingMachine.{VendingMachine, VendingMachineChain}

case class CityMapper(vmByCity:Map[City,Seq[VendingMachineChain]] = Map()) {

  def add(city:City, chains: Seq[VendingMachineChain]): CityMapper = {
    this.copy(vmByCity + vmByCity.get(city).map(city -> _).getOrElse(city -> chains))
  }

  def remove(city:City): CityMapper = {
    val m = vmByCity(city) match {
      case chains:Seq[VendingMachineChain] => vmByCity - city
      case _ => vmByCity
    }
    this.copy(vmByCity = m)
  }

  def getTotalProfit(city:City): Cost = {
    val chains:Seq[VendingMachineChain] =  vmByCity.getOrElse(city,Seq())
    chains.map(c => c.getTotalProfit()).sum
  }

  def getMachine(id: String): Option[VendingMachine] = vmByCity.flatMap(_._2.flatMap(_.getMachine(id))).headOption

}
