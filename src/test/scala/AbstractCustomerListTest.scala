import org.scalatest.{BeforeAndAfterEach, FunSuite}

class AbstractCustomerListTest extends FunSuite with BeforeAndAfterEach {
  val data = "C1\t1000\t130\t240\t760\t320"
  val customer = new Customer(data)
  var order: Order = _
  val customerList = new AbstractCustomerList()

  override def beforeEach(): Unit = {
    order = Order(StockName.A, 1, 100)

  }
  test("addClient: add Customer to map") {
    customerList.addClient(customer)
    assert(customerList.clients(customer.name) == customer)
  }

  test("getCustomerByName: get customer for name") {
    assert(customerList.getCustomerByName(customer.name) == customer)
  }

}
