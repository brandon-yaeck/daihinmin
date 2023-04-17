package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class Hand extends GroupOfCards {

	private ArrayList<Card.Rank> singleCards;
	private ArrayList<Card.Rank> doubleCards;
	private ArrayList<Card.Rank> tripleCards;
	private ArrayList<Card.Rank> quadrupleCards;

	public Hand() {
		singleCards = new ArrayList<>();
		doubleCards = new ArrayList<>();
		tripleCards = new ArrayList<>();
		quadrupleCards = new ArrayList<>();
	}
	/**
	 * Sorts the hand for visual clarity.
	 * 
	 */
	public void sortHand() {
		Collections.sort(cards);
	}

	public void addSingles() {
		for (Card card: cards) {
			singleCards.add(card.getRank());
		}
	}
	public ArrayList<Card.Rank> getSingles() {
		return singleCards;
	}

	public void addDoubles(Card.Rank rank) {
		doubleCards.add(rank);
	}
	public ArrayList<Card.Rank> getDoubles() {
		return doubleCards;
	}

	public void addTriples(Card.Rank rank) {
		tripleCards.add(rank);
	}
	public ArrayList<Card.Rank> getTriples() {
		return tripleCards;
	}

	public void addQuadruples(Card.Rank rank) {
		quadrupleCards.add(rank);
	}
	public ArrayList<Card.Rank> getQuadruples() {
		return quadrupleCards;
	}

	public void clearRankLists() {
		singleCards.clear();
		doubleCards.clear();
		tripleCards.clear();
		quadrupleCards.clear();
	}

	@Override
	public void clearCards() {
		cards.clear();
		clearRankLists();
	}

	public int getTrickSizes() {
		//determine available trick sizes in the current hand
		// previousCard stores the last one checked in the hand to check for duplicates
		Card previousCard = null;
		int duplicateCount = 0;
		for (Card card: cards) {
			if (previousCard != null && card.compareTo(previousCard) == 0) {
				switch (duplicateCount) {
					case 0:
						addDoubles(card.getRank());
						break;
					case 1:
						addTriples(card.getRank());
						break;
					case 2:
						addQuadruples(card.getRank());
						break;
					default:
						break;
				}
				duplicateCount++;
			} else {
				duplicateCount = 0;
			}
			previousCard = card;
		}
		// register all cards as singles as well
		addSingles();


		// return the max trick size based on the above
		if (!getQuadruples().isEmpty()) {
			return 4;
		} else if (!getTriples().isEmpty()) {
			return 3;
		} else if (!getDoubles().isEmpty()) {
			return 2;
		} else {
			return 1;
		}

	}
}
