package equipment;

import enumerate.TypeOfAbility;
import enumerate.TypeOfBelt;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a belt. It offers all the operations mandated by the
 * Gear interface.
 *
 */
public class Belt extends GearAbstract {
  private final TypeOfBelt beltType;
  
  /**
   * Construct a Belt object by using given effect type and belt type.
   * @param no represents the no of the gear
   * @param effectType represents the type of effects
   * @param beltType represents the type of belt
   */
  public Belt(String no, TypeOfEffect effectType, TypeOfBelt beltType) {
    super(no, effectType, 10);
    this.beltType = beltType;
  }
  
  @Override
  public Map<String, Map<String, Object>> use() {
    Map<String, Object> values = new HashMap<>();
    if (beltType.toString().equals(TypeOfBelt.SMALL.toString())) {
      values.put("gearType", TypeOfGear.SBELT);
    } else if (beltType.toString().equals(TypeOfBelt.MEDIUM.toString())) {
      values.put("gearType", TypeOfGear.MBELT);
    } else {
      values.put("gearType", TypeOfGear.LBELT);
    }
    if (effectType.getEffect() == TypeOfEffect.POSITIVE.getEffect()) {
      values.put(TypeOfAbility.CONSTITUTION.getName(),  positiveEffect());
      values.put(TypeOfAbility.CHARISMA.getName(), positiveEffect());
    } else {
      values.put(TypeOfAbility.CONSTITUTION.getName(),  negativeEffect());
      values.put(TypeOfAbility.CHARISMA.getName(), negativeEffect());
    }
    Map<String, Map<String, Object>> result = new HashMap<>();
    result.put(no, values);
    return result;
  }
  
  /**
   * This method is to get the positive effect of each type of belts.
   * @return the effect
   */
  private int positiveEffect() {
    if (this.beltType.toString().equals(TypeOfBelt.SMALL.toString())) {
      return 1;
    } else if (this.beltType.toString().equals(TypeOfBelt.MEDIUM.toString())) {
      return 2;
    } else if (this.beltType.toString().equals(TypeOfBelt.LARGE.toString())) {
      return 4;
    }
    return 0;
  }
  
  /**
   * This method is to get the negative effect of each type of belts.
   * @return the effect
   */
  private int negativeEffect() {
    if (this.beltType.toString().equals(TypeOfBelt.SMALL.toString())) {
      return -1;
    } else if (this.beltType.toString().equals(TypeOfBelt.MEDIUM.toString())) {
      return -2;
    } else if (this.beltType.toString().equals(TypeOfBelt.LARGE.toString())) {
      return -4;
    }
    return 0;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("no:" + no + ",")
    .append("unit:" + beltType.getUnit()).append(",")
    .append("effect:" + effectType.toString() + ",")
        .append("limit:" + limit);
    return sb.toString();
  }
}
