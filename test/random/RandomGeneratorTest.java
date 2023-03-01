package random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import enumerate.TypeOfAbility;
import enumerate.TypeOfGear;
import enumerate.TypeOfWeapon;
import org.junit.Test;


/**
 * This class is to test the functions of {@link RandomGenerator}.
 *
 */
public class RandomGeneratorTest {
  
  /**
   * This is to test if getInt() in the {@link RealRandomGenerator} 
   * can return the value between the lower and the upper.
   */
  @Test
  public void testGetIntWithRealRandom() {
    RandomGenerator realRandom  = new RealRandomGenerator(3, 10);
    for (int i = 0; i < 100; i++) {
      int value = realRandom.getInt();
      assertTrue(value >= 3 && value <= 10);
    }
    
    realRandom  = new RealRandomGenerator(-3, -1);
    for (int i = 0; i < 100; i++) {
      int value = realRandom.getInt();
      assertTrue(value >= -3 && value <= -1);
    }
  }
 
  /**
   * This is to test if getHalfValue() in the {@link RealRandomGenerator} 
   * can return the value between 
   * the half lower and the half upper.
   */
  @Test
  public void testGetHalfIntWithRealRandom() {
    RandomGenerator realRandom  = new RealRandomGenerator(8, 12);
    for (int i = 0; i < 100; i++) {
      int value = realRandom.getHalfValue();
      assertTrue(value >= 4 && value <= 6);
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMockRandom() {
    new MockRandomGenerator(3, 10, 2);
    new MockRandomGenerator(3, 10, 11);
  }
  
  /**
   * This is to test if getInt() in the {@link MockRandomGenerator} can return the fixed value.
   */
  @Test
  public void testGetIntWithMockRandom() {
    RandomGenerator mockRandom  = new MockRandomGenerator(8, 12, 8);
    assertEquals(8, mockRandom.getInt());
  }
  
  
  /**
   * This is to test if the method getInt() in the {@link MockRandomGenerator}
   *  can return the half fixed value.
   */
  @Test
  public void testGetHalfIntWithMockRandom() {
    RandomGenerator mockRandom  = new MockRandomGenerator(4, 6, 5);
    assertEquals(5, mockRandom.getHalfValue());
  }
  
  /**
   * This is to test if the method getAbility() in the {@link RealRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetAbilityWithRealRandom() {
    RandomGenerator random = new RealRandomGenerator("ability");
    for (int i = 0; i < 100; i++) {
      TypeOfAbility ability =  random.getAbility();
      assertTrue(ability != null);
    }
  }
  
  /**
   * This is to test if the method getAbility() in the {@link MockRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetAbilityWithMockRandom() {
    RandomGenerator random = new MockRandomGenerator("ability", 1);
    TypeOfAbility ability =  random.getAbility();
    assertEquals(TypeOfAbility.CONSTITUTION, ability);
  }
  
  /**
   * This is to test if the method getGear() in the {@link RealRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetGearWithRealRandom() {
    RandomGenerator random = new RealRandomGenerator("gear");
    for (int i = 0; i < 100; i++) {
      TypeOfGear gear =  random.getGear();
      assertTrue(gear != null);
    }
  }
  
  /**
   * This is to test if the method getGear() in the {@link MockRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetGearWithMockRandom() {
    RandomGenerator random = new MockRandomGenerator("gear", 1);
    TypeOfGear gear =  random.getGear();
    assertEquals(TypeOfGear.HEADGEAR, gear);
  }
  
  /**
   * This is to test if the method getWeapon() in the {@link RealRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetWeaponWithRealRandom() {
    RandomGenerator random = new RealRandomGenerator("weapon");
    for (int i = 0; i < 100; i++) {
      TypeOfWeapon weapon =  random.getWeapon();
      assertTrue(weapon != null);
    }
  }
  
  /**
   * This is to test if the method getWeapon() in the {@link MockRandomGenerator} 
   * can return the ability correctly.
   */
  @Test
  public void testGetWeaponWithMockRandom() {
    RandomGenerator random = new MockRandomGenerator("weapon", 1);
    TypeOfWeapon weapon =  random.getWeapon();
    assertEquals(TypeOfWeapon.AXE, weapon);
  }

}
