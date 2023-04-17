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

	@Override
	public void clearCards() {
		cards.clear();
		doubleCards.clear();
		tripleCards.clear();
		quadrupleCards.clear();
	}
}
