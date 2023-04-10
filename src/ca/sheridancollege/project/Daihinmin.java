package ca.sheridancollege.project;

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
