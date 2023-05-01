class Bank {
  /**
   * An array containing all the counters. Each
   * counter will have an int id attribute and
   * a boolean available attribute.
   */
  private Array<Counter> allCounters;
  
  /**
   * Queue for the bank.
   */
  private Queue<Customer> queue;
 
  /**
   * Bank constructor to initialise the counters.
   *
   * @param numOfCounters  Number of counters in the bank
   * @param maxQueue       Maximum length of the queue
   */ 
  public Bank(int numOfCounters, int lengthQueue, int maxQueue) {
    this.allCounters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.allCounters.set(i, new Counter(i, lengthQueue));
    }
    this.queue = new Queue<Customer>(maxQueue);
  }

  /**
   * Method to retrieve the first available
   * counter.
   *
   * @return Return first available counter,
   *         otherwise return null.
   */
  public Counter findCounter() {
    Counter counter = this.allCounters.min();
    if (!counter.getAvailability() && counter.getQueue().isFull()) {
      return null;
    } else {
      return counter;
    }
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
   * Method to access the queue.
   *
   * @return the entire queue object
   */
  public Queue<Customer> getQueue() {
    return this.queue;
  }
}
