package random;

/**
 * An abstract class that contains all of the code that is shared by all types
 * of random generators. 
 */
abstract class AbstractRandomGenerator implements RandomGenerator {
  protected int lower;
  protected int upper;
  
  /**
   * Constructor with no parameter.
   */
  protected AbstractRandomGenerator() {}
  
  /**
   * Constructor with a parameter to facilitate to construct various types of generator.
   * @param type item type
   */
  protected AbstractRandomGenerator(String type) {
    if (type == null
        || (!"ability".equals(type) && !"gear".equals(type)
            && !"weapon".equals(type))) {
      throw new IllegalArgumentException("type is error");
    }
    lower = 1;
    if ("ability".equals(type)) {
      upper = 4;
    } else if ("gear".equals(type)) {
      upper = 6;
    } else if ("weapon".equals(type)) {
      upper = 5;
    }
  }
  
  /**
   * Constructor with lower and upper boundaries.
   * @param lower boundary of the random number
   * @param upper boundary of the random number
   */
  protected AbstractRandomGenerator(int lower, int upper) {
    this.lower = lower;
    this.upper = upper;
  }
  
  @Override
  public String toString() {
    return lower + "~" + upper;
  }
}
