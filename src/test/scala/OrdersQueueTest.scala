import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class OrdersQueueTest extends FunSuite with BeforeAndAfterEach{
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var ordersMap: Map[Order, mutable.Queue[Action]] = Map.empty
  var buy: Buy = _
  var order: Order = _
  val customerList = new AbstractCustomerList()
  var queue: mutable.Queue[Action] = mutable.Queue.empty
  val ordersQueue = new OrdersQueue()
  override protected def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)
    customerList.addClient(customer)
    buy = Buy(customer.name, order)
    buy.apply(customerList)
    queue.enqueue(buy)
    ordersMap = ordersMap + (order -> queue)
    ordersQueue.addOrder(buy)
  }

  test("addOrder: add order to buy or sell queue's ") {
    assert(ordersQueue.ordersMap(order).dequeue()==buy)
  }

  test("checkOrder: check if order exists") {

  }

  test("pullOrder: return if order exists") {
    assert(ordersQueue.pullOrder(order)==buy)
  }
}
