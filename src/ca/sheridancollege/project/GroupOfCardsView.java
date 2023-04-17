package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class GroupOfCardsView {

	public static void show(String message, ArrayList<Card> list) {
		System.out.printf("\n%s:\n", message);
		for (Card card: list) {
			System.out.println(card);
		}
		System.out.println();
	}
}
