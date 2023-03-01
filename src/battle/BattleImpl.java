package battle;

import equipment.EquipmentBag;
import equipment.EquipmentBagImpl;
import player.Player;
import player.PlayerImpl;
import random.RandomGenerator;
import random.RealRandomGenerator;

/**
 * 
 *This class implement the functions of the interface {@link Battle}.
 */
public class BattleImpl implements Battle {
  
  private final Player playerA;
  private final Player playerB;
  private Player currentPlayer;
  private Player opponent;
  
  /**
   * Construct a battle with two players.
   * @param playerName   represents playerA's name
   * @param opponentName represents playerB's name
   */
  public BattleImpl(String playerName, String opponentName) {
    if (playerName.equals(opponentName)) {
      throw new IllegalArgumentException("The two players' name cannot be the same");
    }
    RandomGenerator randomWeapon = new RealRandomGenerator(1, 5);
    RandomGenerator randomRoll = new RealRandomGenerator(1, 6);
    
    RandomGenerator randomStrikingPower = new RealRandomGenerator(1, 10);
    RandomGenerator randomAvoidAbility = new RealRandomGenerator(1, 6);
    playerA = new PlayerImpl(playerName, randomWeapon, randomRoll, 
        randomStrikingPower, randomAvoidAbility);
    playerB = new PlayerImpl(opponentName, randomWeapon, randomRoll, 
        randomStrikingPower, randomAvoidAbility);
  }
  
  @Override
  public void enterArena() {
    RandomGenerator randomGear = new RealRandomGenerator(1, 6);
    EquipmentBag battleBagA = new EquipmentBagImpl(randomGear);
    //playerA request equipment and equip him/herself and request a weapon
    playerA.requestEquipment(battleBagA);
    playerA.initEquip();
    playerA.requestWeapon();
    
    //playerB request equipment and equip him/herself and request a weapon
    EquipmentBag battleBagB = new EquipmentBagImpl(randomGear);
    playerB.requestEquipment(battleBagB);
    playerB.initEquip();
    playerB.requestWeapon();
    currentPlayer = playerA.getTotalCharisma() > playerB.getTotalCharisma() ? playerA : playerB;
    opponent = playerA.getTotalCharisma() > playerB.getTotalCharisma() ? playerB : playerA;
  }
  
  @Override
  public String fight() {
    if (isGameOver()) {
      throw new IllegalStateException("The game is over");
    }
    String info = currentPlayer.attack(opponent);
    Player temp = currentPlayer;
    currentPlayer = opponent;
    opponent = temp;
    return info;
  }
  
  @Override
  public boolean isGameOver() {
    if (playerA.getHealth() <= 0 || playerB.getHealth() <= 0) {
      return true;
    }
    return false;
  }
  
  @Override
  public Player getWinner() {
    if (playerA.getHealth() > 0 && playerA.getHealth() > playerB.getHealth()) {
      return playerA;
    } else if (playerB.getHealth() > 0 && playerA.getHealth() < playerB.getHealth()) {
      return playerB;
    }
    return null;
  }

  @Override
  public Player getTurn() {
    return currentPlayer;
  }

  @Override
  public String printPlayerBasicAbility() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":");
    sb.append(playerA.printBasicAbilities()).append("\n");
    sb.append(playerB.getName()).append(":");
    sb.append(playerB.printBasicAbilities()).append("\n");
    return sb.toString();
  }

  @Override
  public String printPlayerEquippedGears() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":").append("\n");
    sb.append(playerA.printEquippedGears()).append("\n");
    sb.append(playerB.getName()).append(":").append("\n");
    sb.append(playerB.printEquippedGears()).append("\n");
    return sb.toString();
  }

  @Override
  public String printPlayerEquipmentBag() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":");
    sb.append(playerA.printEquipmentBag()).append("\n");
    sb.append(playerB.getName()).append(":");
    sb.append(playerB.printEquipmentBag()).append("\n");
    return sb.toString();
  }

  @Override
  public String printPlayerTemporaryAbilities() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":");
    sb.append(playerA.printTemporaryAbilities()).append("\n");
    sb.append(playerB.getName()).append(":");
    sb.append(playerB.printTemporaryAbilities()).append("\n");
    return sb.toString();
  }

  @Override
  public String printPlayerInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":").append("\n");
    sb.append("wearing gears:\n");
    sb.append(playerA.printEquippedGears()).append("");
    sb.append(playerA.printWeapon()).append("\n\n");
    sb.append(playerB.getName()).append(":").append("\n");
    sb.append("wearing gears:\n");
    sb.append(playerB.printEquippedGears()).append("");
    sb.append(playerB.printWeapon()).append("\n\n");
    return sb.toString();
  }

  @Override
  public String printPlayerHealth() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append("'s health:");
    sb.append(playerA.getHealth()).append(", ");
    sb.append(playerB.getName()).append("'s health:");
    sb.append(playerB.getHealth());
    return sb.toString();
  }

  @Override
  public String printPlayerTotalAbilitites() {
    StringBuilder sb = new StringBuilder();
    sb.append(playerA.getName()).append(":");
    sb.append(playerA.printTotalAbilities()).append("\n");
    sb.append(playerB.getName()).append(":");
    sb.append(playerB.printTotalAbilities()).append("\n");
    return sb.toString();
  }

  @Override
  public String printOpponentName() {
    return opponent.getName();
  }

}
