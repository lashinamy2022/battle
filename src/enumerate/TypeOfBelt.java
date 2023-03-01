package enumerate;

/**
 * Represents all kinds of belts in the battle.
 */
public enum TypeOfBelt {
  SMALL("small", 1),
  MEDIUM("medium", 2),
  LARGE("large", 4);

  private final int unit;   // in kilograms
  private final String type;
  
  TypeOfBelt(String type, int unit) {
    this.type = type;
    this.unit = unit;
  }
  
  public int getUnit() { 
    return unit; 
  }
  
  public String toString() {
    return this.type;
  }
}
