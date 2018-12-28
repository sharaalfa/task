

import StockName.StockName
case class Customer(name:String, usd: Int, a: Int, b: Int, c: Int, d: Int) extends TCustomer {
  def this(s: Array[String]) {
    this(s(0), Integer.valueOf(s(1)), Integer.valueOf(s(2)), Integer.valueOf(s(3)), Integer.valueOf(s(4)), Integer.valueOf(s(5)))
  }

  def this(data: String) {
    this(data.split("\t"))
  }


  override def getStock(name: StockName): Int = name match {
    case StockName.A => this.a
    case StockName.B => this.b
    case StockName.C => this.c
    case StockName.D => this.d

  }
  override def getName: String = name
  override def getBalance: Int = usd

  override def patBuy(order: Order): Customer = order.stockName match {
    case StockName.A => Customer(name, 0, Stock(StockName.A, a).increase(usd/order.price).quantity, b, c, d)
    case StockName.B =>  Customer(name, 0, a, Stock(StockName.B, b).increase(usd/order.price).quantity, c, d)
    case StockName.C =>  Customer(name, 0, a, b, Stock(StockName.C, c).increase(usd/order.price).quantity, d)
    case StockName.D =>  Customer(name, 0, a, b, c, Stock(StockName.D, d).increase(usd/order.price).quantity)

  }
  override def buy(order: Order): Customer = order.stockName match {
    case StockName.A => Customer(name, usd - order.quantity*order.price, Stock(StockName.A, a).increase(order.quantity).quantity, b, c, d)
    case StockName.B =>  Customer(name, usd - order.quantity*order.price, a, Stock(StockName.B, b).increase(order.quantity).quantity, c, d)
    case StockName.C =>  Customer(name, usd - order.quantity*order.price, a, b, Stock(StockName.C, c).increase(order.quantity).quantity, d)
    case StockName.D =>  Customer(name, usd - order.quantity*order.price, a, b, c, Stock(StockName.D, d).increase(order.quantity).quantity)

  }

  override def sell(order: Order): Customer =  order.stockName match {
    case StockName.A => Customer(name, usd + order.quantity*order.price, Stock(StockName.A, a).decrease(order.quantity).quantity, b, c, d)
    case StockName.B =>  Customer(name, usd + order.quantity*order.price, a, Stock(StockName.B, b).decrease(order.quantity).quantity, c, d)
    case StockName.C =>  Customer(name, usd + order.quantity*order.price, a, b, Stock(StockName.C, c).decrease(order.quantity).quantity, d)
    case StockName.D =>  Customer(name, usd + order.quantity*order.price, a, b, c, Stock(StockName.D, d).decrease(order.quantity).quantity)
  }

}






