import StockName.StockName

trait TCustomer {
  def sell(order: Order): Customer
  def buy(order: Order): Customer
  def patBuy(order: Order): Customer
  def getName: String
  def getBalance: Int
  def getStock(name: StockName): Int

}
