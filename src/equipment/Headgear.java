package equipment;

import enumerate.TypeOfAbility;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.Map;
import random.RandomGenerator;

/**
 * This class represents a headgear. It offers all the operations mandated by the
 * Gear interface.
 */
public class Headgear extends GearAbstract {
  
  /**
   * Construct a headgear object by using given effect type.
   * @param no represents the no of the gear
   * @param effectType represents the type of effects
   * @param positiveRandom represents a {@link RandomGenerator}
   *     which can return positive random values
   * @param negativeRandom represents a {@link RandomGenerator} 
   *     which can return negative random values
   */
  public Headgear(String no, TypeOfEffect effectType, 
      RandomGenerator positiveRandom, RandomGenerator negativeRandom) {
    super(no, effectType, 1, positiveRandom, negativeRandom);
  }

  @Override
  public Map<String, Map<String, Object>> use() {
    return helpUse(TypeOfGear.HEADGEAR, TypeOfAbility.CONSTITUTION);
  }
}
