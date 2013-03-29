/**
 * 
 */
package com.github.me717.talisman;

/**
 * Represents those who can fight in combat
 * @author Albert
 *
 */
public interface Combatant {
	public int getPower(boolean psychic);
	public String getName();
	public Game getGame();
	public void onWin(Combatant opponent);
	public boolean isPeltable();
	public boolean isRobable();
}
