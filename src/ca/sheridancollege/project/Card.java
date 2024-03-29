/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @author Brandon Yaeck, April 2023
 */
public class Card implements Comparable<Card> {
	/**
	 * List of available suits for each card.
	 */
	public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};

	/**
	 * List of available ranks for each card.
	 * given an integer value for comparison purposes.
	 * 3 is the weakest and 2 is the strongest.
	 */
	public enum Rank {
		THREE(1), FOUR(2), FIVE(3), SIX(4), SEVEN(5), EIGHT(6), NINE(7), TEN(8), JACK(9), QUEEN(10), KING(11), ACE(12), TWO(13);

		public final int value;

		private Rank(int value) {
			this.value = value;
		}
	};

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	/**
	 * @return a String representation of a card.
	 */
	@Override
	public String toString() {
		return rank + " of " + suit;
	}

	/**
	 * Compare the value of cards.
	 *
	 * @param card the card being compared with
	 * @return standard Comparable interface values
	 */
	@Override
	public int compareTo(Card card) {
		if (rank.value < card.getRank().value) {
			return -1;
		} else if (rank == card.getRank()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Check if two cards match using the comparable interface.
	 *
	 * @param card the card being checked for a match
	 * @return whether they match
	 */
	public boolean checkMatch(Card card) {
		if (card != null) {
			return compareTo(card) == 0;
		} else {
			return false;
		}
	}

	/**
	 * Check if two cards match using the comparable interface.
	 * alternate version using a card's rank directly
	 *
	 * @param rank the rank of the card being checked for a match
	 * @return whether they match
	 */
	public boolean checkMatch(Rank rank) {
		return this.rank == rank;
	}
}
