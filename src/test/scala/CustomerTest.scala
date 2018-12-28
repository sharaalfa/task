import org.scalatest.{BeforeAndAfterEach, FunSuite}

class CustomerTest extends FunSuite with BeforeAndAfterEach {
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var order: Order = _

  override def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)
  }

  test("constructor: parse") {
    val data = "C1\t1000\t130\t240\t760\t320"
    val customer = new Customer(data)
    assert(customer.a == 130)
    assert(customer.name=="C1")
    assert(customer.usd==1000)

  }
  test("getStock: getting quantity") {

    val q = customer.getStock(StockName.apply(1))
    assert(q == 240)
  }
  test ("buy: the order to init for buy") {
    customer.buy(order)
    assert(customer.buy(order).a==230)
    assert(customer.buy(order).usd==900)
  }
  test ("sell: the order to init for sell") {
    customer.sell(order)
    assert(customer.sell(order).a==30)
    assert(customer.sell(order).usd==1100)
  }
}
