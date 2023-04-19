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

	// daihinmin is the best, daifugou is the worst
	public enum PlayerRank {
		DAIFUGOU(1), FUGOU(2), HEIMIN(3), HINMIN(4), DAIHINMIN(5);

		public final int value;

		private PlayerRank(int value) {
			this.value = value;
		}
	};

	/**
	 * A constructor that allows you to set the player's unique ID
	 *
	 * @param name the unique ID to assign to this player.
	 * HEIMIN is the starting rank for all players
	 */
	public Player(String name) {
		this.name = name;
		rank = PlayerRank.HEIMIN;
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

	public Hand getHand() {
		return hand;
	}

	/**
	 * The method to be overridden when you subclass the Player class with your specific type of Player and filled in with logic to play your game.
	 * 
	 * @return Whether a card was played
	 */
	public boolean play(ArrayList<Card> playArea, int trickSize, Card.Rank lastCard) {
		Scanner keyboard = new Scanner(System.in);



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

		System.out.printf("%d cards of the same rank must be played.\n", trickSize);
		


		while (true) {
			System.out.printf("(%s) Enter card rank to play (or pass): ", name);
			try {
				String input = keyboard.nextLine().trim().toUpperCase();

				if (input.equals("PASS")) {
					return false;
				} else {
					Card.Rank selection = Card.Rank.valueOf(input);
					if (selectedRanks.contains(selection)) {
						if (playArea.isEmpty() || selection.value > lastCard.value) {
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
						} else {
							System.out.printf("%s is lower than the current cards in play\n", selection);
						}
					} else {
						System.out.printf("%s is not available to play\n", selection);
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
	 * @param card
	 * @return.
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
