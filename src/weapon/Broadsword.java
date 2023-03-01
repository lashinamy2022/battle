package weapon;

import random.RandomGenerator;

/**
 * This class represents a broadsword. It offers all the operations mandated by the
 * Weapon interface.
 */
public class Broadsword extends WeaponAbstract {
  
  /**
   * Construct an Broadsword object.
   * @param random represents a {@link RandomGenerator} which can return random values
   */
  public Broadsword(RandomGenerator random) {
    super(random);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("weapon: a broadsword, ");
    sb.append("damage: ").append(random.toString());
    return sb.toString();
  }
}
