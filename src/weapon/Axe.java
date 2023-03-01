package weapon;

import random.RandomGenerator;

/**
 * This class represents an axe. It offers all the operations mandated by the
 * Weapon interface.
 */
public class Axe  extends WeaponAbstract {
  
 
  /**
   * Construct an Axe object.
   * @param random represents a {@link RandomGenerator} which can return random values
   */
  public Axe(RandomGenerator random) {
    super(random);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("weapon: an axe, ");
    sb.append("damage: ").append(random.toString());
    return sb.toString();
  }
}
