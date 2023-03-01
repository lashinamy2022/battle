package player;

import enumerate.TypeOfAbility;
import enumerate.TypeOfGear;
import enumerate.TypeOfWeapon;
import equipment.EquipmentBag;
import equipment.Gear;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import random.RandomGenerator;
import weapon.Weapon;

/**
 * This class represents a player. It offers all the operations 
 * mandated by the {@link Player} interface.
 */
public class PlayerImpl implements Player {

  private final String name;
  private Map<TypeOfAbility, Integer> basicAbility;
  private Weapon weapon;
  private EquipmentBag bag; // a equipment bag contains 20 items
  private final int times = 4;
  //{gearNo:{Dexterity:3},{usage:0},{turns:0},{gearType:Headgear}}
  private Map<String, Map<String, Object>>  usageRecord;
  //after attacking the opponent for a fixed times, the gears' effects will disappear
  private int lifeCycleOfGears; 
  private final RandomGenerator randomWeapon;
  private final RandomGenerator randomRoll;
  private final RandomGenerator randomStrikingPower;
  private final RandomGenerator randomAvoidAbility;
  
  /**
   * Construct a player with basic abilities.
   * @param name of the player from user input
   * @param randomWeapon represents the random weapon generator
   * @param randomRoll   represents the random roll generator
   * @param randomStrikingPower represents the generator 
   *       that can generate a random value of striking power 
   * @param randomAvoidAbility  represents the generator 
   *       that can generate a random value of avoidance ability 
   */
  public PlayerImpl(String name, RandomGenerator randomWeapon, RandomGenerator randomRoll, 
      RandomGenerator randomStrikingPower, RandomGenerator randomAvoidAbility) {
    this.name = name;
    this.randomWeapon = randomWeapon;
    this.randomRoll = randomRoll;
    this.randomStrikingPower = randomStrikingPower;
    this.randomAvoidAbility = randomAvoidAbility;
    basicAbility = new HashMap<>();
    usageRecord = new HashMap<>();
    int sum = getBasicAbilityValueByRolling();
    assignAbility(sum);
    lifeCycleOfGears = 3;
  }
  
  @Override
  public void assignAbility(int sum) {
    int strength = (int) (0.3 * sum);
    int dexterity = (int) (0.3 * sum);
    int constitution = (int) (0.2 * sum);
    int charisma = sum - strength - dexterity - constitution;
    basicAbility.put(TypeOfAbility.STRENGTH, strength);
    basicAbility.put(TypeOfAbility.DEXTERITY, dexterity);
    basicAbility.put(TypeOfAbility.CONSTITUTION, constitution);
    basicAbility.put(TypeOfAbility.CHARISMA, charisma);
  }
  
  /**
   * Add the result of 4 times rolling together and assign it to a ability.
   * @return the sum of the 4 times rolling
   */
  private int getBasicAbilityValueByRolling() {
    int result = 0;
    for (int i = 0; i < times; i++) {
      result += rollDice();
    }
    return result;
  }
  
  /**
   * This method is to randomly roll four 6-sided dice, re-rolling any 1s.
   * @return the result of the rolling
   */
  private int rollDice() {
    int r = randomRoll.getInt();
    if (r == 1) {
      r = rollDice();
    } else {
      return r;
    }
    return r;
  }
  
  @Override
  public int getHealth() {
    int health = getTotalStrength() + getTotalConstitution()
                + getTotalDexterity() + getTotalCharisma();
    return health;
  }
  
  /**
   * This method is to get each type of temporary ability the player get from using gears.
   * @param ability represents the ability type
   * @return the value of that kind of temporary ability
   */
  private int getTempAbility(TypeOfAbility ability) {
    int result = 0;
    for (Map.Entry<String, Map<String, Object>> entity : usageRecord.entrySet()) {
      Map<String, Object> info = entity.getValue();
      if (ability.getIndex() == TypeOfAbility.STRENGTH.getIndex()) {
        result += (int) info.getOrDefault(TypeOfAbility.STRENGTH.getName(), 0);
      } else if (ability.getIndex() == TypeOfAbility.CONSTITUTION.getIndex()) {
        result += (int) info.getOrDefault(TypeOfAbility.CONSTITUTION.getName(), 0);
      } else if (ability.getIndex() == TypeOfAbility.DEXTERITY.getIndex()) {
        result += (int) info.getOrDefault(TypeOfAbility.DEXTERITY.getName(), 0);
      } else if (ability.getIndex() == TypeOfAbility.CHARISMA.getIndex()) {
        result += (int) info.getOrDefault(TypeOfAbility.CHARISMA.getName(), 0);
      } 
    }
    return result;
  }
  
