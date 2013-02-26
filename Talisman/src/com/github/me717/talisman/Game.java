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
	public void turn(PChar p) {
		if (!p.isSkipping()) {
			int roll = rollDice(1, p.getName(), " movement");
			ArrayList<Space> options = Movement.getMovementOptions(p, roll,
					board);
			Space s = getUserInput(options);
			s.onLand(p);
		} else {
			outputString(p.getName() + " has skipped their turn");
		}
	}

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

	public <V> V getUserInput(ArrayList<V> options) {
		// TODO get user input
		return null;
	}
}
