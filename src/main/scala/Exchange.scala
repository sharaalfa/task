
case class Exchange(customers: TCustomerList) extends TExchange {
  private val sellQueue = new OrdersQueue
  private val buyQueue = new OrdersQueue
  def getSellQueue: OrdersQueue = sellQueue
  def getBuyQueue: OrdersQueue = buyQueue

  override def buyAction(action: Buy): Unit = {
    if (sellQueue.checkOrder(action.getOrder)) {
      action.apply(customers)
      sellQueue.pullOrder(action.getOrder).apply(customers)
    }
    else buyQueue.addOrder(action)
  }

  override def sellAction(action: Sell): Unit = {
    if (buyQueue.checkOrder(action.getOrder)) {
      action.apply(customers)
      buyQueue.pullOrder(action.getOrder).apply(customers)
    }
    else sellQueue.addOrder(action)
  }
}

