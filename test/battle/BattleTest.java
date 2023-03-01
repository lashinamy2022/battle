package battle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is to test if all the functions in the {@link Battle} are correct.
 *
 */
public class BattleTest {
  
  private Battle battle;
  
  /**
   * Setting up an object for all tests.
   */
  @Before
  public void setup() {
    battle = new BattleImpl("Jerry", "Tom");
    battle.enterArena();
  }
  
  /**
   * This is to test whether the players has been correctly equipInfo.
   */
  @Test
  public void testInitEquip() {
    String info = battle.printPlayerEquippedGears();
    String equipInfo = info.split("\n\n")[0];
    int headgear = 0;
    int belt = 0;
    int footwear = 0;
    while (equipInfo.contains("HEADGEAR")
          || equipInfo.contains("BELT")
          || equipInfo.contains("FOOTWEAR")) {
      if (equipInfo.contains("SMALL BELT")) {
        belt += 1;
        equipInfo = equipInfo.replaceFirst("SMALL BELT", "");
      }
      if (equipInfo.contains("MEDIUM BELT")) {
        belt += 2;
        equipInfo = equipInfo.replaceFirst("MEDIUM BELT", "");
      }
      if (equipInfo.contains("LARGE BELT")) {
        belt += 4;
        equipInfo = equipInfo.replaceFirst("LARGE BELT", "");
      }
      
      if (equipInfo.contains("HEADGEAR")) {
        headgear += 1;
        equipInfo = equipInfo.replaceFirst("HEADGEAR", "");
      }
      if (equipInfo.contains("FOOTWEAR")) {
        footwear += 1;
        equipInfo = equipInfo.replaceFirst("FOOTWEAR", "");
      }
    }
    assertTrue(headgear < 2);
    assertTrue(footwear < 2);
    assertTrue(belt < 11);
    String restGears =  battle.printPlayerEquipmentBag();
    assertTrue(!restGears.contains("POTION"));
  }
  
  /**
   * This is to test whether getTurn() can return correclty.
   */
  @Test
  public void testGetTurn() {
    String total = battle.printPlayerTotalAbilitites();
    String[] info = total.split("\n");
    String jerry = info[0];
    String tom = info[1];
    String jerryCharisma = jerry.split(",")[3].split(":")[1];
    String tomCharisma = tom.split(",")[3].split(":")[1];
    String expected = Integer.parseInt(jerryCharisma) 
                           > Integer.parseInt(tomCharisma)
                                      ? "Jerry" : "Tom";
    assertEquals(expected, battle.getTurn().getName());
    battle.fight();
    expected = ("Jerry").equals(expected) ? "Tom" : "Jerry";
    assertEquals(expected, battle.getTurn().getName());

  }

}
