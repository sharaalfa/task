import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class ExchangeTest extends FunSuite with BeforeAndAfterEach {
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var ordersMap: Map[Order, mutable.Queue[Action]] = Map.empty
  var buy: Buy = _
  var sell: Sell = _
  var order: Order = _
  val customerList = new AbstractCustomerList()
  var queue: mutable.Queue[Action] = mutable.Queue.empty
  val ordersQueue = new OrdersQueue()
  var exchange: Exchange = _
  var buyQueue: OrdersQueue = _
  var sellQueue: OrdersQueue = _
  override protected def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)
    customerList.addClient(customer)
    buy = Buy(customer.name, order)
    sell = Sell(customer.name, order)
    buy.apply(customerList)
    queue.enqueue(buy)
    ordersMap = ordersMap + (order -> queue)
    ordersQueue.addOrder(buy)
    exchange = Exchange(customerList)
    buyQueue = new OrdersQueue
    sellQueue = new OrdersQueue
    buyQueue.addOrder(buy)
    sellQueue.addOrder(sell)

  }

  test("buyAction: add and pull") {
    exchange.buyAction(buy)
    assert(exchange.getBuyQueue.ordersMap(order)==buyQueue.ordersMap(order))
  }

  test("sellAction: add and pull") {
    exchange.sellAction(sell)
    assert(exchange.getSellQueue.ordersMap(order)==sellQueue.ordersMap(order))

  }



}
