package com.github.me717.talisman;

import java.util.ArrayList;
import java.util.HashSet;

import com.github.me717.talisman.board.*;
import com.github.me717.talisman.cards.*;


/**
 * The player control character, and the class they are playing
 * 
 * @author Albert
 * 
 */
public class PChar implements Combatant{

	/**
	 * The options that the alignment of the character can be
	 * 
	 * @author Albert
	 * 
	 */
	public enum Alignment {
		Good, Evil, Neutral;
	}

	/**
	 * The name of the characters class (Wizard, Troll, etc)
	 */
	private String name;
	/**
	 * The strength of the character out of combat. This does not include
	 * weapons such as swords and axes
	 */
	private int strength;
	/**
	 * The strength of the character in combat. This includes boosts from
	 * weapons.
	 */
	private int combatStrength;
	/**
	 * The craft of the character. Includes all boosts (all craft boosting items
	 * work both in combat and out of combat.
	 */
	private int craft;
	/**
	 * The amount of gold the character is currently carrying
	 */
	private int gold;
	/**
	 * The amount of weapons the player is carrying
	 */
	private int numWeapons;
	/**
	 * The space the character currently occupies
	 */
	private Space space;
	/**
	 * The alignment of the character (Good, Evil, or Neutral)
	 */
	private Alignment align;
	/**
	 * The players current inventory
	 */
	private ArrayList<ObjectCard> objects;
	/**
	 * A list of objects carried by the player
	 */
	private HashSet<String> objectNames;
	/**
	 * The followers the player currently has
	 */
	private ArrayList<ObjectCard> followers;
	/**
	 * The stength enemies the player has killed
	 */
	private ArrayList<Integer> strengthPelts;
	/**
	 * the craft enemies the player has killed
	 */
	private ArrayList<Integer> craftPelts;
	/**
	 * a reference to the game that the player is involved in
	 */
	private Game game;
	/**
	 * The number of lives the player has left
	 */
	private int lives;
	/**
	 * If true, the player skips their next turn
	 */
	private boolean skipping;
	/**
	 * If true, the player 
	 */
	private boolean teleport;
	/**
	 * Assigns values to each of the variables based on the name of the
	 * character
	 * 
	 * @param name
	 *            The name of the characters class
	 */
	public PChar(String name) {
		this.name = name;
		// TODO starting stats
		// TODO starting spaces
		// TODO starting spells
		// TODO abilities

		// Stats same for all characters
		gold = 1;
		numWeapons = 0;
		lives = 3;
		//Initialize variables
		objects = new ArrayList<ObjectCard>();
		objectNames = new HashSet<String>();
		followers = new ArrayList<ObjectCard>();
		craftPelts = new ArrayList<Integer>();
		strengthPelts = new ArrayList<Integer>();
		setTeleport(false);
		// Values for the Assassin
		if (name == "Assassin") {
			strength = 3;
			craft = 3;
			align = Alignment.Evil;
			// Values for the Druid
		} else if (name == "Druid") {
			strength = 2;
			craft = 4;
			align = Alignment.Neutral;
			// Values for the dwarf
		} else if (name == "Dwarf") {
			// TODO lookup dwarf stats
			// Values for the elf
		} else if (name == "Elf") {
			strength = 3;
			craft = 3;
			align = Alignment.Good;
			// Values for the ghoul
		} else if (name == "Ghoul") {
			strength = 2;
			craft = 4;
			align = Alignment.Evil;
			// Values for the Minstrel
		} else if (name == "Minstrel") {
			strength = 3;
			craft = 3;
			align = Alignment.Neutral;
			// Values for the Monk
		} else if (name == "Monk") {
			strength = 2;
			craft = 3;
			align = Alignment.Good;
			// Values for the Priest
		} else if (name == "Priest") {
			strength = 2;
			craft = 3;
			align = Alignment.Good;
			// Values for the Sorceress
		} else if (name == "Sorceress") {
			strength = 2;
			craft = 4;
			align = Alignment.Evil;
			// Values for the Troll
		} else if (name == "Theif") {
			strength = 3;
			craft = 3;
			align = Alignment.Neutral;
			// Values for the Troll
		} else if (name == "Troll") {
			strength = 6;
			craft = 1;
			align = Alignment.Neutral;
			// Values for the warrior
		} else if (name == "Warrior") {
			strength = 4;
			craft = 2;
			align = Alignment.Neutral;
			// Values for the Wizard
		} else if (name == "Wizard") {
			strength = 2;
			craft = 5;
			align = Alignment.Evil;
		}
	}
	
