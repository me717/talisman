/**
 * 
 */
package com.github.me717.talisman;


/**
 * Runs combat
 * 
 * @author Albert
 * 
 */
public class Combat {
	/**
	 * 
	 * @param player
	 *            the player who is fighting
	 * @param enemy
	 * @return positive if the first player wins, negative if the second player
	 *         wins, 0 if it is a tie
	 */
	public static int combat(Combatant c1, Combatant c2, boolean psychic) {

		int power1 = c1.getPower(psychic)
				+ c1.getGame().rollDice(1, c1.getName(), "combat");
		int power2 = c2.getPower(psychic)
				+ c2.getGame().rollDice(1, c2.getName(), "combat");
		return power1 - power2;
	}
}
