package mall.cigars.Pack

import mall.Cost

sealed trait Pack{ def name: String; def price : Cost}
case class CigarettesPack(name : String = "Cigarettes", price : Cost = 1.5) extends Pack
case class CigarsPack(name: String = "Cigars", price : Cost = 0.9) extends Pack
