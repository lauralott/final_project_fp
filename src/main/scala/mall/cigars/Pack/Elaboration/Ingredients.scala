package mall.cigars.Pack.Elaboration

sealed trait Ingredients

case object Paper extends Ingredients
case object TobaccoLeaf extends Ingredients
case object Nicotine extends Ingredients
case object Tar extends Ingredients
case object Sugar extends Ingredients
case object Arsenic extends Ingredients
case object Filter extends Ingredients