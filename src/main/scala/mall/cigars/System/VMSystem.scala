package mall.cigars.System

import akka.actor.{ActorRef, ActorSystem, Props}

case class VMSystem() {
  val system : ActorSystem  = ActorSystem("JIT")
  val cloud : ActorRef = system.actorOf(Props[CloudActor], name = "cloud")
  val machine : ActorRef = system.actorOf(Props(new MallActor(cloud)), name = "mall")

  def initProcess(machineId : String, packCount : Int) ={
    machine ! InitRequest(machineId, packCount)
  }



}
