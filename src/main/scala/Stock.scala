import StockName.StockName

case class Stock(name: StockName, quantity: Int) {

  def increase(number: Int): Stock = Stock.apply(name, quantity+number)
  def decrease(number: Int): Stock = Stock.apply(name, quantity-number)

}
