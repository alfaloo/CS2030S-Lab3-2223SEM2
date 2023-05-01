class Arrival extends Event {
  /** 
   * Customer is now a separate class, holding information
   * about a particular customer.
   */
  private Customer customer;
  
  /**
   * Bank is now a separate class, holding information about
   * a particular bank.
   */
  private Bank bank;

  /**
   * Constructor for an Arrival event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this
   *                    event.
   * @param bank        The indicator of which bank is
   *                    visited
   */
  public Arrival(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public Event[] simulate() {
    // Customer chooses most favourable counter
    Counter counter = this.customer.chooseCounter(this.bank);  
    if (counter == null) {
      // If no such counter can be found, the customer
      // should join the Entrance Queue. If Entrance
      // Queue is full, the customer should depart.
      if (this.bank.queueFull()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer)
        };
      } else {
        return new Event[] {
          new JoinEntranceQueue(this.getTime(), this.customer, this.bank)
        };
      }
    } else {
      // Else, the customer should go the the counter.
      // If the counter is available, the customer should
      // proceeed with service, otherwise, the customer
      // should join the CounterQueue.
      if (counter.getAvailability()) {
        return new Event[] {
          new ServiceBegin(this.getTime(), this.customer, counter, this.bank)
        };
      } else {
        return new Event[] {
          new JoinCounterQueue(this.getTime(), this.customer, counter)
        };
      }
    }
  }

  @Override
  public String toString() {
    String str = String.format(": %s arrived %s", this.customer, this.bank.getQueue());
    return super.toString() + str;
  }
}

