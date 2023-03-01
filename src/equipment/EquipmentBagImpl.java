package equipment;

import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import random.RandomGenerator;


/**
 * This class is to implement the functions in the EquipmentBag interface.
 */
public class EquipmentBagImpl implements EquipmentBag {
  private Map<TypeOfGear, Stack<Gear>> gears;
  private final int size;
  private final Double negativePercent;
  private Map<TypeOfGear, Integer> gearNum;
  private final RandomGenerator randomGear;
 
  /**
   * This is the constructor of {@link EquipmentBagImpl} which is to facilitate
   * a client to instantiate an equipment bag.
   * 
   * @param randomGear represents the random gear generator
   */
  public EquipmentBagImpl(RandomGenerator randomGear) {
    this.randomGear = randomGear;
    size = 40;
    negativePercent = 25.0;
    gears = new HashMap<>();
    //5 headgear, 5 footwear, 15 belts,15 potions
    gearNum = new HashMap<>();
    gearNum.put(TypeOfGear.HEADGEAR, 5);
    gearNum.put(TypeOfGear.SBELT, 5);
    gearNum.put(TypeOfGear.MBELT, 5);
    gearNum.put(TypeOfGear.LBELT, 5);
    gearNum.put(TypeOfGear.FOOTWEAR, 5);
    gearNum.put(TypeOfGear.POTION, 15);
    //25% have negative effects which means that 10 gears have negative effects
    int count = (int) (size * (negativePercent / 100));
    //the order should be create negative gears first and then create positive gears
    //the order can't be turned upside town 
    createNegativeGears(count);
    createPositiveGears();
  }
 
  /**
  * This constructor is to facilitate  to return a equipment bag with 20 random items to players.
  * @param gears the requested gears
  * @param array the number of each type of gears
  */
  private EquipmentBagImpl(Map<TypeOfGear, Stack<Gear>> gears) {
    this.randomGear = null;
    this.gears = gears;
    this.size = 20;
    this.negativePercent = null;
    this.gearNum = new HashMap<>();
    for (Map.Entry<TypeOfGear, Stack<Gear>> entity : gears.entrySet()) {
      gearNum.put(entity.getKey(), entity.getValue().size());
    }
  }
  
  /**
  * This method is to help create gears with negative effects for the equipment bag.
  * @param count  the number of gears with negative effects
  * @param array  the total number of each type of gears
  */
  private void createNegativeGears(int count) {
    int index = 1;
    while (count > 0) {
      TypeOfGear type = randomGear.getGear();
      if (!gears.containsKey(type) || (gears.get(type).size() < gearNum.get(type))) {
        Gear gear = Gear.createGear(index, type, TypeOfEffect.NEGATIVE);
        gears.computeIfAbsent(type, k -> new Stack<>()).push(gear);
      } 
      index++;
      count--;
    }
  }
 
  /**
  * This method is to create gears with positive effects for the equipment bag.
  * @param array the total number of each type of gears that needs to be created
  */
  private void createPositiveGears() {
    int index = (int) (size * (negativePercent / 100)) + 1;
    for (Map.Entry<TypeOfGear, Integer> entity : gearNum.entrySet()) {
      TypeOfGear type = entity.getKey();
      int num = entity.getValue();
      while (!gears.containsKey(type) || gears.get(type).size() < num) {
        Gear gear = Gear.createGear(index, type, TypeOfEffect.POSITIVE);
        gears.computeIfAbsent(type, k -> new Stack<>()).push(gear);
        index++;
      }  
    }
  }
 
 
  @Override
  public EquipmentBag requestEquipment() {
    Map<TypeOfGear, Stack<Gear>> playerGears = new HashMap<>();
    int count = 20;
    while (count > 0) {
      TypeOfGear type = randomGear.getGear();
      if (gears.containsKey(type)) {
        Stack<Gear> stack = gears.get(type);
        if (!stack.isEmpty()) {
          Gear g = stack.pop();
          playerGears.computeIfAbsent(type, k -> new Stack<>()).push(g);
          count--;
        }
      }
    }
    return new EquipmentBagImpl(playerGears);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    PriorityQueue<Map.Entry<TypeOfGear, Stack<Gear>>> queue = 
        new PriorityQueue<>((a, b) -> (a.getKey().getIndex() - b.getKey().getIndex()));
    for (Map.Entry<TypeOfGear, Stack<Gear>> gear : gears.entrySet()) {
      queue.offer(gear);
    }
    while (!queue.isEmpty()) {
      Map.Entry<TypeOfGear, Stack<Gear>> gear = queue.poll();
      TypeOfGear gearType = gear.getKey();
      Stack<Gear> stack = gear.getValue();
      Iterator<Gear> values = stack.iterator();
      sb.append(gearType.getName() + ":[ ");
      while (values.hasNext()) {
        sb.append(values.next().toString() + "; ");
      }
      sb.append("]\n");
    }
    return sb.toString();
  }

  @Override
  public Map<TypeOfGear, Stack<Gear>> getGears() {
    return this.gears;
  }

  
}
