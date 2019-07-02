package mall.cigars
import mall.Cost

sealed trait Pack{ def name: String; def price : Cost; def maxCount: Int}
case class Cigarettes( name : String = "Cigarettes", price : Cost = 1.5, maxCount : Int = 100) extends Pack
case class Cigars( name: String = "Cigars", price : Cost = 0.9, maxCount : Int = 30) extends Pack
