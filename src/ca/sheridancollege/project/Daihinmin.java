package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Models the main gameplay of the particular game
 *
 * @author Brandon Yaeck, April 2023
 */
public class Daihinmin extends Game {

	private Deck deck = Deck.getInstance();
	private PlayArea playArea = PlayArea.getInstance();

	private int round = 1;

	public Daihinmin() {
		super("Daihinmin");
	}

	public Deck getDeck() {
		return deck;
	}

	public PlayArea getPlayArea() {
		return playArea;
	}

	public void setPlayers(String name1, String name2, String name3, String name4) {
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
	}

	public int getRound() {
		return round;
	}

	/**
	 * Deal the deck of cards.
	 * to be called at the beginning of each round.
	 */
	public void deal() {
		deck.shuffle();

		int i = 0;
		// iterate through every card in deck
		for (Card card: deck.getCards()) {
			// go back to first player after last player
			if (i == players.size()) {
				i = 0;
			}
			players.get(i).getHand().getCards().add(card);
			i++;
		}
	}

	@Override
	public void play() {
		// 5 rounds by default
		play(5);
	}

	/**
	 * Play the game. 
	 * 
	 * @param totalRounds number of rounds to run the game for.
	 */
	public void play(int totalRounds) {
		while (round <= totalRounds) {
			// deal to each player
			deal();

			// force card trading here if required ranks exist
			forceCardSwap();

			System.out.println("----------------------------");
			System.out.println("Beginning round " + round);
			System.out.println("----------------------------");

			// begin the round
			playRound();

			round++;
		}
		declareWinner();
	}

	/**
	 * Play an individual round. 
	 * This is the main gameplay loop.
	 */
	public void playRound() {
		/** Index of the current player. */
		int i = 0;

		/** The size of the trick currently in play. */
		int trickSize = 1;

		/** The last player to successfully play a card. */
		Player lastPlay = null;

		/** Reference to the current player. */
		Player currentPlayer;

		/** The last card that was successfully played in the current trick. */
		Card.Rank lastCard = null;

		/** List of players who have emptied their hands to be used for ranking. */
		ArrayList<Player> roundRanking = new ArrayList<>();

		/** Current number of consecutive passes. */
		int passCount = 0;

		while (true) {
			currentPlayer = players.get(i);

			// check for trick end if all players pass
			if (passCount == players.size()) {
				// last person to successfully play goes first in next trick
				currentPlayer = lastPlay;
				// also have to set the index for the next player to be correct when looping
				i = players.indexOf(currentPlayer);

				// VISUAL BUG HERE:
				// if a player empties their hand and is the last player to play in the trick, the output will say they start the next trick
				// doesn't affect gameplay as it will auto pass to the next player anyway

				System.out.println();
				System.out.println("=======================================================");
				System.out.printf("Trick has ended. %s starts the next trick.\n", currentPlayer.getName());
				System.out.println("=======================================================");

				//clean up for next trick
				lastCard = null;
				trickSize = 1;
				playArea.clearCards();
			}

			// if last card was a 2 or hand empty then auto pass
			if (lastCard != Card.Rank.TWO && !currentPlayer.getHand().getCards().isEmpty()) {

				// check if round is over
				// NOTE: this must be in this loop to ensure the last player getting added to the rankings is the one without an empty hand
				if (roundRanking.size() == players.size() - 1) {
					// rank the last remaining player
					roundRanking.add(currentPlayer);

					// process final round rankings
					rankPlayers(roundRanking);

					// clean up
					currentPlayer.getHand().clearCards();
					playArea.clearCards();

					break;
				}

				currentPlayer.getHand().sortHand();

				// show playing field:
				// NOTE: playing field never needs to be sorted as cards are always added in ascending order
				playArea.show("Playing Field");

				// show current player's hand
				currentPlayer.getHand().show(currentPlayer.getName() + "'s hand");

				//determine available trick sizes in the current hand and get the max size
				int maxTrickSize = currentPlayer.getHand().getTrickSizes();

				//get the trick size to be used for the trick if field is empty and player has a trick size greater than 1
				if (playArea.getCards().isEmpty() && maxTrickSize > 1) {
					System.out.printf("(%s) Enter amount of cards to play [1 - %d]: ", currentPlayer.getName(), maxTrickSize);
					trickSize = getTrickSize(maxTrickSize);

					// reset pass count to 0 whenever the player picks a trick size
					// this is to prevent a crash if all 4 players pick a trick size and then pass without playing
					// no one will have ever played so lastPlay will be null
					passCount = 0;
				} 

				// player plays
				if (currentPlayer.play(playArea.getCards(), trickSize, lastCard)) {
					// keep track of the last player to play a card successfully
					lastPlay = currentPlayer;
					// reset pass count since a card was successfully played
					passCount = 0;
				} else {
					passCount++;
				}

				// check if player emptied hand
				if (currentPlayer.getHand().getCards().isEmpty()) {
					System.out.println();
					System.out.println("=======================================================");
					System.out.println(currentPlayer.getName() + " has emptied their hand.");
					System.out.println("=======================================================");

					// rank the player
					roundRanking.add(currentPlayer);
				}
			} else {
				passCount++;
			}

			// keep track of what the rank of the last card placed in the playing area is
			if (!playArea.getCards().isEmpty()) {
				lastCard = playArea.getCards().get(playArea.getCards().size() - 1).getRank();
			}

			
			// return to first player after each have played
			if (i < players.size() - 1) {
				i++;
			} else {
				i = 0;
			}
		}

	}

