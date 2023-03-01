package enumerate;

/**
 * Represents all kinds of gears in the battle.
 */
public enum TypeOfGear {
   HEADGEAR("HEADGEAR", 1, null, "HEADGEAR"),
   POTION("POTION", 2, null, "POTION"),
   SBELT("SBELT", 3, TypeOfBelt.SMALL, "BELT"),
   MBELT("MBELT", 4, TypeOfBelt.MEDIUM, "BELT"),
   LBELT("LBELT", 5, TypeOfBelt.LARGE, "BELT"),
   FOOTWEAR("FOOTWEAR", 6, null, "FOOTWEAR");
   
  private final int index;
  private final String name;
  private final TypeOfBelt beltType;
  private final String category;
  
  private TypeOfGear(String name, int index, TypeOfBelt beltType, String category) {
    this.name = name;
    this.index = index;
    this.beltType = beltType;
    this.category = category;
  }
   
   
  public String toString() {
    return name;
  }
   
  public String getName() {
    return name;
  }


  public int getIndex() {
    return index;
  }
  
  public TypeOfBelt getBeltType() {
    return beltType;
  }


  public String getCategory() {
    return category;
  }
  
}
