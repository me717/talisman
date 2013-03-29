package com.github.me717.talisman;

import java.util.*;

import com.github.me717.talisman.board.*;
import com.github.me717.talisman.cards.*;


/**
 * A class representing the game itself
 * 
 * @author Albert
 */
public class Game {
	/**
	 * The deck of adventure cards
	 */
	private ArrayList<Card> deck;
	/**
	 * The characters playing the game
	 */
	private ArrayList<PChar> players;
	/**
	 * The board the game is being played on
	 */
	private Board board;

	/**
	 * Creates the Game
	 */
	public Game() {
		// TODO game constructor
		board = new Board();
		players = new ArrayList<PChar>();
		// TODO add characters to the game
	}

	/**
	 * Runs a turn for that player
	 * 
	 * @param p
	 */
	public void turn(PChar player) {
		if (!player.isSkipping()) {
			int roll = rollDice(1, player.getName(), " movement");
			ArrayList<Space> options = Movement.getMovementOptions(player, roll,
					board);
			Space s = getUserInput(options);
			options.remove(player.getSpace());
			s.onLand(player);
		} else {
			outputString(player.getName() + " has skipped their turn");
		}
	}

	/**
	 * Returns a priority queue of random cards from the deck.  If the deck is empty, the deck will be 
	 * @param numToDraw the number of cards to draw
	 * @return the drawn cards, sorted so that the lowest priority number is first
	 */
	public PriorityQueue<Card> drawCards(int numToDraw){
		Random dice = new Random();
		PriorityQueue<Card> ret = new PriorityQueue<Card>();
		for(int i=0;i<numToDraw;i++){
			int draw = dice.nextInt(deck.size());
			ret.add(deck.remove(draw));
			if(deck.size()==0){
				fillDeck();
			}
		}
		return ret;
	}

	/**
	 * Roll a number of 6 sided dice, and print out the appropriate response
	 * @param num the number of dice to roll
	 * @param name the player rolling the dice
	 * @param reason the reason the dice are being rolled
	 * @return the number rolled
	 */
	public int rollDice(int num, String name, String reason) {
		Random r = new Random();
		int sum = 0;
		for (int i = 0; i < num; i++) {
			sum += r.nextInt(6) + 1;
		}
		outputString(name + " has rolled a " + sum + " for " + reason);
		return sum;
	}

	/**
	 * Gives output to the user
	 * 
	 * @param s
	 *            the string to output
	 */
	public void outputString(String s) {
		// TODO user output
	}

	/**
	 * Creates and fills the deck
	 */
	public void fillDeck() {
		deck = new ArrayList<Card>();
		//TODO fill the deck
	}

	/**
	 * Gets input from the user
	 * @param options a list of options to chose from
	 * @return the selected option
	 */
	public <V> V getUserInput(ArrayList<V> options) {
		// TODO get user input
		return null;
	}
}
