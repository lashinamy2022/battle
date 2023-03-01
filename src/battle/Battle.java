package battle;

import player.Player;

/**
 * This interface provides all the functions that battle game should support.
 *
 */
public interface Battle {
  
  /**
   * This method is provided a way for players to enter the arena.
   */
  public void enterArena();
  
  /**
   * This method is to control two players to fight with each other in the battle game.
   * @return the actual damage
   */
  public String fight();
  
  /**
   * This method is to check whether the game is over or not.
   * @return true if the game is over, otherwise false
   */
  public boolean isGameOver();
  
  /**
   * This method is to get the winner when the game is over.
   * @return the winner if there is a winner, otherwise return null if it's a tied game
   */
  public Player getWinner();
  
  /**
   * This method is to get whose turn it is right now.
   * @return the current player
   */
  public Player getTurn();
  
  /**
   * This method is to print two players' ability information.
   * @return the ability information
   */
  public String printPlayerBasicAbility();
  
  /**
   * This method is to print what gears the two players use after he/she gets the equipment bag.
   * @return the equipped gears information
   */
  String printPlayerEquippedGears();
  
  /**
   * This method is provided to print player's equipment information.
   * @return the detail gears inside the bag
   */
  String printPlayerEquipmentBag();
  
  /**
   * This method is to print the two players' temporary abilities got by using gears.
   * @return the temporary abilities
   */
  String printPlayerTemporaryAbilities();
  
  /**
   * This method is to print the two players' description 
   * including temporary abilities, the gears they use, and 
   * the weapon they use respectively.
   * @return players' information
   */
  String printPlayerInfo();
  
  /**
   * This method is to print the two players' health information.
   * @return players' health value
   */
  String printPlayerHealth();
  
  /**
   * This method is to print the two players' total abilities 
   * including basic abilities and temporary abilities.
   * @return the sum of basic ability and temporary ability respectively
   */
  String printPlayerTotalAbilitites();
  
  /**
   * This method is to print opponent's name.
   * @return the name of the opponent
   */
  String printOpponentName();
}
