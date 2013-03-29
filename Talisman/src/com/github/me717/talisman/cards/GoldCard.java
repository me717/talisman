/**
 * 
 */
package com.github.me717.talisman.cards;

import com.github.me717.talisman.PChar;

/**
 * @author Albert
 * 
 */
public class GoldCard extends Card {

	/**
	 * The amount of gold granted when this card is drawn
	 */
	private int numGold;

	public GoldCard(int num) {
		super(num == 1 ? "Bag of Gold" : num + " bags of gold",
				num + num == 1 ? " bag " : "bags" + " of gold", "Gold", 5);
		numGold = num;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.me717.talisman.cards.Card#onDraw(com.github.me717.talisman
	 * .PChar)
	 */
	@Override
	public void onDraw(PChar player) {
		player.setGold(player.getGold() + numGold);
		getGame().outputString(player + " has gained "+numGold+" gold");

	}
}
