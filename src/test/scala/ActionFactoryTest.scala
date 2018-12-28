import org.scalatest.{BeforeAndAfterEach, FunSuite}

class ActionFactoryTest extends FunSuite with BeforeAndAfterEach {
  val str = "C8\tb\tC\t15\t4"
  var buy: Buy = _
  var order: Order = _
  val actionFactory = new ActionFactory
  override def beforeEach(): Unit = {
    order = Order(StockName.C, 15, 4)
    buy = Buy("C8", order)
  }
  test("get: object Buy's or Sell's to get") {
    assert(actionFactory.get(str) == buy)
  }
}
