import java.nio.file.{Files, Paths}
import java.util.stream.Stream

class OrdersFromFile(fileNameOrders: String) {
  private val actionFactory = new ActionFactory


  def apply(exchange: TExchange): Unit = {
    try {
      val stream = Files.lines(Paths.get(fileNameOrders))
      try
        stream.forEach((data: String) => actionFactory.get(data).addToQueue(exchange))
      finally if (stream != null) stream.close()
    }
  }
}