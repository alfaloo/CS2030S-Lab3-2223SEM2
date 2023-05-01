class JoinEntranceQueue extends Event {
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
  public JoinEntranceQueue(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public Event[] simulate() {
    this.bank.joinQueue(this.customer);
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = String.format(": %s joined bank queue %s", this.customer, this.bank.getQueue());
    return super.toString() + str;
  }
}

