package random;

import enumerate.TypeOfAbility;
import enumerate.TypeOfGear;
import enumerate.TypeOfWeapon;

/**
 * This is a interface that provides generating random numbers.
 *
 */
public interface RandomGenerator {
  
  /**
   * Get an random integer.
   * @return the result
   */
  int getInt();
  
  /**
   * Get an random integer within the half of lower and upper boundaries.
   * @return the result
   */
  int getHalfValue();
  
  /**
   * Get a random ability.
   * @return a random ability
   */
  TypeOfAbility getAbility();
  
  /**
   * Get a random gear.
   * @return a random gear.
   */
  TypeOfGear getGear();
  
  /**
   * Get a random weapon.
   * @return a random weapon
   */
  TypeOfWeapon getWeapon();
}
