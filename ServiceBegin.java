class ServiceBegin extends Event {
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
   * Counter is now a separate class, holding information
   * about a particular counter.
   */
  private Counter counter;
  
  /**
   * Constructor for ServiceBegin event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this
   *                    event.
   * @param counter     The counter associated with
   *                    this event.
   * @param bank        The indicator of which bank is
   *                    visited
   */
  public ServiceBegin(double time, Customer customer, Counter counter, Bank bank) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.counter.setAvailability(false);
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
      new ServiceEnd(endTime, this.customer, this.counter, this.bank)
    };
  }

  @Override
  public String toString() {
    String str = String.format(": %s %s begin (by %s %s)",
          this.customer, this.customer.getServiceType(), this.counter, this.counter.getQueue());
    return super.toString() + str;
  }
}
