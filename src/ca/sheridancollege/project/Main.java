package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class Main {
	public static void main(String[] args) {

		// create game
		Daihinmin game = new Daihinmin();

		// add 4 players to game
		game.setPlayers("Tom", "Greg", "Steve", "Linda");

		// start the game
		game.play();

	}
}
