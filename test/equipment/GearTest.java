package equipment;

import static org.junit.Assert.assertEquals;

import enumerate.TypeOfAbility;
import enumerate.TypeOfBelt;
import enumerate.TypeOfEffect;
import enumerate.TypeOfGear;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import random.MockRandomGenerator;
import random.RandomGenerator;

/**
 * This class contains all the unit test for various kinds of gears.
 */
public class GearTest {
  
  private Gear sbeltp;
  private Gear sbeltn;
  
  private Gear mbeltp;
  private Gear mbeltn;
  
  private Gear lbeltp;
  private Gear lbeltn;
  
  private Gear footwearp;
  private Gear footwearn;
  
  private Gear headgearp;
  private Gear headgearn;
  
  private Gear potionp;
  private Gear potionn;
  
  
  private String sbeltpNo;
  private String sbeltnNo;
  
  private String mbeltpNo;
  private String mbeltnNo;
  
  private String lbeltpNo;
  private String lbeltnNo;
  
  private String footwearpNo;
  private String footwearnNo;
  
  private String headgearpNo;
  private String headgearnNo;
  
  private String potionpNo;
  private String potionnNo;
  
  /**
   * Setting up objects for all tests.
   */
  @Before
  public void setup() {
    sbeltpNo = "SBELT-" + new Date().getTime();
    sbeltp = new Belt(sbeltpNo, TypeOfEffect.POSITIVE, TypeOfBelt.SMALL);
    sbeltnNo = "SBELT-" + new Date().getTime();
    sbeltn = new Belt(sbeltnNo, TypeOfEffect.NEGATIVE, TypeOfBelt.SMALL);
    
    mbeltpNo = "MBELT-" + new Date().getTime();
    mbeltp = new Belt(mbeltpNo, TypeOfEffect.POSITIVE, TypeOfBelt.MEDIUM);
    mbeltpNo = "MBELT-" + new Date().getTime();
    mbeltn = new Belt(mbeltnNo, TypeOfEffect.NEGATIVE, TypeOfBelt.MEDIUM);
    
    lbeltpNo = "LBELT-" + new Date().getTime();
    lbeltp = new Belt(lbeltpNo, TypeOfEffect.POSITIVE, TypeOfBelt.LARGE);
    lbeltnNo = "LBELT-" + new Date().getTime();
    lbeltn = new Belt(lbeltnNo, TypeOfEffect.NEGATIVE, TypeOfBelt.LARGE);
    
    RandomGenerator positiveRandom = new MockRandomGenerator(1, 5, 3);
    RandomGenerator negativeRandom = new MockRandomGenerator(-3, -1, -1);
    
    footwearpNo = "FOOTWEAR-" + new Date().getTime();
    footwearp = new Footwear(footwearpNo, TypeOfEffect.POSITIVE, positiveRandom, negativeRandom);
    footwearnNo = "FOOTWEAR-" + new Date().getTime();
    footwearn = new Footwear(footwearnNo,  TypeOfEffect.NEGATIVE, positiveRandom, negativeRandom);
    
    headgearpNo = "HEADGEAR-" + new Date().getTime();
    headgearp = new Headgear(headgearpNo, TypeOfEffect.POSITIVE, positiveRandom, negativeRandom);
    headgearnNo = "HEADGEAR-" + new Date().getTime();
    headgearn = new Headgear(headgearnNo, TypeOfEffect.NEGATIVE, positiveRandom, negativeRandom);
    
    positiveRandom = new MockRandomGenerator(1, 10, 2);
    RandomGenerator abilityRandom = new MockRandomGenerator("ability", 1);
    potionpNo = "POTION-" + new Date().getTime();
    potionp = new Potion(potionpNo, TypeOfEffect.POSITIVE, positiveRandom,
        negativeRandom, abilityRandom);
    potionnNo = "POTION-" + new Date().getTime();
    potionn = new Potion(potionnNo, TypeOfEffect.NEGATIVE, positiveRandom, 
        negativeRandom, abilityRandom);
  }
  
