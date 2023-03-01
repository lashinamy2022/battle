package enumerate;

/**
 * Represents all kinds of effects in the battle.
 */
public enum TypeOfEffect implements Comparable<TypeOfEffect> {
  
  POSITIVE(1),
  NEGATIVE(-1);
  
  private final int effect;
  
  private TypeOfEffect(int effect) {
    this.effect = effect;
  }
  
  public int getEffect() {
    return effect;
  }
  
  /**
   * This is to get the name of this type.
   * @return the name
   */
  public String toString() {
    if (effect < 0) {
      return "negative";
    } else {
      return "positive";
    }
  }
}