	/**
	 * Apply the new player ranks based on round placement.
	 * To be called at the end of each round.
	 * 
	 * @param roundRanking holds the order that each player finished the round
	 */
	public void rankPlayers(ArrayList<Player> roundRanking) {
		// announce results
		System.out.println();
		System.out.println();
		System.out.println("The round has ended.");
		System.out.println();
		System.out.println("New player rankings:");
		System.out.println();


		// can't loop, bottom 2 players must always be last 2 ranks as player count might not line up with rank count
		roundRanking.get(0).setRank(Player.PlayerRank.DAIFUGOU);
		roundRanking.get(1).setRank(Player.PlayerRank.FUGOU);
		roundRanking.get(roundRanking.size() - 2).setRank(Player.PlayerRank.HINMIN);
		roundRanking.get(roundRanking.size() - 1).setRank(Player.PlayerRank.DAIHINMIN);

		// for more than 4 players, the rest get HEIMIN, but not implementing now

		// order by player rank
		Collections.sort(players);

		for (Player player: players) {
			System.out.printf("%s: %s\n", player.getName(), player.getRank());
		}
		System.out.println();
	}

	/**
	 * Initiate card swapping based on player ranks.
	 * To be called before each round
	 * Only runs if there are ranked players, so not on the first round.
	 */
	public void forceCardSwap() {
		int amount = 0;

		// daifugou is always moved to first element after ranking
		// if not then it's first round and everyone is heimin still
		if (players.get(0).getRank() == Player.PlayerRank.DAIFUGOU) {
			Hand daifugou = players.get(0).getHand();
			Hand fugou = players.get(1).getHand();
			Hand hinmin = players.get(players.size() - 2).getHand();
			Hand daihinmin = players.get(players.size() - 1).getHand();


			System.out.printf("The daihinmin has given their two best cards to the daifugou as tax.\n\n");
			daihinmin.swapCardsUp(daifugou, 2);

			System.out.printf("The daifugou must return 2 cards of choice.\n");
			daifugou.swapCardsDown(daihinmin, 2);

			System.out.printf("The hinmin has given their best card to the fugou as tax.\n\n");
			hinmin.swapCardsUp(fugou, 1);

			System.out.printf("The fugou must return 1 card of choice.\n");
			fugou.swapCardsDown(hinmin, 1);
		}
	}

	/**
	 * When the game is over, declare and display a winning player.
	 */
	@Override
	public void declareWinner() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// daifugou player is always placed as the first index in the list
		System.out.printf("%s wins the game for being the last daifugou!", players.get(0));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}

	/**
	 * Prompt the player to enter their desired trick size.
	 * Only used for the first play of each trick.
	 * 
	 * @return the trick size that was input
	 */
	public int getTrickSize(int maxTrickSize) {
		Scanner keyboard = new Scanner(System.in);
		int trickSize = 1;

		while (true) {
			try {
				// choose trick size
				trickSize = Integer.parseInt(keyboard.nextLine());

				if (trickSize < 1 || trickSize > maxTrickSize) {
					throw new IllegalArgumentException("Amount of cards to play must be between 1 and " + maxTrickSize + ".");
				}
				else {
					System.out.println();
					break;
				}
			}
			catch (NumberFormatException exception) {
				System.out.printf("You must enter a number.\n");
			}
			catch (IllegalArgumentException exception) {
				System.out.printf("%s\n", exception.getMessage());
			}
		}

		return trickSize;
	}
}
