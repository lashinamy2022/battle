package random;

import enumerate.TypeOfAbility;
import enumerate.TypeOfGear;
import enumerate.TypeOfWeapon;
import java.util.Random;


/**
 * This class represents a real random generator that provides 
 * real random values within the boundary.
 */
public final class RealRandomGenerator extends AbstractRandomGenerator {
  
  /**
   * Construct a RealRandomGenerator Object.
   * @param type represents which type of Object you want to get: Gear, Weapon, Ability
   */
  public RealRandomGenerator(String type) {
    super(type);
  }
  
  /**
   * Construct a RealRandomGenerator with lower and upper boundaries.
   * @param lower represents the lower boundary
   * @param upper represents the upper boundary
   */
  public RealRandomGenerator(int lower, int upper) {
    super(lower, upper);
  }

  @Override
  public int getInt() {
    return  new Random().nextInt(upper - lower + 1) + lower;
  }


  @Override
  public int getHalfValue() {
    int halfUpper = upper / 2;
    int halfLower = lower / 2;
    return  new Random().nextInt(halfUpper - halfLower + 1) + halfLower;
  }


  @Override
  public TypeOfAbility getAbility() {
    int value = getInt();
    TypeOfAbility[] abilities =  TypeOfAbility.values();
    for (int i = 0; i < abilities.length; i++) {
      if (value == abilities[i].getIndex()) {
        return abilities[i];
      }
    }
    return null;
  }
  

  @Override
  public TypeOfGear getGear() {
    int value = getInt();
    TypeOfGear[] gears =  TypeOfGear.values();
    for (int i = 0; i < gears.length; i++) {
      if (value == gears[i].getIndex()) {
        return gears[i];
      }
    }
    return null;
  }

  @Override
  public TypeOfWeapon getWeapon() {
    int value = getInt();
    TypeOfWeapon[] weapons =  TypeOfWeapon.values();
    for (int i = 0; i < weapons.length; i++) {
      if (value == weapons[i].getIndex()) {
        return weapons[i];
      }
    }
    return null;
  }

}