  @Override
  public int getStrikingPower() {
    int random = randomStrikingPower.getInt();
    return getTotalStrength() + random;
  }
 
  @Override
  public int getAvoidanceAbility() {
    int random = randomAvoidAbility.getInt();
    return getTotalDexterity() + random;
  }
  
  @Override
  public int getPotentialDamage() {
    Map<TypeOfAbility, Integer> abilities = new HashMap<>();
    abilities.put(TypeOfAbility.STRENGTH, getTotalStrength());
    abilities.put(TypeOfAbility.CONSTITUTION, getTotalConstitution());
    abilities.put(TypeOfAbility.DEXTERITY, getTotalDexterity());
    abilities.put(TypeOfAbility.CHARISMA, getTotalCharisma());
    return getTotalStrength() + weapon.getDamage(abilities);
  }
  
  @Override
  public int getTotalStrength() {
    return basicAbility.get(TypeOfAbility.STRENGTH) + getTempAbility(TypeOfAbility.STRENGTH);
  }
  
  @Override
  public int getTotalDexterity() {
    return basicAbility.get(TypeOfAbility.DEXTERITY) + getTempAbility(TypeOfAbility.DEXTERITY);
  }
  
  @Override
  public int getTotalConstitution() {
    return basicAbility.get(TypeOfAbility.CONSTITUTION) 
        + getTempAbility(TypeOfAbility.CONSTITUTION);
  }
  
  @Override
  public int getTotalCharisma() {
    return basicAbility.get(TypeOfAbility.CHARISMA) + getTempAbility(TypeOfAbility.CHARISMA);
  }
  
  @Override
  public String attack(Player opponent) throws IllegalStateException {
    int actualDamage = 0;
    if (getHealth() <= 0 || opponent.getHealth() <= 0) {
      throw new IllegalStateException("the game is over");
    }
    int strikingPower = getStrikingPower();
    int opponentAvoidance = opponent.getAvoidanceAbility();
    int potentialDamage = getPotentialDamage();
    int opponentConstitution = opponent.getTotalConstitution();
    if (strikingPower > opponentAvoidance) {
      actualDamage = potentialDamage - opponentConstitution;
      if (actualDamage > 0) {
        deductActualDamage(opponent, actualDamage);
      }
    }
    updateUsageRecord();
    StringBuilder sb = new StringBuilder();
    sb.append(this.getName()).append(": ");
    sb.append("strikingPower:").append(strikingPower).append(", ");
    sb.append("potentialDamage:").append(potentialDamage).append("\n");
    
    sb.append(opponent.getName()).append(": ");
    sb.append("avoidanceAbility:").append(opponentAvoidance).append(", ");
    sb.append("constitution:").append(opponentConstitution).append("\n");
    
    if (strikingPower > opponentAvoidance) {
      sb.append(this.getName()).append(" attacked ")
         .append(opponent.getName()).append(" successfully.");
      sb.append(opponent.getName()).append(" got ")
        .append(actualDamage > 0 ? actualDamage : 0).append(" damage");
    } else {
      sb.append(this.getName()).append(" failed to attack ").append(opponent.getName());
    }
    return sb.toString();
  }
 
  /**
   * This is to deduct actual damage from the health, then assign the new health to 4 abilities.
   * @param actualDamage represents the actual damage 
   */
  private void deductActualDamage(Player opponent, int actualDamage) {
    int newHealth = opponent.getTotalBasicAbilities() - actualDamage;
    if (newHealth < 0) {
      newHealth = 0;
    }
    opponent.assignAbility(newHealth);
  }
  
  @Override
  public int getTotalBasicAbilities() {
    int total = 0;
    for (Map.Entry<TypeOfAbility, Integer> entity : basicAbility.entrySet()) {
      total += entity.getValue();
    }
    return total;
  }
  
  /**
   * To record the turns in each type of the gears the player used.
   */
  private void updateUsageRecord() {
    List<String> list = new ArrayList<String>();
    for (Map.Entry<String, Map<String, Object>> entity : usageRecord.entrySet()) {
      String no = entity.getKey();
      Map<String, Object> info = entity.getValue();
      int turns = (Integer) info.getOrDefault("turns", 0);
      if (turns + 1  < lifeCycleOfGears) {
        info.put("turns", turns + 1);
      } else { //when turns + 1 >= lifeCycleOfGears, the effects of the gears should be removed
        list.add(no);
      }
    }
    
    for (int i = 0; i < list.size(); i++) {
      usageRecord.remove(list.get(i));
    }
  }
  
