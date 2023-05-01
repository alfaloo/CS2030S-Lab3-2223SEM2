class Departure extends Event {
  /** 
   * Customer is now a separate class, holding information
   * about a particular customer.
   */
  private Customer customer;

  /**
   * Constructor for a Departure event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this
   *                    event.
   */
  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }   
}
