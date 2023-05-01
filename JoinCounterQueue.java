class JoinCounterQueue extends Event { 
  /** 
   * Customer is now a separate class, holding information
   * about a particular customer.
   */
  private Customer customer;

  /**
   * Counter is now a separate class, holding information
   * about a particular counter.
   */
  private Counter counter;

  /**
   * Constructor for an Arrival event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this
   *                    event.
   * @param counter     The indicator of which counter is
   *                    visited
   */
  public JoinCounterQueue(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public Event[] simulate() {
    this.counter.joinQueue(this.customer);
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = String.format(": %s joined counter queue (at %s %s)",
                               this.customer, this.counter, this.counter.getQueue());
    return super.toString() + str;
  }
}

