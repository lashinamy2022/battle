package player;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import enumerate.TypeOfGear;
import equipment.EquipmentBag;
import equipment.EquipmentBagImpl;
import org.junit.Before;
import org.junit.Test;
import random.MockRandomGenerator;
import random.RandomGenerator;
import random.RealRandomGenerator;

/**
 * This class is to test the correctness of all the functions supported by {@link Player}.
 */
public class PlayerTest {
  
  private RandomGenerator strikingPowerMockS;
  private RandomGenerator avoidAbilityMockS;
  private RandomGenerator weaponMockS;
  private RandomGenerator rollMockS;
  private RandomGenerator strikingPowerMockL;
  private RandomGenerator avoidAbilityMockL;
  private RandomGenerator weaponMockL;
  private RandomGenerator rollMockL;
  private RandomGenerator randomGear = new RealRandomGenerator(1, 6);
  private EquipmentBag battleBag = new EquipmentBagImpl(randomGear);
  
  /**
   *  Setting up objects for all tests.
   */
  @Before
  public void setup() {
    strikingPowerMockS = new MockRandomGenerator(1, 10, 1);
    avoidAbilityMockS = new MockRandomGenerator(1, 6, 1);
    weaponMockS = new MockRandomGenerator("weapon", 2);
    rollMockS = new MockRandomGenerator(1, 6, 2);
    
    strikingPowerMockL = new MockRandomGenerator(1, 10, 10);
    avoidAbilityMockL = new MockRandomGenerator(1, 6, 6);
    weaponMockL = new MockRandomGenerator("weapon", 5);
    rollMockL = new MockRandomGenerator(1, 6, 6);
    
  }

  /**
   * This is to test the player's basic abilities are within acceptable ranges.
   */
  @Test
  public void testBasicAbility() {
    for (int k = 0; k < 100; k++) {
      Player player = new PlayerImpl("Jack", weaponMockL, 
           new RealRandomGenerator(1, 6), 
           strikingPowerMockL, avoidAbilityMockL);
      String result = player.printBasicAbilities();
      int total = 0;
      String[] arr = result.split(",");
      for (int i = 0; i < arr.length; i++) {
        total += Integer.parseInt(arr[i].split(":")[1]);
      }
      assertTrue(total <= 24  && total >= 8);
    }
  }
  
  
  /**
   * This is to test the player's ability are only temporarily changed because of the gear 
   *  and will return to their original basic values after 3 attacks.
   */
  @Test
  public void testChangeInAbilityByUsingGears() {
    RandomGenerator randomGear = new RealRandomGenerator(1, 6);
    EquipmentBag battleBagA = new EquipmentBagImpl(randomGear);
    //create playerA with small random values
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    playerA.requestEquipment(battleBagA);
    
    String initialAbility = playerA.printTotalAbilities();
    playerA.initEquip();
    String abilityAfterUsingGears = playerA.printTotalAbilities();
    assertTrue(!initialAbility.equals(abilityAfterUsingGears));
    
    playerA.requestWeapon();
    
    //create playerB with large random values
    Player playerB = new PlayerImpl("Jerry", weaponMockL, rollMockL, 
        strikingPowerMockL, avoidAbilityMockL);
    playerB.requestEquipment(battleBagA);
    playerB.initEquip();
    playerB.requestWeapon();
    
    //As playerA has small abilities so that it won't successfully attack its opponent,
    //which means there won't be any damage produced during the process
    for (int i = 0; i < 3; i++) {
      if (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
        playerA.attack(playerB);
      }
    }
    //the temporary abilities brought by using gears will disappear after three turns
    String abilityAfter3Attacks = playerA.printTotalAbilities();
    //so after 3 attacks, the abilities should return to the initial values
    assertTrue(initialAbility.equals(abilityAfter3Attacks));
  }
  