  @Override
  public void initEquip() {
    Map<TypeOfGear, Stack<Gear>> gears = bag.getGears();
    for (Map.Entry<TypeOfGear, Stack<Gear>> gear : gears.entrySet()) {
      TypeOfGear gearType = gear.getKey();
      Stack<Gear> stack = gear.getValue();
      while (!stack.isEmpty()) {
        int limit = stack.peek().getLimit();
        if (checkLimit(gearType, limit)) {
          //{gearNo:{Dexterity:3},{Constitution:5},{usage:1},{turns:0},{gearType:Headgear}}
          Map<String, Map<String, Object>>  map = stack.pop().use();
          updateGearUsage(map, gearType); // record the gear usage
        } else {
          break;
        }
      }
    }
  }
  
  /**
   * This is to check whether the player can use this type of gear.
   * @param type represents the type of gear the player wants to use
   * @param limit represents the limit of this type of gear
   * @return true if the player can use, otherwise false
   */
  private boolean checkLimit(TypeOfGear type, int limit) {
    if (type.getIndex() == TypeOfGear.POTION.getIndex()) {
      return true;
    }
    Map<TypeOfGear, Integer> countMap = new HashMap<>();
    for (Map.Entry<String, Map<String, Object>> entity : usageRecord.entrySet()) {
      Map<String, Object> info = entity.getValue();
      TypeOfGear gearType = (TypeOfGear) info.get("gearType");
      int usage = (int) info.get("usage");
      countMap.put(gearType, countMap.getOrDefault(gearType, 0) + usage);
    }
    int total = 0;
    if (type.toString().endsWith("BELT")) {
      int unit = type.getBeltType().getUnit();
      total = countMap.getOrDefault(TypeOfGear.SBELT, 0)
          + countMap.getOrDefault(TypeOfGear.MBELT, 0)
          + countMap.getOrDefault(TypeOfGear.LBELT, 0);
      return total + unit <= limit;
    }
    return countMap.getOrDefault(type, 0) + 1 <= limit;
  }
 
  /**
   * This method is used to update the usage of gears.
   * @param type represents the type of gears the player used
   */
  private void updateGearUsage(Map<String, Map<String, Object>> map, TypeOfGear gearType) {
    for (Map.Entry<String, Map<String, Object>> entity : map.entrySet()) {
      Map<String, Object> info = entity.getValue();
      int increase = 0;
      if (gearType.getBeltType() != null) {
        increase = gearType.getBeltType().getUnit();
      } else {
        increase = 1;
      }
      info.put("usage", (Integer) info.getOrDefault("usage", 0) + increase);
      info.put("turns", 0);
    }
    usageRecord.putAll(map);
  }
  
  @Override
  public void requestEquipment(EquipmentBag battleBag) {
    bag = battleBag.requestEquipment();
  }
  
  @Override
  public void requestWeapon() {
    TypeOfWeapon type = randomWeapon.getWeapon();
    weapon = Weapon.createWeapon(type);
  }
  
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void useGear(TypeOfGear type) {
    //1. check the bag
    Map<TypeOfGear, Stack<Gear>> gears = bag.getGears();
    if (!gears.containsKey(type)) {
      throw new IllegalStateException("There is no " + type.getName() + " in the bag");
    }
    if (gears.get(type).isEmpty()) {
      throw new IllegalStateException("There is no " + type.getName() + " in the bag");
    }
    //2. check the usage record
    //{gearNo:{Dexterity:3},{usage:0},{turns:0},{gearType:Headgear}}
    int limit = gears.get(type).peek().getLimit();
    if (!checkLimit(type, limit)) {
      throw new IllegalStateException("Cannot use the gear over the limit");
    } else {
      Map<String, Map<String, Object>>  map = gears.get(type).pop().use();
      updateGearUsage(map, type); // record the gear usage
    }
  }

  @Override
  public String printBasicAbilities() {
    StringBuilder sb = new StringBuilder();
    sb.append(TypeOfAbility.STRENGTH.getName() + ":")
        .append(basicAbility.get(TypeOfAbility.STRENGTH) + ",");
    sb.append(TypeOfAbility.CONSTITUTION.getName() + ":")
        .append(basicAbility.get(TypeOfAbility.CONSTITUTION) + ",");
    sb.append(TypeOfAbility.DEXTERITY.getName() + ":")
        .append(basicAbility.get(TypeOfAbility.DEXTERITY) + ",");
    sb.append(TypeOfAbility.CHARISMA.getName() + ":")
        .append(basicAbility.get(TypeOfAbility.CHARISMA));
    return sb.toString();
  }

