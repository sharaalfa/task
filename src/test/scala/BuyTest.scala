import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class BuyTest extends FunSuite with BeforeAndAfterEach {
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var ordersMap: Map[Order, mutable.Queue[Action]] = Map.empty
  var buy: Buy = _
  var order: Order = _
  val customerList = new AbstractCustomerList()
  var queue: mutable.Queue[Action] = mutable.Queue.empty
  val ordersQueue = new OrdersQueue()
  var exchange: Exchange = _
  var buyQueue: OrdersQueue = _
  override protected def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)
    customerList.addClient(customer)
    buy = Buy(customer.name, order)
    queue.enqueue(buy)
    ordersMap = ordersMap + (order -> queue)
    ordersQueue.addOrder(buy)
    exchange = Exchange(customerList)
    buyQueue = new OrdersQueue
    buyQueue.addOrder(buy)
  }
  test("apply: buy for name of client and order") {
    buy.apply(customerList)
    assert(customerList.clients(customer.name).usd==900)
    assert(customerList.clients(customer.name).a==230)
  }

  test("addQueue: add to queue") {
    buy.addToQueue(exchange)
    assert(exchange.getBuyQueue.ordersMap(order)==buyQueue.ordersMap(order))
  }

  test("getOrder: order to get") {
    assert(buy.getOrder==order)
  }




}
