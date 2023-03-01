package weapon;

import enumerate.TypeOfAbility;
import java.util.Map;
import random.RandomGenerator;

/**
 * This class represents a pair of Katanas. It offers all the operations mandated by the
 * Weapon interface.
 */
public class Katanas  extends WeaponAbstract {
  
  /**
   * Construct an Katanas object.
   * @param random represents a {@link RandomGenerator} which can return random values
   */
  public Katanas(RandomGenerator random) {
    super(random);
  }
  
  @Override
  public int getDamage(Map<TypeOfAbility, Integer> playerAbilities) {
    return super.getDamage(playerAbilities) + super.getDamage(playerAbilities);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("weapon: a pair of katanas, ");
    sb.append("damage: ").append(random.toString());
    return sb.toString();
  }
}
