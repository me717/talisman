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
	public static void combat(Combatant c1, Combatant c2, boolean psychic) {

		int power1 = c1.getPower(psychic)
				+ c1.getGame().rollDice(1, c1.getName(), "combat");
		int power2 = c2.getPower(psychic)
				+ c2.getGame().rollDice(1, c2.getName(), "combat");
		if(power1>power2){
			c1.onWin(c2);
			c1.getGame().outputString(
					"The " + c1.getName() + " has defeatted the "
							+ c2.getName());
		}else if(power1<power2){
			c2.onWin(c1);
			c2.getGame().outputString(
					"The " + c2.getName() + " has defeatted the "
							+ c1.getName());
		}else{
			c1.getGame().outputString(
					"The " + c1.getName() + " has tied with the "
							+ c2.getName());
		}
	}
}
