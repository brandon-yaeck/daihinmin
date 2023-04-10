package ca.sheridancollege.project;

import java.util.Collections;

/**
 * Singleton class representing the only deck used for dealing.
 *
 * @author Brandon Yaeck, April 2023
 */
public class Deck extends GroupOfCards {

	private static Deck onlyObject = null;

	private Deck() {
		// create a deck with one of each possible card combination
		for (Card.Suit suit: Card.Suit.values()) {
			for (Card.Rank rank: Card.Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public static Deck getInstance() {
		if (onlyObject == null) {
			onlyObject = new Deck();
		}

		return onlyObject;
	}

}
