package equipment;

import enumerate.TypeOfAbility;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.HashMap;
import java.util.Map;
import random.RandomGenerator;


/**
 * An abstract class that contains all of the code that is shared by all types
 * of Gears. 
 */
abstract class GearAbstract implements Gear {
  protected final String no;
  protected final TypeOfEffect effectType;
  protected final int limit;
  private RandomGenerator positiveRandom;
  private RandomGenerator negativeRandom;
  
  /**
   * Protected constructor for use by subclasses.
   * @param the no. of the gear
   * @param effectType represents the effects a gear will have(positive/negative effect)
   * @param limit represents the use limit of the gear
   * @param positiveRandom represents a {@link RandomGenerator}
   *     which can return positive random values
   * @param negativeRandom represents a {@link RandomGenerator} 
   *     which can return negative random values
   */
  protected GearAbstract(String no, TypeOfEffect effectType, int limit, 
      RandomGenerator positiveRandom, RandomGenerator negativeRandom) {
    this.no = no;
    this.effectType = effectType;
    this.limit = limit;
    this.positiveRandom = positiveRandom;
    this.negativeRandom = negativeRandom;
  }
  
  /**
   * Protected constructor for use by subclasses.
   * @param the no. of the gear
   * @param effectType represents the effects a gear will have(positive/negative effect)
   * @param limit represents the use limit of the gear
   */
  protected GearAbstract(String no, TypeOfEffect effectType, int limit) {
    this.no = no;
    this.effectType = effectType;
    this.limit = limit;
  }
  
  @Override
  public int getLimit() {
    return limit;
  }
  
  /**
   * This is a method to help to use each type of gears.
   * @param ability  represents the ability the gear can affect
   * @return a map formatting with {gearNo:{Dexterity:3},{Constitution:5},{usage:0}}
   */
  protected Map<String, Map<String, Object>> helpUse(TypeOfGear gearType, TypeOfAbility ability) {
    Map<String, Object> values = new HashMap<>();
    values.put("gearType", gearType);
    if (effectType.getEffect() == TypeOfEffect.POSITIVE.getEffect()) {
      values.put(ability.getName(), positiveRandom.getInt());
    } else {
      values.put(ability.getName(), negativeRandom.getInt());
    }
    Map<String, Map<String, Object>> result = new HashMap<>();
    result.put(no, values);
    return result;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("no:" + no + ",")
      .append("effect:" + effectType.toString() + ",")
        .append("limit:" + limit);
    return sb.toString();
  }
}