  /**
   * This is to test whether the method getHealth()
   * in the {@link Player} can return values correctly.
   */
  @Test
  public void testGetHealth() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    assertEquals(8, playerA.getHealth());
    
    RandomGenerator randomGear = new RealRandomGenerator(1, 6);
    EquipmentBag battleBagA = new EquipmentBagImpl(randomGear);
    playerA.requestEquipment(battleBagA);
    playerA.initEquip();
    
    int basic = playerA.getTotalBasicAbilities();

    int total = playerA.getTotalTempAbility();
    assertEquals(basic + total, playerA.getHealth());
  }
  
  /**
   * This is to test whether the method getStrikingPower()
   * in the {@link Player} can return the correct answer.
   */
  @Test
  public void testGetStrikingPower() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockL, avoidAbilityMockS);
    int strength = playerA.getTotalStrength() + 10;
    assertEquals(strength, playerA.getStrikingPower());
    
    playerA.requestEquipment(battleBag);
    playerA.initEquip();
    strength = playerA.getTotalStrength() + 10;
    assertEquals(strength, playerA.getStrikingPower());
  }
  
  /**
   * This is to test whether the method getAvoidanceAbility()
   * in the {@link Player} can return the correct answer.
   */
  @Test
  public void testGetAvoidanceAbility() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockL);
    int dexterity = playerA.getTotalDexterity() + 6;
    assertEquals(dexterity, playerA.getAvoidanceAbility());
    
    playerA.requestEquipment(battleBag);
    playerA.initEquip();
    dexterity = playerA.getTotalDexterity() + 6;
    assertEquals(dexterity, playerA.getAvoidanceAbility());
  }
  
  /**
   * This is to test whether the method getPotentialDamage()
   * in the {@link Player} can return the correct answer.
   */
  @Test
  public void testGetPotentialDamage() {
    //create a player and give it a broadsword as its weapon
    //broadsword damage is between 6~10
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    playerA.requestWeapon();
    int diff = playerA.getPotentialDamage() - playerA.getTotalStrength();
    assertTrue(diff >= 6 && diff <= 10);
    
    playerA.requestEquipment(battleBag);
    playerA.initEquip();
    diff = playerA.getPotentialDamage() - playerA.getTotalStrength();
    assertTrue(diff >= 6 && diff <= 10);
  }
  
  /**
   * This is to test if the getHealth() can return correct answer when
   * actual damage is greater than zero.
   */
  @Test
  public void testGetHealthAfterDeductActualDamage() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    playerA.requestWeapon();
    Player playerB = new PlayerImpl("Jerry", weaponMockL, rollMockL, 
        strikingPowerMockL, avoidAbilityMockL);
    playerB.requestWeapon();
    
    int originHealth = playerA.getHealth();
    String result = playerB.attack(playerA);
    result = result.substring(result.indexOf("got ") + "got ".length())
        .replace("damage", "").trim();
    int damage = Integer.parseInt(result);
    int expected = originHealth - damage;
    if (expected < 0) {
      expected = 0;
    }
    assertEquals(expected, playerA.getHealth());
  }
  
  /**
   * This is to test whether it will affect the health value if the actual damage is smaller than 0.
   */
  @Test
  public void testGetHealthWithNegativeActualDamage() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    playerA.requestWeapon();
    Player playerB = new PlayerImpl("Jerry", weaponMockL, rollMockL, 
        strikingPowerMockL, avoidAbilityMockL);
    playerB.requestWeapon();
    //equip player A unitl playerB.getStrikingPower() > playerA.getAvoidanceAbility()
    // and playerA.getTotalConstitution() > playerB.getPotentialDamage()
    while (playerB.getStrikingPower() <= playerA.getAvoidanceAbility()
       || playerA.getTotalConstitution() <= playerB.getPotentialDamage()) {
      EquipmentBag bag = new EquipmentBagImpl(randomGear);
      playerA.requestEquipment(bag);
      playerA.initEquip();
    }
    int healthA = playerA.getHealth();
    playerB.attack(playerA);
    assertEquals(healthA, playerA.getHealth());  
  }
  
  /**
   * This is test  if a player is created with only their basic abilities and their bare hands.
   */
  @Test
  public void testPlayerWithOnlyBasicAbility() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    assertEquals(0, playerA.getTotalTempAbility());
    int expected = playerA.getTotalCharisma()
        + playerA.getTotalConstitution()
        + playerA.getTotalDexterity()
        + playerA.getTotalStrength();
    assertEquals(expected, playerA.getTotalBasicAbilities());
    assertEquals("", playerA.printWeapon());
  }
  
  /**
   * This is to test whether a player can wear 2 headgears.
   */
  @Test(expected = IllegalStateException.class)
  public void testWear2HeadGear() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    //to make sure there are 2 headgears in the player's equipment bag
    while (!playerA.printEquipmentBag().contains(TypeOfGear.HEADGEAR.getName() + ":" + 2)) {
      EquipmentBag bag = new EquipmentBagImpl(randomGear);
      playerA.requestEquipment(bag);
    }
    //call initEquip() to wear the first headgear
    playerA.initEquip();
    //make sure the player has worn a headgear
    assertTrue(playerA.printEquippedGears().contains(TypeOfGear.HEADGEAR.getName()));
    //to wear another headgear in the bag, it will throw an error
    playerA.useGear(TypeOfGear.HEADGEAR);
  }
  
  /**
   * This is to test whether a player can wear 2 headgears.
   */
  @Test(expected = IllegalStateException.class)
  public void testWear2Footwear() {
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    
    //to make sure there are 2 footwear in the player's equipment bag
    while (!playerA.printEquipmentBag().contains(TypeOfGear.FOOTWEAR.getName() + ":" + 2)) {
      EquipmentBag bag = new EquipmentBagImpl(randomGear);
      playerA.requestEquipment(bag);
    }
    //call initEquip() to wear the first footwear
    playerA.initEquip();
    //make sure the player is wearing a footwear
    assertTrue(playerA.printEquippedGears().contains(TypeOfGear.FOOTWEAR.getName()));
    //to wear another footwear in the bag, it will throw an error
    playerA.useGear(TypeOfGear.FOOTWEAR);
  }
  
  /**
   * This is to test if a player can wear exactly 10 unit belts if it has 10 unit belts in its bag.
   * And to test whether can wear one more small belt after wearing 10 unit belts.
   */
  @Test(expected = IllegalStateException.class)
  public void testWearUnitBelt() {
    // to test wear exactly 10 unit belts.
    Player playerA = new PlayerImpl("Tom", weaponMockS, rollMockS, 
        strikingPowerMockS, avoidAbilityMockS);
    
    //to make sure there are 11 unit belts in the player's equipment bag
    while (!(playerA.printEquipmentBag().contains(TypeOfGear.SBELT.getName() + ":" + 3)
            && playerA.printEquipmentBag().contains(TypeOfGear.LBELT.getName() + ":" + 2))) {
      EquipmentBag bag = new EquipmentBagImpl(randomGear);
      playerA.requestEquipment(bag);
    }
    playerA.initEquip();
    String equipped = playerA.printEquippedGears();
    int total = 0;
    while (equipped.contains("BELT")) {
      if (equipped.contains("SMALL BELT")) {
        total += 1;
        equipped = equipped.replaceFirst("SMALL BELT", "");
      }
      if (equipped.contains("MEDIUM BELT")) {
        total += 2;
        equipped = equipped.replaceFirst("MEDIUM BELT", "");
      }
      if (equipped.contains("LARGE BELT")) {
        total += 4;
        equipped = equipped.replaceFirst("LARGE BELT", "");
      }
    }
    assertEquals(10, total);
    //to test wear 11 unit belt
    playerA.useGear(TypeOfGear.SBELT);
  }
}
