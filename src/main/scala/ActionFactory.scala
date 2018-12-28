
class ActionFactory {
  def get(input: String): Action = {
    val s = input.split("\t")
    s(1) match {
      case "s" =>
        new Sell(s(0), Order.apply(StockName.withName(s(2)), Integer.valueOf(s(3)), Integer.valueOf(s(4))))
      case "b" =>
        new Buy(s(0), Order.apply(StockName.withName(s(2)), Integer.valueOf(s(3)), Integer.valueOf(s(4))))
      case _ =>
        throw new IllegalArgumentException
    }
  }
}

