import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class SellTest extends FunSuite with BeforeAndAfterEach {
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var order: Order = _
  var sell: Sell = _
  var ordersMap: Map[Order, mutable.Queue[Action]] = Map.empty
  val customerList = new AbstractCustomerList()
  var queue: mutable.Queue[Action] = mutable.Queue.empty
  val ordersQueue = new OrdersQueue()
  var exchange: Exchange = _
  var sellQueue: OrdersQueue = _

  override def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)
    customerList.addClient(customer)
    sell = Sell(customer.name, order)
    queue.enqueue(sell)
    ordersMap = ordersMap + (order -> queue)
    ordersQueue.addOrder(sell)
    exchange = Exchange(customerList)
    sellQueue = new OrdersQueue
    sellQueue.addOrder(sell)
  }

  test("apply: sell for name of client and order") {
    sell.apply(customerList)
    assert(customerList.clients(customer.name).usd == 1100)
    assert(customerList.clients(customer.name).a == 30)
  }
  test("addQueue: add to queue") {
    sell.addToQueue(exchange)
    assert(exchange.getSellQueue.ordersMap(order)==sellQueue.ordersMap(order))
  }

  test("getOrder: order to get") {
    assert(sell.getOrder==order)
  }
}