	@Override
	/**
	 * If a was defeated, allow stealing.  If the enemy is peltable, add it to the pelts
	 */
	public void onWin(Combatant opponent) {
		if(opponent.isPeltable()){
			addPelt((EnemyCard) opponent);
		}
		if(opponent.isRobable()){
			//TODO player defeats another player
		}
	}

	/**
	 * Adds a defeated enemy to the list of pelts
	 * @param e the defeated enemy
	 */
	public void addPelt(EnemyCard e){
		if(e.isPsychic()){
			craftPelts.add(e.getPower(true));
		}else{
			strengthPelts.add(e.getPower(false));
		}
	}
	/**
	 * Add the object or follower to the appropriate list, and takes the
	 * appropriate action
	 * 
	 * @param object
	 */
	public void addObject(ObjectCard object) {
		if (object.isFollower()) {
			followers.add(object);
			object.onAdd(this);
		} else {
			if (objects.size() < 4) {
				objects.add(object);
				if (object.isWeapon()) {
					if (numWeapons > 0) {
						object.onAdd(this);
					}
					numWeapons += 1;

				}
			} else {

				// TODO user input which object to drop
			}
		}

		refreshSet();
	}

	/**
	 * Checks if this character can carry this specific object. This method does
	 * NOT take inventory space into account
	 * 
	 * @param object
	 * @return Whether or not this object can be carried by this specific
	 *         character
	 */
	public boolean canAddObject(ObjectCard object) {
		// TODO special conditions
		
		// Holy lance and holy grail
		if(object.getName().equals("Holy Lance")||object.getName().equals("Holy Grail")){
			return align!=Alignment.Evil;
		}else if(object.getName().equals("Runesword")){
			return align!=Alignment.Good;
		}
		return true;
	}

	/**
	 * 
	 * @param name the name to check
	 * @return whether or not a player has an object of this name
	 */
	public boolean hasObjectByName(String name){
		return objectNames.contains(name);
	}
	private void refreshSet() {
		objectNames.clear();

		for (int i = 0; i < objects.size(); i++) {
			objectNames.add(objects.get(i).getName());
		}

	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getPower(boolean psychic){
		if(psychic){
			return craft;
		}else{
			return combatStrength;
		}
	}
	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param combatStrength
	 *            the combatStrength to set
	 */
	public void setCombatStrength(int combatStrength) {
		this.combatStrength = combatStrength;
	}

	/**
	 * @return the combatStrength
	 */
	public int getCombatStrength() {
		return combatStrength;
	}

	/**
	 * @param craft
	 *            the craft to set
	 */
	public void setCraft(int craft) {
		this.craft = craft;
	}

	/**
	 * @return the craft
	 */
	public int getCraft() {
		return craft;
	}

	/**
	 * @param gold
	 *            the gold to set
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * @param space
	 *            the space to set
	 */
	public void setSpace(Space space) {
		this.space = space;
	}

	/**
	 * @return the space
	 */
	public Space getSpace() {
		return space;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(Alignment align) {
		this.align = align;
	}

	/**
	 * @return the align
	 */
	public Alignment getAlign() {
		return align;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param skipping the skipping to set
	 */
	public void setSkipping(boolean skipping) {
		this.skipping = skipping;
	}

	/**
	 * @return the skipping
	 */
	public boolean isSkipping() {
		return skipping;
	}

	/**
	 * @return the teleport
	 */
	public boolean isTeleport() {
		return teleport;
	}

	/**
	 * @param teleport the teleport to set
	 */
	public void setTeleport(boolean teleport) {
		this.teleport = teleport;
	}

	@Override
	public boolean isPeltable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * All players are robbable
	 * @return true
	 */
	public boolean isRobable() {
		return true;
	}




}
