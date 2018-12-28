import scala.collection.mutable

class OrdersQueue {

  var ordersMap: Map[Order, mutable.Queue[Action]] = Map.empty

  def addOrder(action: Action): Unit = {
    val order: Order = action.getOrder
    if (checkOrder(order)) {
      val list:mutable.Queue[Action] = ordersMap(order)
      list.enqueue(action)
    } else {
      val list: mutable.Queue[Action] = new mutable.Queue[Action]()
      list.enqueue(action)
      ordersMap = ordersMap + (order -> list)
    }
  }

  def checkOrder(order: Order): Boolean = ordersMap.contains(order)

  def pullOrder(order: Order): Action = {
    val list: mutable.Queue[Action] = ordersMap(order)
    val result: Action = list.dequeue()
    if (list.isEmpty) {
      ordersMap = ordersMap - order
    }
    result
  }

}
