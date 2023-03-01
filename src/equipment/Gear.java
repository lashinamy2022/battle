package equipment;

import enumerate.TypeOfBelt;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.Map;
import random.RandomGenerator;
import random.RealRandomGenerator;

/**
 * Gear interface that contains all operations that all types of gears should support.
 *
 */
public interface Gear {
  
  /**
   * This method is provided to use each type of gears which can result in 
   * positive or negative effects on the players' abilities.
   * @return a map formatting with {gearNo:{ability:Dexterity},{value:3},{usage:0}}
   */
  Map<String, Map<String, Object>> use();
  
  /**
   * This method is provides to get the limit of each type of gears.
   * @return the limit of each type gears
   */
  int getLimit();
  
  /**
   * This is a factory method for a client instantiating a Gear Object.
   * @param index represents the no of items
   * @param gear represents which type of gear a client wants to instantiate
   * @param effect  represents which kind of effect the gear will have
   * @return the Gear Object
   */
  static  Gear createGear(int index, TypeOfGear gear, TypeOfEffect effect) {
    String no = gear.getName() + "-" + index;
    RandomGenerator positive = new RealRandomGenerator(1, 5);
    RandomGenerator negative = new RealRandomGenerator(-3, -1);
    if (gear.getIndex() == TypeOfGear.HEADGEAR.getIndex()) {
      return new Headgear(no, effect, positive, negative);
    } else if (gear.getIndex() == TypeOfGear.POTION.getIndex()) {
      positive = new RealRandomGenerator(1, 10);
      RandomGenerator ability = new RealRandomGenerator("ability");
      return new Potion(no, effect, positive, negative, ability);
    } else if (gear.getIndex() == TypeOfGear.SBELT.getIndex()) {
      return new Belt(no, effect, TypeOfBelt.SMALL);
    } else if (gear.getIndex() == TypeOfGear.MBELT.getIndex()) {
      return new Belt(no, effect, TypeOfBelt.MEDIUM);
    } else if (gear.getIndex() == TypeOfGear.LBELT.getIndex()) {
      return new Belt(no, effect, TypeOfBelt.LARGE);
    } else if (gear.getIndex() == TypeOfGear.FOOTWEAR.getIndex()) {
      return new Footwear(no, effect, positive, negative);
    }
    return null;
  }
}
