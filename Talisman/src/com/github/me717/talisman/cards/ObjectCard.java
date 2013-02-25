/**
 * 
 */
package com.github.me717.talisman.cards;

import com.github.me717.talisman.PChar;

/**
 * Represents both Objects and followers
 * 
 * @author Albert
 * 
 */
public class ObjectCard extends Card {

	/**
	 * If true, then this card is a follower and not an object
	 */
	private boolean follower;
	/**
	 * Whether or not this object is a weapon
	 */
	private boolean weapon;

	public ObjectCard(String name, String text, boolean follower, boolean weapon) {
		super(name, text, "Object", 5);
		this.setFollower(follower);
		this.weapon = weapon;
		if (follower) {
			this.setType("Follower");
		}
	}

	/**
	 * Is called when the card is drawn
	 * @param the card that is drawn.
	 */
	@Override
	public void onDraw(PChar player) {

		// TODO Objects On Draw
		if (player.canAddObject(this))
			player.addObject(this);
		else
			player.getSpace().dropCard(this);
	}
	/**
	 * Called if the card is successfully added to the players inventory
	 * 
	 * @param player
	 *            The player who gets the object
	 */
	public void onAdd(PChar player) {
		// TODO onAdd
		
	}

	/**
	 * Is called when this object is removed from a character's inventory
	 * 
	 * @param player
	 *            The character from which this object is removed
	 */
	public void onRemove(PChar player) {
		// TODO Objects onRemove
	}

	/**
	 * @param follower
	 *            the follower to set
	 */
	public void setFollower(boolean follower) {
		this.follower = follower;
	}

	/**
	 * @return the follower
	 */
	public boolean isFollower() {
		return follower;
	}

	/**
	 * @return the weapon
	 */
	public boolean isWeapon() {
		return weapon;
	}

}