  @Override
  public String printEquipmentBag() {
    StringBuilder sb = new StringBuilder();
    if (bag == null) {
      return "";
    }
    Map<TypeOfGear, Stack<Gear>> gears = bag.getGears();
    
    for (Map.Entry<TypeOfGear, Stack<Gear>> entity : gears.entrySet()) {
      if (entity.getValue().size() > 0) {
        sb.append(entity.getKey().getName()).append(":");
        sb.append(entity.getValue().size()).append(",");
      }
    }
    return sb.toString();
  }

  @Override
  public String printEquippedGears() {
    StringBuilder sb = new StringBuilder();
    //{gearNo:{Dexterity:3},{usage:0},{turns:0},{gearType:Headgear}}
    PriorityQueue<Map.Entry<String, Map<String, Object>>> queue = new PriorityQueue<>(
        (a, b) -> (((TypeOfGear) a.getValue().get("gearType")).getIndex() 
            - ((TypeOfGear) b.getValue().get("gearType")).getIndex()));
    for (Map.Entry<String, Map<String, Object>> entity : usageRecord.entrySet()) {
      queue.offer(entity);
    }
    while (!queue.isEmpty()) {
      Map.Entry<String, Map<String, Object>> entity = queue.poll();
      Map<String, Object> info = entity.getValue();
      TypeOfGear gearType = (TypeOfGear) info.get("gearType");
      String gearName = gearType.getName();
      if (gearType.getBeltType() != null) {
        gearName = gearType.getBeltType().toString().toUpperCase() + " BELT";
      }
      sb.append("use a ").append(gearName).append(": ");
      String useInfo = "";
      if (info.containsKey(TypeOfAbility.STRENGTH.getName())) {
        String name = TypeOfAbility.STRENGTH.getName();
        int value = (int) info.get(TypeOfAbility.STRENGTH.getName());
        useInfo += name + " " + (value > 0 ? "+" + value : value) + ","; 
      }
      if (info.containsKey(TypeOfAbility.CONSTITUTION.getName())) {
        String name = TypeOfAbility.CONSTITUTION.getName();
        int value = (int) info.get(TypeOfAbility.CONSTITUTION.getName());
        useInfo += name + " " + (value > 0 ? "+" + value : value) + ","; 
      }
      if (info.containsKey(TypeOfAbility.DEXTERITY.getName())) {
        String name = TypeOfAbility.DEXTERITY.getName();
        int value = (int) info.get(TypeOfAbility.DEXTERITY.getName());
        useInfo += name + " " + (value > 0 ? "+" + value : value) + ","; 
      }
      if (info.containsKey(TypeOfAbility.CHARISMA.getName())) {
        String name = TypeOfAbility.CHARISMA.getName();
        int value = (int) info.get(TypeOfAbility.CHARISMA.getName());
        useInfo += name + " " + (value > 0 ? "+" + value : value) + ","; 
      }
     
      sb.append(useInfo);
      sb.append("\n");
    }
    return sb.toString();
  }

  @Override
  public String printTemporaryAbilities() {
    int strength = getTempAbility(TypeOfAbility.STRENGTH);
    int constitution = getTempAbility(TypeOfAbility.CONSTITUTION);
    int dexterity = getTempAbility(TypeOfAbility.DEXTERITY);
    int charisma = getTempAbility(TypeOfAbility.CHARISMA);
    StringBuilder sb = new StringBuilder();
    sb.append(TypeOfAbility.STRENGTH.getName() + ":")
        .append(strength + ",");
    sb.append(TypeOfAbility.CONSTITUTION.getName() + ":")
        .append(constitution + ",");
    sb.append(TypeOfAbility.DEXTERITY.getName() + ":")
        .append(dexterity + ",");
    sb.append(TypeOfAbility.CHARISMA.getName() + ":")
        .append(charisma);
    return sb.toString();
  }

  @Override
  public String printWeapon() {
    if (weapon == null) {
      return "";
    }
    return weapon.toString();
  }

  @Override
  public String printTotalAbilities() {
    StringBuilder sb = new StringBuilder();
    sb.append(TypeOfAbility.STRENGTH.getName() + ":")
        .append(getTotalStrength() + ",");
    sb.append(TypeOfAbility.CONSTITUTION.getName() + ":")
        .append(getTotalConstitution() + ",");
    sb.append(TypeOfAbility.DEXTERITY.getName() + ":")
        .append(getTotalDexterity() + ",");
    sb.append(TypeOfAbility.CHARISMA.getName() + ":")
        .append(getTotalCharisma());
    return sb.toString();
  }

  @Override
  public int getTotalTempAbility() {
    return getTempAbility(TypeOfAbility.STRENGTH) 
        + getTempAbility(TypeOfAbility.CONSTITUTION)
        + getTempAbility(TypeOfAbility.DEXTERITY)
        + getTempAbility(TypeOfAbility.CHARISMA);
  }
}
