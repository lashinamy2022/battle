package enumerate;

/**
 * Represents all abilities of players in the battle .
 */
public enum TypeOfAbility {
  CONSTITUTION("CONTITUTION", 1),
  STRENGTH("STRENGTH", 2),
  DEXTERITY("DEXTERITY", 3),
  CHARISMA("CHARISMA", 4);
  
  private final String name;
  private final int index;
  
  private TypeOfAbility(String name, int index) {
    this.name = name;
    this.index = index;
  }
  
  public int getIndex() {
    return index;
  }

  public String getName() {
    return name;
  }
  
  public String toString() {
    return name;
  }

}
