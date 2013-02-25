/**
 * 
 */
package com.github.me717.talisman.cards;

import com.github.me717.talisman.*;

/**
 * An abstract class representing the adventure card. The onDraw method gets
 * called when the card gets drawn
 * 
 * @author Albert
 * 
 */
public abstract class Card implements Comparable<Card> {
	/**
	 * The name of the card
	 */
	private String name;

	/**
	 * The text written on the card
	 */
	private String text;
	/**
	 * The type of the adventure card (follower, object, place, stranger, event,
	 * or enemy). This is also represent by the subclass of the card
	 */
	private String type;
	/**
	 * The priority of the card. If multiple cards are drawn, then the lowest
	 * number happens first
	 */
	private int priority;
	private Game game;

	/**
	 * The basic constructor used for the card class. This should only be used
	 * by the subclasses of card
	 * 
	 * @param name
	 *            the desired name of the card. This varies from card to card.
	 * @param text
	 *            the desired text of the card. This varies from card to card.
	 * @param type
	 *            the desired type of the card (follower, object, place,
	 *            stranger, event, or enemy). This is the same for each subclass
	 *            of card, with the exception of enemy.
	 * @param priority
	 *            the desired priority name of the card. This is the same for
	 *            each subclass of the card, with the exception of enemy.
	 */
	public Card(String name, String text, String type, int priority) {
		this.name = name;
		this.text = text;
		this.setType(type);
		this.priority = priority;
	}

	/**
	 * 
	 * @return Returns the name of the card
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return Returns the text on the card
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return Returns the type of the card (follower, object, place, event,
	 *         stranger, enemy)
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return Returns the priority number of the card
	 */
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets called when this card gets drawn
	 * 
	 * @param player
	 *            The character that just drew the card
	 */
	public abstract void onDraw(PChar player);

	/**
	 * Returns
	 * 
	 * @param c
	 *            the card to which this will be compared
	 * @return 1 if the priority of this card is greater than the priority of c,
	 *         -1 if it is less than the priority of c, and 0 if they are equal
	 */
	public int compareTo(Card c) {
		return priority - c.getPriority()
				/ Math.abs(priority - c.getPriority());
	}

	/**
	 * @return the game this card is used in
	 */
	public Game getGame() {
		return game;
	}
}
