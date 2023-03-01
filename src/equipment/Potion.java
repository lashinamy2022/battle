package equipment;

import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.Map;
import random.RandomGenerator;


/**
 * This class represents a potion. It offers all the operations mandated by the
 * Gear interface.
 */
public class Potion extends GearAbstract {
  
  private final RandomGenerator randomAbility;
  
  /**
   * Construct a potion object by using given effect type.
   * @param no represents the no of the gear
   * @param type represents the type of effects
   * @param positiveRandom represents a {@link RandomGenerator}
   *     which can return positive random values
   * @param negativeRandom represents a {@link RandomGenerator} 
   *     which can return negative random values
   * @param randomAbility represents a {@link RandomGenerator} 
   *     which can return a random ability
   */
  public Potion(String no, TypeOfEffect type, 
      RandomGenerator positiveRandom, RandomGenerator negativeRandom, 
      RandomGenerator randomAbility) {
    super(no, type, -1, positiveRandom, negativeRandom);
    this.randomAbility = randomAbility;
  }
  
  @Override
  public Map<String, Map<String, Object>> use() {
    return helpUse(TypeOfGear.POTION, randomAbility.getAbility());
  }
}
