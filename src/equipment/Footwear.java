package equipment;

import enumerate.TypeOfAbility;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.Map;
import random.RandomGenerator;


/**
 * This class represents a pair of footwears. It offers all the operations mandated by the
 * Gear interface.
 */
public class Footwear extends GearAbstract {

  
  /**
   * Construct a pair of footwear object by using given effect type.
   * @param no represents the no of the gear
   * @param type represents the type of effects
   * @param positiveRandom represents a {@link RandomGenerator}
   *     which can return positive random values
   * @param negativeRandom represents a {@link RandomGenerator} 
   *     which can return negative random values
   */
  public Footwear(String no, TypeOfEffect type, 
      RandomGenerator positiveRandom, RandomGenerator negativeRandom) {
    super(no, type, 1, positiveRandom, negativeRandom);
  }
  
 
  @Override
  public Map<String, Map<String, Object>> use() {
    return helpUse(TypeOfGear.FOOTWEAR, TypeOfAbility.DEXTERITY);
  }
}
