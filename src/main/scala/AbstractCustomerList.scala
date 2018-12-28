import java.io.{FileOutputStream}


class AbstractCustomerList extends TCustomerList {

  var clients: Map[String, Customer] = Map.empty


  def addClient(newClient: Customer): Unit = {
    if (clients.contains(newClient.name))
      clients = clients - newClient.name
    clients = clients + (newClient.getName -> newClient)

  }

  override def getCustomerByName(name: String): TCustomer = clients(name)

  def writeToFile(fileName: String): Unit = {
    val stringBuffer: StringBuffer = new StringBuffer()
    for ((_, value) <- clients) {
      stringBuffer.append(value.toString)
      stringBuffer.append("\r\n")
    }
    try {
      val fos = new FileOutputStream(fileName)
      try {
        val buffer = stringBuffer.toString.getBytes
        fos.write(buffer, 0, buffer.length)
      } finally if (fos != null) fos.close()
    }

  }


}
