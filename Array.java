/**
 * The Array<T> for CS2030S 
 *
 * @author Zhiyang Lu
 * @version CS2030S AY22/23 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  /**
   * Constructor for Array.
   *
   * @param size The maximum num of elements we can put in the array.
   */
  public Array(int size) {
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast `Object[]` to `T[]`.
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }
  
  /**
   * Method to assign new item to a particular index.
   *
   * @param index  the position where the new item is to be set.
   * @param item   the item that is to be set.
   */
  public void set(int index, T item) {
    this.array[index] = item;
  }

  /**
   * Method to access the item at a particlar index.
   *
   * @param index  the position where the item is to be accessed.
   */
  public T get(int index) {
    return this.array[index];
  }

  /**
   * Method to retrieve smallest item in the array.
   *
   * @return an object of type T.
   */
  public T min() {
    T result = array[0];
    for (T i : array) {
      if (i.compareTo(result) < 0) {
        result = i;
      }
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
