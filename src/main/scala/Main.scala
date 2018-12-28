
object Main {

  def main(args: Array[String]): Unit = {
    val customersList: TCustomerList = new CustomerFromFile("src/main/resources/clients.txt")
    val ordersFromFile = new OrdersFromFile("src/main/resources/orders.txt")
    val exchange: TExchange = Exchange(customersList)
    ordersFromFile.apply(exchange)
    customersList.writeToFile("src/main/resources/result.txt")
  }
}
