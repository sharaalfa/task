case class Sell (clientName: String, order: Order) extends Action with BaseAction {
  override def apply(customers: TCustomerList): Unit = customers.addClient(customers.getCustomerByName(clientName).sell(order))

  override def addToQueue(exchange: TExchange): Unit = exchange.sellAction(this)

  override def getOrder: Order = order
}
