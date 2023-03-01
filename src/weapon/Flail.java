package weapon;

import enumerate.TypeOfAbility;
import java.util.Map;
import random.RandomGenerator;

/**
 * This class represents a flail. It offers all the operations mandated by the
 * Weapon interface.
 */
public class Flail extends WeaponAbstract {
  private final TypeOfAbility ability;
  private final int threshold;
  
  /**
   * Construct an Flail object.
   * @param random represents a {@link RandomGenerator} which can return random values
   */
  public Flail(RandomGenerator random) {
    super(random);
    this.ability = TypeOfAbility.DEXTERITY;
    this.threshold = 14;
  }
  
  @Override
  public int getDamage(Map<TypeOfAbility, Integer> playerAbilities) {
    int damage = 0; 
    if (playerAbilities.containsKey(ability) 
        &&  playerAbilities.get(ability) > threshold) {
      damage = random.getInt();
    } else {
      damage = random.getHalfValue();
    }
    return damage;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("weapon: a flail, ");
    sb.append("damage: ").append(random.toString()).append(", ");
    sb.append("ability: ").append(ability.getName()).append(", ");
    sb.append("threshold: ").append(threshold);
    return sb.toString();
  }
}
