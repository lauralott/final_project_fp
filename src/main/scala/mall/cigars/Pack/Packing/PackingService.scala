package mall.cigars.Pack.Packing

import akka.actor.Actor
import mall.cigars.Pack.Elaboration._
import mall.cigars.Pack.Product.Cigarettes

case class PackingService() {

  def makeCigaretteBox(count : Int): Seq[Box] ={
    val newBox = Seq()
    for(i <- 1 to count){
      newBox :+ Box(20, Cigarettes(Seq(Paper,  TobaccoLeaf, Nicotine, Tar, Sugar, Arsenic, Filter)))
    }

    newBox
  }

}
