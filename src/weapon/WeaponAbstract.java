package weapon;

import enumerate.TypeOfAbility;
import java.util.Map;
import random.RandomGenerator;

/**
 * An abstract class that contains all of the code that is shared by all types
 * of weapons. 
 */
abstract class WeaponAbstract implements Weapon {
  protected final RandomGenerator random;

  /**
   * Protected constructor for use by subclasses. 
   * @param random represents a {@link RandomGenerator} which can return random values
   */
  protected WeaponAbstract(RandomGenerator random) {
    this.random = random;
  }
  
  @Override
  public int getDamage(Map<TypeOfAbility, Integer> playerAbilities) {
    return random.getInt();
  }
}
