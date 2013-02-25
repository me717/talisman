package com.github.me717.talisman.cards;

import com.github.me717.talisman.PChar;

/**
 * Represent all event cards (priority 1).  These include the storm, the blizzard, and Mephistopholes
 * @author Albert
 *
 */
public class EventCard extends Card {

	public EventCard(String name, String text){
		super(name, text, "Event", 1);
	}
	
	/**
	 * Gets called when this card gets drawn
	 * @param player The character that just drew the card
	 */
	@Override
	public void onDraw(PChar player) {
		// TODO onDraw Events

	}

}
