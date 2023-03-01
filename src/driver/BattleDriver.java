package driver;

import battle.Battle;
import battle.BattleImpl;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This is a driver class to show how to play this game.
 *
 */
public class BattleDriver {
  private final Readable in;
  private final Appendable out;
  
  /**
   * Construct a battle driver with input and output parameters.
   * @param in input parameter
   * @param out output parameter
   */
  public BattleDriver(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }
  
  /**
   * To start a battle by reading user's input command.
   * @throws IOException when there is a input or output exception
   */
  public void startBattle() throws IOException {
    Scanner scan = new Scanner(this.in);
    String nameA = "";
    String nameB = "";
    this.out.append("Welcome! This is a battle game "
        + "which is started by entering two players' name such as 'start Jimmy Tom'\n ");
    this.out.append("and then the two players will enter the arena and start a battle.\n");
    this.out.append("Notice that when the health of one of the players equals to zero, "
        + "the game will be over! And whose \n");
    this.out.append(" health is greater than zero at the end of the game is the winner!"
        + "Now start your game!\n");
    Battle battle = null;
    while (true) {
      if (battle == null) {
        this.out.append("Please enter 'start' + players' name:");
      } else {
        if (battle.isGameOver()) {
          this.out.append("Please enter 'r' to play a rematch or 'q' to quit the game:");
        } else {
          if (battle.getTurn() == null) {
            this.out.append("Please enter 'e' to enter the arena:");
          } else {
            this.out.append("Please enter 'f' to start attacking:");
          }
        }
      }
      String command = scan.next();
      switch (command) {
        case "start":
          nameA = scan.next();
          nameB = scan.next();
          if (nameA.equals(nameB)) {
            this.out.append("Two players' name cannot be the same!");
            return;
          }
          battle = new BattleImpl(nameA, nameB);
          this.out.append("Players' basic abilities:\n" + battle.printPlayerBasicAbility());
          this.out.append("\n");
          break;
        case "e":
          battle.enterArena();
          printGameInfo(battle);
          break;
        case "f":
          if (battle.isGameOver()) {
            throw new IllegalStateException("The game is over!");
          }
          String attackInfo = battle.fight();
          this.out.append(attackInfo).append("\n");
          this.out.append(battle.printPlayerHealth()).append("\n\n");
          if (battle.isGameOver()) {
            this.out.append("Game is over! The winner is ")
            .append(battle.getWinner().getName()).append("!\n");
          }
          break;
        case "r":
          battle = new BattleImpl(nameA, nameB);
          battle.enterArena();
          printGameInfo(battle);
          break;
        case "q":
          scan.close();
          return;
        default:
          throw new UnsupportedOperationException(command + " is unsupported");
      }
    }
  }
  
  /**
   * This method is to print the information of the current battle.
   * @param battle the current game
   * @throws IOException when there is a input or output exception.
   */
  private void printGameInfo(Battle battle) throws IOException {
    this.out.append("Players' description:\n" + battle.printPlayerInfo());
    this.out.append("Players' temporary abilities:\n" 
        + battle.printPlayerTemporaryAbilities()).append("\n");
    this.out.append("Players' total abilities:\n" 
        + battle.printPlayerTotalAbilitites()).append("\n");
    this.out.append("Now start fighting!\n");
    this.out.append(battle.printPlayerHealth()).append("\n");
    this.out.append(battle.getTurn().getName() + " first attack.\n\n");
  }
  
  /**
   * Driver program for Battle to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    BattleDriver driver = new BattleDriver(in, System.out);
    try {
      driver.startBattle();
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
  
}
