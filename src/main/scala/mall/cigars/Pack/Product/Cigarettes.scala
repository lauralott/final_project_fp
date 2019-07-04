package mall.cigars.Pack.Product

import mall.cigars.Pack.Elaboration.Ingredients
import mall.utils.SequenceUtils

case class Cigarettes(ingredients: Seq[Ingredients]) extends  Product {

  def addIngrediente(i:Ingredients): Cigarettes = {
    this.copy(ingredients=this.ingredients :+ i)
  }

  def removeIngrediente(i:Ingredients): Cigarettes = {
    val newIngredients = SequenceUtils.removeElement(this.ingredients, i)
    this.copy(ingredients = newIngredients)
  }

  def resetIngredientes() : Cigarettes = {
    this.copy(ingredients=Seq[Ingredients]())
  }
}
