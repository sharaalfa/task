

import scala.io.Source

class CustomerFromFile (fileNameClients: String) extends AbstractCustomerList {
  for (line <- Source.fromFile(fileNameClients).getLines()) {
    this.addClient(new Customer(line))
  }
}
