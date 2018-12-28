import org.scalatest.{BeforeAndAfterEach, FunSuite}

class StockTest extends FunSuite with BeforeAndAfterEach{
  var stock: Stock = _

  override protected def beforeEach(): Unit = {
    stock = Stock(StockName.A, 200)
  }

  test("increase: quantity to add") {
    stock.increase(100)
    assert(stock.increase(100).quantity==300)
  }
}
