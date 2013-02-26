/**
 * Contains the board, spaces, and anything related to movement
 */
package com.github.me717.talisman.board;

import java.util.*;

import com.github.me717.talisman.*;
import com.github.me717.talisman.cards.*;

/**
 * The spaces that make up the board
 * 
 * @author Albert
 * 
 */
public class Space {
	/**
	 * The name of the space. Different spaces on the board may have the same
	 * name.
	 */
	private String name;
	/**
	 * An integer representing the space. Every space in the region has a
	 * different id. They increase clockwise, with 0 being the village, portal
	 * of power, and plain of peril
	 */
	private int id;
	private Region region;

	public enum Region {
		OUTER, MIDDLE, INNER
	}

	private PriorityQueue<Card> cards;

	/**
	 * Creates the space
	 * 
	 * @param name
	 *            the name of the space
	 * @param id
	 */
	public Space(int id, Region r) {
		// TODO Space constructor
		if (r.equals(Region.OUTER)) {
			switch (id) {
			case 0:
				name = "Village";
				break;
			}
		} else if (r.equals(Region.MIDDLE)) {
			switch (id) {
			case 0:
				name = "Portal of Power";
				break;
			}
		} else if (r.equals(Region.INNER)) {
			switch (id) {
			case 0:
				name = "Plain of Peril";
				break;
			}
		} else {
			name = "Crown of F**king Command";
		}
	}

	public void dropCard(Card c) {
		cards.add(c);
	}

	/**
	 * This methods get called when a character lands on the space. The effect
	 * varies depending on the name of the space.
	 * 
	 * @param player
	 *            The character that has just landed on the space.
	 */
	public void onLand(PChar player) {
		player.setSpace(this);
		// TODO encounter characters
		PriorityQueue<Card> drawn = null;
		if (name.equals("Woods") || name.equals("Plains")
				|| name.equals("Fields") || name.equals("Hills")
				|| name.equals("Portal of Power") || name.equals("Sentinel")) {
			drawn = drawCards(1, player.getGame());
		} else if (name.equals("Ruins") || name.equals("Oasis")) {
			drawn = drawCards(2, player.getGame());
		} else if (name.equals("Hidden Valley")) {
			drawn = drawCards(3, player.getGame());
		} else if (name.equals("Forest")) {
			// TODO forest
		} else if (name.equals("Crags")) {
			// TODO crags
		} else if (name.equals("Village")) {
			// TODO village
		} else if (name.equals("City")) {
			// TODO city
		} else if (name.equals("Chapel")) {
			// TODO chapel
		} else if (name.equals("Graveyard")) {
			// TODO graveyard
		} else if (name.equals("Tavern")) {
			// TODO tavern
		} else if (name.equals("Chasm")) {
			// TODO chasm
		} else if (name.equals("Deset")) {
			if (!player.hasObjectByName("Holy Grail")
					&& !player.hasObjectByName("Water Bottle")) {
				player.setLives(player.getLives() - 1);

				player.getGame().outputString(
						player.getName() + " has lost a life in the desert");
			}
		} else if (name.equals("Temple")) {
			// TODO temple
		} else if (name.equals("Warlock's Cave")) {
			// TODO warlocks cave
		} else if (name.equals("Cursed glade")) {
			// TODO cursed glade
		}
		if(drawn != null && drawn.size()>0){
			for(Card card : drawn){
				card.onDraw(player);
			}
		}

	}

	public PriorityQueue<Card> drawCards(int num, Game game) {
		PriorityQueue<Card> toDraw = new PriorityQueue<Card>();
		for (int i = 0; i < num; i++) {
			if (cards.size() > 0) {
				toDraw.add(cards.poll());
			} else {
				// TODO draw card
			}
		}
		return toDraw;
	}

	/**
	 * @return the name of the space
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the spaces ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the region of the space
	 */
	public Region getRegion() {
		return region;
	}

}
