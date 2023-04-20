package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

	public int lastCardIndex() {
		return cards.size() - 1;
	}

	public boolean isValidIndex(int index) {
		if (index < 0 || index > lastCardIndex()) {
			return false;
		} else {
			return true;
		}
	}

	public void swapCardsUp(Hand targetHand, int amount) {
		// last card in sorted hand will be the highest rank
		sortHand();

		for (int i = 0; i < amount; i++) {
			targetHand.getCards().add(cards.get(lastCardIndex()));
			cards.remove(lastCardIndex());
		}
	}

	public void swapCardsDown(Hand targetHand, int amount) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList<Card> selectedCards = new ArrayList<>();

		sortHand();

		show("Hand");

		System.out.printf("Enter %d card numbers from the list to return (starting from 0):\n", amount);
		// BUG here: user can put the same card twice
		while (selectedCards.size() < amount) {
			try {
				int selectedIndex = Integer.parseInt(keyboard.nextLine());
				if (!isValidIndex(selectedIndex)) {
					throw new IllegalArgumentException("Selection must be between 0 and " + (lastCardIndex()) + ".");
				}
				else {
					selectedCards.add(cards.get(selectedIndex));
				}
			}
			catch (NumberFormatException exception) {
				System.out.printf("You must enter a number.\n");
			}
			catch (IllegalArgumentException exception) {
				System.out.printf("%s\n", exception.getMessage());
			}
		}
		targetHand.getCards().addAll(selectedCards);
		cards.removeAll(selectedCards);
		selectedCards.clear();
	}

	public int getTrickSizes() {
		//determine available trick sizes in the current hand
		// previousCard stores the last one checked in the hand to check for duplicates
		Card previousCard = null;
		int duplicateCount = 0;
		for (Card card: cards) {
			if (card.checkMatch(previousCard)) {
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
