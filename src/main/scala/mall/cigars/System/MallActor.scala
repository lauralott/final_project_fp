package mall.cigars.System

import akka.actor.{Actor, ActorRef}

class MallActor(cloud: ActorRef) extends Actor {
  override def receive: Receive = {
    case InitRequest(machineId,packUnits) =>
      println(s"sending request to Cloud from machine $machineId")
      cloud ! StartElaborationAndPacking(machineId, packUnits)
    case SuccessfullyEnded(machineId) =>
      println(s"Order by $machineId received!")
      context.stop(self)
  }
}
