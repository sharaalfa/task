trait TCustomerList {
  def getCustomerByName(name: String): TCustomer
  def addClient(newClient: Customer)
  def writeToFile(file: String)

}
