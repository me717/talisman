/**
 * 
 */
package com.github.me717.talisman.cards;

import com.github.me717.talisman.*;

/**
 * @author Albert
 *
 */
public class EnemyCard extends Card implements Combatant{
	/**
	 * The prefix to the title of the enemy card.  Has the value "Monster - "
	 */
	private final static String PREFIX = "Monster - ";
	/**
	 * The strength or craft this monster
	 */
	private int power;
	/**
	 * If this boolean is true, then {@link power} represents craft, not strength
	 */
	private boolean psychic;
	/**
	 * 
	 * @param name The name of the card
	 * @param text The text on the card
	 * @param type The type of enemy this is (animal, demon, etc)
	 * @param power	The strength or craft of this monster
	 * @param psychic If true, this monster has a craft value.  If false, it has a strength value.
	 */
	public EnemyCard(String name, String text, String type, int power, boolean psychic){
			super(name,text, PREFIX + type, 2);
			if(psychic){
				this.setPriority(3);
			}
			this.psychic = psychic;
			this.power = power;
	}
	/**
	 * Gets called when this card gets drawn
	 * @param player The character that just drew the card
	 */
	@Override
	public void onDraw(PChar player) {
		// TODO onDraw Enemies
		int winner = Combat.combat(player, this, psychic);
		if(winner>0){
			player.getGame().outputString("The "+player.getName()+ " has defeatted the "+getName());
			player.addPelt(this);
		}else if(winner == 0){
			player.getGame().outputString("The "+player.getName()+ " has tied with the "+getName());
			player.getSpace().dropCard(this);
		}else{
			player.getGame().outputString("The "+getName()+ " has defeatted the "+player.getName());
			player.getSpace().dropCard(this);
			player.setLives(player.getLives() - 1);
		}
	}
	
	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * @param psychic does not matter
	 * @return the power
	 */
	public int getPower(boolean psychic) {
		return power;
	}
	/**
	 * @param psychic the psychic to set
	 */
	public void setPsychic(boolean psychic) {
		this.psychic = psychic;
	}
	/**
	 * @return the psychic
	 */
	public boolean isPsychic() {
		return psychic;
	}
	
	
}