  /**
   * This method is to test whether all kinds of gears have been instantiated correctly.
   */
  @Test
  public void testGears() {
    String expected = "no:" + sbeltpNo + ",unit:1,effect:positive,limit:10";
    assertEquals(expected, sbeltp.toString());
    
    expected = "no:" + sbeltnNo + ",unit:1,effect:negative,limit:10";
    assertEquals(expected, sbeltn.toString());
    
    expected = "no:" + mbeltpNo + ",unit:2,effect:positive,limit:10";
    assertEquals(expected, mbeltp.toString());
    
    expected = "no:" + mbeltnNo + ",unit:2,effect:negative,limit:10";
    assertEquals(expected, mbeltn.toString());
    
    expected = "no:" + lbeltpNo + ",unit:4,effect:positive,limit:10";
    assertEquals(expected, lbeltp.toString());
    
    expected = "no:" + lbeltnNo + ",unit:4,effect:negative,limit:10";
    assertEquals(expected, lbeltn.toString());
    
    expected = "no:" + footwearpNo + ",effect:positive,limit:1";
    assertEquals(expected, footwearp.toString());
    
    expected = "no:" + footwearnNo + ",effect:negative,limit:1";
    assertEquals(expected, footwearn.toString());
    
    expected = "no:" + headgearpNo + ",effect:positive,limit:1";
    assertEquals(expected, headgearp.toString());
    
    expected = "no:" + headgearnNo + ",effect:negative,limit:1";
    assertEquals(expected, headgearn.toString());
    
    expected = "no:" + potionpNo + ",effect:positive,limit:-1";
    assertEquals(expected, potionp.toString());
    
    expected = "no:" + potionnNo + ",effect:negative,limit:-1";
    assertEquals(expected, potionn.toString());
  }
  
  
  /**
   * This method is to test the effect of using all kinds of belts.
   */
  @Test
  public void testUseBelt() {
    Map<String, Object> expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  1);
    expected.put(TypeOfAbility.CHARISMA.getName(), 1);
    expected.put("gearType", TypeOfGear.SBELT.getName());
    Map<String, Map<String, Object>> effects = sbeltp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  -1);
    expected.put(TypeOfAbility.CHARISMA.getName(), -1);
    expected.put("gearType", TypeOfGear.SBELT.getName());
    effects = sbeltn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  2);
    expected.put(TypeOfAbility.CHARISMA.getName(), 2);
    expected.put("gearType", TypeOfGear.MBELT.getName());
    effects = mbeltp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  -2);
    expected.put(TypeOfAbility.CHARISMA.getName(), -2);
    expected.put("gearType", TypeOfGear.MBELT.getName());
    effects = mbeltn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
   
    expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  4);
    expected.put(TypeOfAbility.CHARISMA.getName(), 4);
    expected.put("gearType", TypeOfGear.LBELT.getName());
    effects = lbeltp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  -4);
    expected.put(TypeOfAbility.CHARISMA.getName(), -4);
    expected.put("gearType", TypeOfGear.LBELT.getName());
    effects = lbeltn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
  }
  
  /**
   * This method is to test the effect of using footwear.
   */
  @Test
  public void testUseFootwear() {
    Map<String, Object> expected =  new HashMap<>();
    expected.put(TypeOfAbility.DEXTERITY.getName(),  3);
    expected.put("gearType", TypeOfGear.FOOTWEAR.getName());
    Map<String, Map<String, Object>> effects = footwearp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected.put(TypeOfAbility.DEXTERITY.getName(),  -1);
    effects = footwearn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
  }
  
  /**
   * This method is to test the effect of using headgear.
   */
  @Test
  public void testUseHeadgear() {
    Map<String, Object> expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  3);
    expected.put("gearType", TypeOfGear.HEADGEAR.getName());
    Map<String, Map<String, Object>> effects = headgearp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  -1);
    expected.put("gearType", TypeOfGear.HEADGEAR.getName());
    effects = headgearn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
  }
  
  /**
   * This method is to test the effect of using potion.
   */
  @Test
  public void testUsePotion() {
    Map<String, Object> expected =  new HashMap<>();
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  2);
    expected.put("gearType", TypeOfGear.POTION.getName());
    
    Map<String, Map<String, Object>> effects = potionp.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
    
    expected.put(TypeOfAbility.CONSTITUTION.getName(),  -1);
    effects = potionn.use();
    for (Map.Entry<String, Map<String, Object>> entity : effects.entrySet()) {
      Map<String, Object> info = entity.getValue();
      assertEquals(expected.toString(), info.toString());
    }
  }
  
  /**
   * This is to test whether the method getLimit() could return the limitation of each type of
   * gears correctly.
   */
  @Test
  public void testGetLimit() {
    assertEquals(10, sbeltp.getLimit());
    assertEquals(10, sbeltn.getLimit());

    assertEquals(10, mbeltp.getLimit());
    assertEquals(10, mbeltn.getLimit());
    
    assertEquals(10, lbeltp.getLimit());
    assertEquals(10, lbeltn.getLimit());
    
    assertEquals(1, headgearp.getLimit());
    assertEquals(1, headgearn.getLimit());
    
    assertEquals(-1, potionp.getLimit());
    assertEquals(-1, potionn.getLimit());
    
    assertEquals(1, footwearp.getLimit());
    assertEquals(1, footwearn.getLimit());
  }
}
