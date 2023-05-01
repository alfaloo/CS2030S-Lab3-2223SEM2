class Customer {
  /** 
   * The id of a customer associated with this event.  
   * First customer has id 0. Next is 1, 2, etc. 
   */
  private int customerId;

  /**
   * The service time of the customer associated
   * this event.  This field matters only if the
   * event type is ARRIVAL or SERVICE_BEGIN.
   */
  private double serviceTime;

  /**
   * The type of service that the customer wishes to perform.
   */
  private String serviceType; 
  
  /**
   * Constructor for a Customer.
   * @param customerId  The customer associated with this
   *                    event.
   * @param serviceTime The time this customer takes 
   *                    for service.
   * @param serviceType Indicates the service wanted by
   *                    the customer.
   */
  public Customer(int customerId, double serviceTime, int serviceType) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.serviceType = serviceType == 0
                     ? "Deposit"
                     : serviceType == 1
                     ? "Withdrawal"
                     : serviceType == 2
                     ? "OpenAccount"
                     : "UnknownServiceType";
  }

  /**
   * Method to select most optimal counter.
   *
   * @param bank  The bank that the customer is visiting.
   * @return  The counter which the customer should proceed to.
   */
  public Counter chooseCounter(Bank bank) {
    return bank.findCounter();
  }
 
  /**
   * Method to access customerId.
   *
   * @return this cutomer's Id.
   */
  public int getCustomerId() {
    return this.customerId;
  }

  /**
   * Method to access serviceTime.
   *
   * @return this customer's service time.
   */
  public double getServiceTime() {
    return serviceTime;
  }

  /**
   * Method to access serviceType.
   *
   * @return this customer's service type.
   */
  public String getServiceType() {
    return this.serviceType;
  }

  @Override
  public String toString() {
    return "C" + this.customerId;
  }
}
