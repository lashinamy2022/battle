package weapon;

import enumerate.TypeOfAbility;
import enumerate.TypeOfWeapon;
import java.util.Map;
import random.RandomGenerator;
import random.RealRandomGenerator;

/**
 * Weapon interface that contains all operations that all types of weapons should support.
 */
public interface Weapon {
  
  /**
   * This is to get damage of the weapon.
   * @param abilities represents the abilities of a player
   * @return the value of damage
   */
  int getDamage(Map<TypeOfAbility, Integer> abilities);
  
  /**
   * This is a factory method for a client instantiating a Weapon Object.
   * @param weapon represents which type of weapon a client wants to instantiate
   * @return the Weapon Object
   */
  static  Weapon createWeapon(TypeOfWeapon weapon) {
    RandomGenerator random;
    if (weapon.getIndex() == TypeOfWeapon.AXE.getIndex()) {
      random = new RealRandomGenerator(6, 10);
      return new Axe(random);
    } else if (weapon.getIndex() == TypeOfWeapon.BROADSWORD.getIndex()) {
      random = new RealRandomGenerator(6, 10);
      return new Broadsword(random);
    } else if (weapon.getIndex() == TypeOfWeapon.FLAIL.getIndex()) {
      random = new RealRandomGenerator(8, 12);
      return new Flail(random);
    } else if (weapon.getIndex() == TypeOfWeapon.KATANAS.getIndex()) {
      random = new RealRandomGenerator(4, 6);
      return new Katanas(random);
    } else if (weapon.getIndex() == TypeOfWeapon.TWOHANDEDSWORD.getIndex()) {
      random = new RealRandomGenerator(8, 12);
      return new TwoHandedSword(random);
    } 
    return null;
  }
}
