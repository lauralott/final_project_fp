package mall.cigars.System

case class InitRequest(machineId: String, packUnits: Int)
case class StartElaborationAndPacking(machineId: String, packUnits: Int)
case class SuccessfullyEnded(machineId: String)

