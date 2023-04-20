/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Brandon Yaeck, April 2023
 */
public class Player implements Comparable<Player> {

	private String name; //the unique name for this player
	private PlayerRank rank; //the current rank of the player
	private Hand hand = new Hand();

	/**
	 * List of available ranks for each player.
	 * given an integer value for comparison purposes.
	 * daifugou is the best, daihinmin is the worst.
	 */
	public enum PlayerRank {
		DAIFUGOU(1), FUGOU(2), HEIMIN(3), HINMIN(4), DAIHINMIN(5);

		public final int value;

		private PlayerRank(int value) {
			this.value = value;
		}
	};

	/**
	 * A constructor that allows you to set the player's unique ID.
	 * HEIMIN is the starting rank for all players
	 *
	 * @param name the unique ID to assign to this player.
	 */
	public Player(String name) {
		this.name = name;
		rank = PlayerRank.HEIMIN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerRank getRank() {
		return rank;
	}

	public void setRank(PlayerRank rank) {
		this.rank = rank;
	}

	public Hand getHand() {
		return hand;
	}

	/**
	 * Test whether a card is currently playable.
	 * 
	 * @param selection the rank of the card the player wishes to play
	 * @param lastCard the rank of the last card that was played, new one must be higher
	 * @param trickSize the trick size currently in play
	 * @return boolean of whether it is playable
	 */
	public boolean isPlayable(int trickSize, Card.Rank selection, Card.Rank lastCard) {
		ArrayList<Card.Rank> selectedRanks;
		switch (trickSize) {
			case 4:
				selectedRanks = hand.getQuadruples();
				break;
			case 3:
				selectedRanks = hand.getTriples();
				break;
			case 2:
				selectedRanks = hand.getDoubles();
				break;
			default:
				selectedRanks = hand.getSingles();
				break;
		}

		if (!selectedRanks.contains(selection)) {
			System.out.printf("%s is not available to play\n", selection);
			return false;
		} else if (lastCard != null && selection.value < lastCard.value) {
			System.out.printf("%s is lower than the current cards in play\n", selection);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Player specific gameplay.
	 * Prompts the player for the card they wish to play and tests its validity.
	 * 
	 * @return Whether a card was successfully played
	 */
	public boolean play(ArrayList<Card> playArea, int trickSize, Card.Rank lastCard) {
		Scanner keyboard = new Scanner(System.in);

		System.out.printf("%d cards of the same rank must be played.\n", trickSize);
		
		while (true) {
			System.out.printf("(%s) Enter card rank to play (or pass): ", name);
			try {
				String input = keyboard.nextLine().trim().toUpperCase();

				if (input.equals("PASS")) {
					return false;
				} else {
					Card.Rank selection = Card.Rank.valueOf(input);
					if (isPlayable(trickSize, selection, lastCard)) {
						// find card in hand that matches
						int matchAmount = 0;
						for (Card card: hand.getCards()) {
							if (card.checkMatch(selection)) {
								playArea.add(card);
								matchAmount++;	
							}

							// continue looping according to how many cards were played in order to find them all
							// limit amount of cards played to trick size
							if (matchAmount == trickSize) {
								break;
							}						
						}
						// remove cards from hand if they are in the play area
						// this only works with a single deck in use
						hand.getCards().removeAll(playArea);

						break;
					}
				}
			}
			catch (IllegalArgumentException exception) {
				System.out.printf("%s\n", exception.getMessage());
			}
		}
		System.out.println();

		// remove the cards from the duplicate list (will be regenerated next loop)
		hand.clearRankLists();

		// card successfully played if reached end
		return true;
	}

	/**
	 * Compare the ranks of players.
	 *
	 * @param player the player being compared with
	 * @return standard Comparable interface values
	 */
	@Override
	public int compareTo(Player player) {
		if (rank.value < player.getRank().value) {
			return -1;
		} else if (rank == player.getRank()) {
			return 0;
		} else {
			return 1;
		}
	}
}
