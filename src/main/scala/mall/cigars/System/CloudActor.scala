package mall.cigars.System

import akka.actor.Actor
import mall.cigars.Pack.Packing.PackingService

class CloudActor() extends Actor{
  override def receive: Receive = {
    case StartElaborationAndPacking(machineId, packUnits) =>
      println(s"Starting elaboration and packing of $packUnits units for machine $machineId")
      PackingService().makeCigaretteBox(packUnits)
      sender ! SuccessfullyEnded(machineId)
      context.stop(self)
  }
}
