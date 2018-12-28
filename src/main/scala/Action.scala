trait Action {
  def apply (customers: TCustomerList)
  def addToQueue(exchange: TExchange)
  def getOrder: Order

}
