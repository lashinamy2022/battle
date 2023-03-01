#Battle

####Overview
   
This is a turn-based battles generally start by pitting two    players against one another. 

####Features

  * Support four types of gears, belt, headgear, potion, footwear 
  * Support assigned 40 items to the equipment bag, including  5 items of headgear, 5 items of footwear, 15 belts, and 15 potions and 25% of them have negative effects which will diminish the player's ability 
  * Support randomly assigned 20 items of the equipment bag to each player and they may be assigned items with positive or negative effects
  * The two players get gears from a new equipment bag, which means they do not share the equipment bag
  * After players get their own equipment bag with 20 items in it, they must use all the gears unless it cannot be combined with what the player is already using. For example, a player can only wear one headgear and one pair of footgear and 10 units of belt so if there are 2 headgears in the bag, the player can only use one.
  * Players can use gears to change their temporary abilities and these temporary abilities and only be used in three attacks, after that these temporary abilities will disappear whether they are positive or negative
  * Support five types of weapons, katanas, broadsword, two-handed sword, axe and flail
  * Each player can request only one weapon
  * Each types of weapons can bring random damage within different ranges. And some weapons like flail and two-handed sword, only people with enough abilities can fully use these weapons, otherwise these weapons only do half damage. And Katanas come to be a pair and during the attack they are used together so that this weapon can make double damage
  * Players enter the arena with bare hands and basic abilities that get from rolling dices 4 times (re-rolling any 1s)
  * 30% of the sum of rolling dices is assigned to STRENGTH, 30% to DEXTERITY, 20% to CONSTITUTION and the rest to CHARISMA
  * A player's health is calculated to be the sum of their 4 abilities(including temporary abilities)
  * Striking power is the sum of the strength of the player, any of the gear that adds (or subtracts) from strength, and a random number between 1 and 10 (inclusive)
  * Avoidance ability is the sum of the dexterity of the player, any of the gear that adds (or subtracts) from dexterity, and a random number between 1 and 6 (inclusive)
  * If the striking power of the attacking player is greater than the avoidance ability of their opponent, the attacking player successfully strikes their opponent and the damage must be calculated
  * The potential striking damage is calculated by adding the strength of the attacking player to a random value in the range of the damage that their weapon can inflict (if they have a weapon).
  * The actual damage is the potential striking damage minus the constitution of their opponent
  * If the actual damage is greater than 0, it is subtracted from the player's health
  
####How To Run

java -cp /res/project2.jar driver.BattleDriver

 - driver is the package name
 - BattleDrive is the drive class
 
####How to Use the Program

 - when it asks you to enter players' name like :
   "Please enter 'start' + players' name", please enter "start Jerry Tom", or other names
 - when you want to quit, please enter 'q'
 - when you want to play a rematch, please enter 'r'
 - when you want to enter the arena, please enter 'e'
 - when you want to start a fight, please enter 'f'

###Description of Examples

  Run1-ExampleRun.txt
  * introduction information
  * read a message from the user via keyboard to create two characters, 'start Jerry Tom'
  * print players' basic abilities before they enter the arena
  * read a message from the user via keyboard to enter the arena, 'e'
  * print players' description, including all the gears they have used and the effects brought by those gears and the weapon they use
  * start fighting, print players' health
  * print who is the first turn
  * read a message from the user via keyboard to start fighting,'f'
  * print attacker's striking power and potential damage, print opponent's avoidance ability and its constitution
  * print the attack result, succeeded or failed and the actual damage
  * the result of the game and the winner
  * read a message from the user via keyboard to quit or start a rematch
  
####Design/Model Changes
 
  In my first design, I implement Battle and Player in a class not a interface and a class implements the interface. Now I implement them in interface Battle and Player respectively.
  
####Assumptions

  * I assume that the weapon a pair of Katanas should be used together and so they can make twice damage, so the damage range is 8 ~12
  * I assume that the actual damage is deducted from basic abilities not the temporary abilities
  * I assume that the temporary abilities have a life cycle that they will disappear after three turns
  
  
