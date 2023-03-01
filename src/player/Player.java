package player;

import enumerate.TypeOfGear;
import equipment.EquipmentBag;

/**
 * This interface defines all the operations that a player class should support.
 *
 */
public interface Player {
  
  /**
   * To get a player's health which is calculated to be the sum of their 4 abilities. 
   * @return the sum of each type of abilities
   */
  int getHealth();
  
  /**
   * This is to get the sum of 4 basic abilities.
   * @return the sum
   */
  int getTotalBasicAbilities();
  
  /**
   * This is to get the striking power which is the sum of the strength of the player, 
   * any of the gear that adds (or subtracts) from strength, and a random number 
   * between 1 and 10 (inclusive).
   * @return the striking power
   */
  int getStrikingPower();
  
  /**
   * This is to get the avoidance ability which is the sum of the dexterity of the player,
   * any of the gear that adds (or subtracts) from dexterity, and a random number 
   * between 1 and 6 (inclusive).
   * @return the avoidance ability
   */
  int getAvoidanceAbility();
  
  /**
   * This is to get the total dexterity, including basic dexterity and the temporary dexterity.
   * @return sum of the two kinds of dexterity
   */
  int getTotalDexterity();
   
  /**
   * This is to get the potential damage which is calculated by adding 
   * the strength of the attacking player to a random value in the range 
   * of the damage that their weapon can inflict (if they have a weapon).
   * @return the potential damage
   */
  int getPotentialDamage();
  
  /**
   * This is to get the total charisma, including basic charisma and the temporary charisma.
   * @return the sum of the two kinds of charisma
   */
  int getTotalCharisma();
  
  /**
   * This is to provide a way for the player to attack his/her opponent.
   * @param opponent represents the opponent player
   * @return attack result and other information in a string
   * @throws IllegalStateException when the game is over
   */
  String attack(Player opponent) throws IllegalStateException;
  
  /**
   * This is to initially equip the players once they have got the 20 items of gears.
   * (not all the gears can be used at once, after call initEquip(), there may be other
   * gears left since the limit of some types of gears)
   * For example: if the player got two headgears, and he/she can only use 1 headgear, and 
   * so there will be another headgear left in the player's bag.
   */
  void initEquip();
  
  /**
   * This is to facilitate a player to request equipment for him/her.
   * @param battleBag the equipment bag of the battle
   */
  void requestEquipment(EquipmentBag battleBag);
  
  /**
   * This provides functions of requesting a weapon randomly for a player.
   */
  void requestWeapon();
  
  /**
   * This method is used to assign the new health value to 4 abilities
   * after rolling dices or deducting damages.
   * @param sum the health
   */
  void assignAbility(int sum);
  
  /**
   * This method is to get the player's name.
   * @return the player's name
   */
  String getName();
  
  /**
   * This method is to get the constitution of the player.
   * @return the total constitution of the player
   */
  int getTotalConstitution();
  
  /**
   * This is to get the total strength, including basic strength and the temporary strength.
   * @return sum of the two kinds of strength 
   */
  public int getTotalStrength();
  
  /**
   * This method is provided to facilitate a player to use the rest gears in the bag.
   * @param gear represents the gear type 
   */
  void useGear(TypeOfGear gear);
  
  /**
   * This method is provided to print ability values of the player.
   * @return the ability values
   */
  String printBasicAbilities();
  
  /**
   * This method is to print what gears the player uses after he/she gets the equipment bag.
   * @return the equipped gears information
   */
  String printEquippedGears();
  
  /**
   * This method is provided to print player's equipment information.
   * @return the detail gears inside the bag
   */
  String printEquipmentBag();
  
  /**
   * This method is to print the player's temporary abilities got by using gears.
   * @return the temporary abilities
   */
  String printTemporaryAbilities();
  
  /**
   * This method is to print the player's weapon information.
   * @return weapon name
   */
  String printWeapon();
  
  /**
   * This method is to print the player's total abilities 
   * including basic abilities and temporary abilities.
   * @return the sum of basic ability and temporary ability respectively
   */
  String printTotalAbilities();
  
  /**
   * This method is to get the total sum of temporary abilities.
   * @return the sum
   */
  int getTotalTempAbility();
}
