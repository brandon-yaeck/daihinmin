package ca.sheridancollege.project;

import java.util.Collections;

/**
 * Singleton class representing the only deck used for dealing.
 *
 * @author Brandon Yaeck, April 2023
 */
public class Deck extends GroupOfCards {

	private static Deck onlyObject = null;

	/**
	 * Create a deck with one of each possible card combination.
	 */
	private Deck() {
		for (Card.Suit suit: Card.Suit.values()) {
			for (Card.Rank rank: Card.Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}
	
	/**
	 * Shuffle the deck.
	 * Used before dealing.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Singleton pattern instance creation.
	 * 
	 * @return the only instance of the deck
	 */
	public static Deck getInstance() {
		if (onlyObject == null) {
			onlyObject = new Deck();
		}

		return onlyObject;
	}

}
