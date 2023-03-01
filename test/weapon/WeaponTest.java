package weapon;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import enumerate.TypeOfAbility;
import enumerate.TypeOfWeapon;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import random.RandomGenerator;
import random.RealRandomGenerator;

/**
 * This class is to test whether all the functions 
 * supported by {@link Weapon} and its subclass is well.
 */
public class WeaponTest {
  
  private Weapon axe;
  private Weapon broadsword;
  private Weapon flail;
  private Weapon katanas;
  private Weapon twoHandedSword;

  /**
   * Setting up objects for all tests.
   */
  @Before
  public void setup() {
    axe = new Axe(createRandom6To10());
    broadsword = new Broadsword(createRandom6To10());
    flail = new Flail(createRandom8To12());
    katanas = new Katanas(createRandom4To6());
    twoHandedSword = new TwoHandedSword(createRandom8To12());
  }
  
  /**
   * This is to helper which helps to create a random generator 
   * with the lower and upper boundaries are 6,10 respectively.
   * @param expextedValue the value you want
   * @return the {@link RandomGenerator} object
   */
  private RandomGenerator createRandom6To10() {
    return new RealRandomGenerator(6, 10);
  }
  
  /**
   * This is to helper which helps to create a random generator 
   * with the lower and upper boundaries are 8,12 respectively.
   * @param expextedValue the value you want
   * @return the {@link RandomGenerator} object
   */
  private RandomGenerator createRandom8To12() {
    return new RealRandomGenerator(8, 12);
  }
  
  /**
   * This is to helper which helps to create a random generator 
   * with the lower and upper boundaries are 4,6 respectively.
   * @param expextedValue the value you want
   * @return the {@link RandomGenerator} object
   */
  private RandomGenerator createRandom4To6() {
    return new RealRandomGenerator(4, 6);
  }
  
  /**
   * This is to test whether the constructor can create weapons correctly.
   */
  @Test
  public void testCreateWeapons() {
    String expected = "weapon: an axe, damage: 6~10";
    assertEquals(expected, axe.toString());
    expected = "weapon: a broadsword, damage: 6~10";
    assertEquals(expected, broadsword.toString());
    expected = "weapon: a flail, damage: 8~12, ability: DEXTERITY, threshold: 14";
    assertEquals(expected, flail.toString());
    expected = "weapon: a pair of katanas, damage: 4~6";
    assertEquals(expected, katanas.toString());
    expected = "weapon: a two-handed sword, damage: 8~12, ability: STRENGTH, threshold: 14";
    assertEquals(expected, twoHandedSword.toString());
  }
  
  /**
   * This to test if the factory method can instantiate various weapons correctly.
   */
  @Test
  public void testFactoryMethod() {
    Weapon axef = Weapon.createWeapon(TypeOfWeapon.AXE);
    assertEquals("weapon: an axe, damage: 6~10", axef.toString());
    
    Weapon broadswordf = Weapon.createWeapon(TypeOfWeapon.BROADSWORD);
    assertEquals("weapon: a broadsword, damage: 6~10", broadswordf.toString());
    
    Weapon flailf = Weapon.createWeapon(TypeOfWeapon.FLAIL);
    assertEquals("weapon: a flail, damage: 8~12, ability: DEXTERITY, threshold: 14", 
        flailf.toString());
    
    Weapon katanasf = Weapon.createWeapon(TypeOfWeapon.KATANAS);
    assertEquals("weapon: a pair of katanas, damage: 4~6", katanasf.toString());
    
    Weapon twoHandedSwordf = Weapon.createWeapon(TypeOfWeapon.TWOHANDEDSWORD);
    assertEquals("weapon: a two-handed sword, damage: 8~12, ability: STRENGTH, threshold: 14", 
        twoHandedSwordf.toString());
  }
 
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using an axe.
   */
  @Test
  public void testGetDamageUsingAxe() {
    int damage = axe.getDamage(new HashMap<TypeOfAbility, Integer>());
    assertTrue(damage >= 6 && damage <= 10);
  }
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a broadsword.
   */
  @Test
  public void testGetDamageUsingBroadsword() {
    int damage = broadsword.getDamage(new HashMap<TypeOfAbility, Integer>());
    assertTrue(damage >= 6 && damage <= 10);
  }
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a flail with enough dexterity.
   */
  @Test
  public void testGetDamageUsingFlailWithEnoughDexterity() {
    Map<TypeOfAbility, Integer> playerAbilities = new HashMap<>();
    playerAbilities.put(TypeOfAbility.STRENGTH, 10);
    playerAbilities.put(TypeOfAbility.CHARISMA, 10);
    playerAbilities.put(TypeOfAbility.CONSTITUTION, 10);
    playerAbilities.put(TypeOfAbility.DEXTERITY, 15);
    
    int damage = flail.getDamage(playerAbilities);
    assertTrue(damage >= 8 && damage <= 12);
  }
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a flail without enough dexterity.
   */
  @Test
  public void testGetDamageUsingFlailWithoutEnoughDexterity() {
    Map<TypeOfAbility, Integer> playerAbilities = new HashMap<>();
    playerAbilities.put(TypeOfAbility.STRENGTH, 10);
    playerAbilities.put(TypeOfAbility.CHARISMA, 10);
    playerAbilities.put(TypeOfAbility.CONSTITUTION, 10);
    playerAbilities.put(TypeOfAbility.DEXTERITY, 14);
    int damage = flail.getDamage(playerAbilities);
    assertTrue(damage >= 4 && damage <= 6);
  }
  
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a pair of katanas.(it should return double damage)
   */
  @Test
  public void testGetDamageUsingKatanas() {
    int damage = katanas.getDamage(new HashMap<TypeOfAbility, Integer>());
    assertTrue(damage >= 8 && damage <= 12);
  }
  
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a two-handed sword with enough strength.
   */
  @Test
  public void testGetDamageUsingTwoHandedSwordWithEnoughStrength() {
    Map<TypeOfAbility, Integer> playerAbilities = new HashMap<>();
    playerAbilities.put(TypeOfAbility.STRENGTH, 20);
    playerAbilities.put(TypeOfAbility.CHARISMA, 10);
    playerAbilities.put(TypeOfAbility.CONSTITUTION, 10);
    playerAbilities.put(TypeOfAbility.DEXTERITY, 14);
    
    int damage = twoHandedSword.getDamage(playerAbilities);
    assertTrue(damage >= 8 && damage <= 12);
  }
  
  /**
   * This is to test whether the method getDamage can return the correct damage when 
   * using a two-handed sword with enough strength.
   */
  @Test
  public void testGetDamageUsingTwoHandedSwordWithoutEnoughStrength() {
    Map<TypeOfAbility, Integer> playerAbilities = new HashMap<>();
    playerAbilities.put(TypeOfAbility.STRENGTH, 10);
    playerAbilities.put(TypeOfAbility.CHARISMA, 10);
    playerAbilities.put(TypeOfAbility.CONSTITUTION, 10);
    playerAbilities.put(TypeOfAbility.DEXTERITY, 14);
    
    int damage = twoHandedSword.getDamage(playerAbilities);
    assertTrue(damage >= 4 && damage <= 6);
  }

}
