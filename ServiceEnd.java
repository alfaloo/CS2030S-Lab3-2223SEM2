class ServiceEnd extends Event {
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
   * Constructor for a ServiceEnd event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this
   *                    event.
   * @param counter     The counter associated with
   *                    this event.
   * @param bank        The indicator of which bank is
   *                    visited
   */
  public ServiceEnd(double time, Customer customer,
                    Counter counter, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  @Override
  public Event[] simulate() {
    if (this.counter.queueEmpty() && this.bank.queueEmpty()) {
      // If Counter Queue is empty, set the counter as available
      // and schedule a departure event at current time.
      this.counter.setAvailability(true);
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      }; 
    } else if (this.bank.queueEmpty()) {
      // If Bank Queue is empty, serve next customer in Counter Queue.
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), this.counter.leaveQueue(),
                         this.counter, this.bank)
      };
    } else if (this.counter.queueMaxSize() != 0) {
      // Otherwise, allow first customer in Bank Queue to join this Counter Queue,
      // given that one does indeed exist.
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), this.counter.leaveQueue(),
                         this.counter, this.bank),
        new JoinCounterQueue(this.getTime(), this.bank.leaveQueue(),
                             this.counter)
      };
    } else {
      // Special case where the max Counter Queue length is 0.
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), this.bank.leaveQueue(),
                         this.counter, this.bank),
      };
    }

  }

  @Override
  public String toString() {
    String str = String.format(": %s %s done (by %s %s)",
          this.customer, this.customer.getServiceType(), this.counter, this.counter.getQueue());
    return super.toString() + str;
  }
}
