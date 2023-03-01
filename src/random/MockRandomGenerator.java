package random;

import enumerate.TypeOfAbility;
import enumerate.TypeOfGear;
import enumerate.TypeOfWeapon;


/**
 * This class represents a fake random generator which just returns a fixed value.
 * And this is for testing classes.
 *
 */
public final class MockRandomGenerator extends AbstractRandomGenerator {
  
  private final int mockValue;

  /**
   * Construct a MockRandomGenerator Object.
   * @param mockItemType represents which type of Object you want to get: Gear, Weapon, Ability
   * @param mockValue represents which item you want to get within the above Object
   * @throws IllegalArgumentException when the mockItemType is not in the range
   */
  public MockRandomGenerator(String mockItemType, int mockValue) throws IllegalArgumentException {
    super(mockItemType);
    if (mockItemType == null
        || (!"ability".equals(mockItemType) && !"gear".equals(mockItemType)
            && !"weapon".equals(mockItemType))) {
      throw new IllegalArgumentException("type is error");
    }
    this.mockValue = mockValue;
  }
  
  /**
   * Construct a MockRandomGenerator with a mock value.
   * @param lower represents the lower boundary
   * @param upper represents the upper boundary
   * @param mockValue represents the value you want to get in a test
   */
  public MockRandomGenerator(int lower, int upper, int mockValue) throws IllegalArgumentException {
    super(lower, upper);
    if (mockValue < lower || mockValue > upper) {
      throw new IllegalArgumentException("The mock value cannot be smaller "
          + "or greater than the lower and upper boundary respectively");
    }
    this.mockValue = mockValue;
  }
  
  @Override
  public int getInt() {
    return mockValue;
  }

  @Override
  public int getHalfValue() {
    return mockValue;
  }

  @Override
  public TypeOfAbility getAbility() {
    int value = mockValue;
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
    int value = mockValue;
    TypeOfGear[] types =  TypeOfGear.values();
    for (int i = 0; i < types.length; i++) {
      if (value == types[i].getIndex()) {
        return types[i];
      }
    }
    return null;
  }

  @Override
  public TypeOfWeapon getWeapon() {
    int value = mockValue;
    TypeOfWeapon[] weapons =  TypeOfWeapon.values();
    for (int i = 0; i < weapons.length; i++) {
      if (value == weapons[i].getIndex()) {
        return weapons[i];
      }
    }
    return null;
  }
}
