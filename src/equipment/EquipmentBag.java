package equipment;

import enumerate.TypeOfGear;
import java.util.Map;
import java.util.Stack;


/**
 * 
 * This interface provides the functions of a equipment bag should support.
 */
public interface EquipmentBag {
  
  /**
   * This method is for players to request equipment.
   * Each player can get 20 items in their equipment bag after requesting equipment
   * 
   * @return an equipment bag
   */
  EquipmentBag requestEquipment();
  
  /**
   * This method is to get gears from the equipment bag.
   * @return the gear map
   */
  Map<TypeOfGear, Stack<Gear>> getGears();
}
