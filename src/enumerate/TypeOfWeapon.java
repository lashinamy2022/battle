package enumerate;

/**
 * Represents all kinds of weapons in the battle.
 */
public enum TypeOfWeapon {
  AXE("AXE", 1),
  BROADSWORD("BROADSWORD", 2),
  FLAIL("FLAIL",  3),
  KATANAS("KATANAS", 4),
  TWOHANDEDSWORD("TWOHANDEDSWORD", 5);
  
  private final String name;
  private final int index;
  
  private TypeOfWeapon(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }
}
