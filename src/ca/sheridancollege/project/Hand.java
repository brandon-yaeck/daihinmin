package ca.sheridancollege.project;

import java.util.Collections;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class Hand extends GroupOfCards {

	/**
	 * Sorts the hand for visual clarity.
	 * 
	 */
	public void sortHand() {
		Collections.sort(cards);
	}
	
}
