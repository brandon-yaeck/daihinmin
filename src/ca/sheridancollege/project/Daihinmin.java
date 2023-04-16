package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class Daihinmin extends Game {

	private Deck deck = Deck.getInstance();
	private PlayArea playArea = PlayArea.getInstance();

	private int round = 0;

	public Daihinmin() {
		super("Daihinmin");
	}

	public Deck getDeck() {
		return deck;
	}

	public PlayArea getPlayArea() {
		return playArea;
	}

	/**
	 * @param players the players of this game
	 */
	public void setPlayers(String name1, String name2, String name3, String name4) {
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	


	
	/**
	 * Deal the deck of cards at the beginning of each round.
	 */
	public void deal() {
		int i = 0;
		// iterate through every card in deck
		for (Card card: deck.getCards()) {
			// go to next player after each loop
			if (i < players.size()) {
				players.get(i).getHand().getCards().add(card);
				i++;
			} else {
			// go back to first player after last player
				i = 0;
			}
		}
	}

	/**
	 * Play the game. This might be one method or many method calls depending on your game.
	 */
	public void play() {

	}

	public void rankPlayers() {

	}

	public void forceCardSwap() {

	}

	/**
	 * When the game is over, use this method to declare and display a winning player.
	 */
	public void declareWinner() {

	}
}
