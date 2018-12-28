case class Buy (clientName: String, order: Order)  extends Action with BaseAction {
  override def apply(customers: TCustomerList): Unit = {
    if (customers.getCustomerByName(clientName).getBalance < order.quantity*order.price)
      customers.addClient(customers.getCustomerByName(clientName).patBuy(order))
    else customers.addClient(customers.getCustomerByName(clientName).buy(order))
  }

  override def addToQueue(exchange: TExchange): Unit = exchange.buyAction(this)

  override def getOrder: Order = order
}
