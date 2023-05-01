class Counter implements Comparable<Counter> {
  /**
   * The id of the counter associated with this event.
   * This field only matters if the event type if 
   * SERVICE_BEGIN or SERVICE_END.
   */
  private int counterId;
  
  /**
   * This indicates the availability of this particular
   * counter. True means available, false means occupied.
   */
  private boolean available;
  
  /**
   * A Queue of Customer objects representing the Counter Queue.
   */
  private Queue<Customer> queue; 
  
  /**
   * Constructor for a Counter.
   * @param counterId   The id of the counter associated with
   *                    this event.
   * @param lengthQueue The max length of the Counter Queue.
   */
  public Counter(int counterId, int lengthQueue) {
    this.counterId = counterId;
    this.available = true;
    this.queue = new Queue<Customer>(lengthQueue);
  }

  /**
   * Method to access counterId.
   *
   * @return this counter's Id
   */
  public int getCounterId() {
    return this.counterId;
  }

  /**
   * Sets the boolean value of this.available.
   *
   * @param update  Indicates the new availability of the counter.
   */
  public void setAvailability(boolean update) {
    this.available = update;
  }

  /**
   * Returns the boolean value of this.available.
   *
   * @return whether this counter is available,
   *         true if so, false otherwise.
   */
  public boolean getAvailability() {
    return this.available;
  }

  /**
   * Method to access queue.
   *
   * @return this counter's queue.
   */
  public Queue<Customer> getQueue() {
    return this.queue;
  }

  /**
   * Method to add customer into queue.
   *
   * @param customer  the customer that is to be added.
   */
  public void joinQueue(Customer customer) {
    this.queue.enq(customer);
  }

  /**
   * Method to eject customer out of queue.
   *
   * @return  the customer that is to be ejected.
   */
  public Customer leaveQueue() {
    return (Customer) this.queue.deq();
  }

  /**
   * Method to check if queue is empty.
   *
   * @return  true if the queue is empty, false otherwise.
   */
  public boolean queueEmpty() {
    return this.queue.isEmpty();
  }

  /**
   * Method to check if queue is full.
   *
   * @return  true if the queue is full, false otherwise.
   */
  public boolean queueFull() {
    return this.queue.isFull();
  }

  /**
   * Method to access the max size of the queue.
   *
   * @return  the max size of the queue.
   */
  public int queueMaxSize() {
    return this.queue.getMaxSize();
  }

  /**
   * Compares the counterId with another Counter object.
   *
   * @param counter  The counter object that the current counter
   *                 is to compare to.
   * @return -1 if current counter has smaller Id, 0 if equal,
   *         and 1 if bigger.
   */
  public int compareId(Counter counter) {
    if (this.counterId < counter.getCounterId()) {
      return -1;
    } else if (this.counterId == counter.getCounterId()) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * Compares which counter is more favourable for a customer.
   *
   * @param counter  The counter object that the current counter
   *                 is to compare to.
   * @return -1 if the current counter is more favourable, 0 if equally
   *         as much, and 1 if less so.
   */
  @Override
  public int compareTo(Counter counter) {
    // Any available counter will take precedence.
    if (this.available && counter.getAvailability()) {
      return compareId(counter);
    } else if (this.available) {
      return -1;
    } else if (counter.getAvailability()) {
      return 1;
    }

    // Both counters are currently unavailable.
    if (this.queue.length() < counter.getQueue().length()) {
      return -1;
    } else if (this.queue.length() == counter.getQueue().length()) {
      return compareId(counter);
    } else {
      return 1;
    }
  }

  @Override
  public String toString() {
    return "S" + this.counterId;
  }
}
