/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Brandon Yaeck, April 2023
 */
public class Player {

	private String name; //the unique name for this player
	private PlayerRank rank; //the current rank of the player

	public enum PlayerRank {DAIHINMIN, HINMIN, HEIMIN, FUGOU, DAIFUGOU};

	/**
	 * A constructor that allows you to set the player's unique ID
	 *
	 * @param name the unique ID to assign to this player.
	 */
	public Player(String name, PlayerRank rank) {
		this.name = name;
		this.rank = rank;
	}

	/**
	 * @return the player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ensure that the playerID is unique
	 *
	 * @param name the player name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public PlayerRank getRank() {
		return rank;
	}

	public void setRank(PlayerRank rank) {
		this.rank = rank;
	}

	/**
	 * The method to be overridden when you subclass the Player class with your specific type of Player and filled in with logic to play your game.
	 */
	public void play() {

	}

}
