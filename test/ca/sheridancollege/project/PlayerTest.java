package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class PlayerTest {

	Player instance = new Player("Test");
	
	public PlayerTest() {
		instance.getHand().getDoubles().add(Card.Rank.TEN);
	}

	@Test
	public void testIsPlayableGood() {
		System.out.println("isPlayable Good");

		int trickSize = 2;
		Card.Rank selection = Card.Rank.TEN;
		Card.Rank lastCard = Card.Rank.FIVE;

		boolean expResult = true;
		boolean result = instance.isPlayable(trickSize, selection, lastCard);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsPlayableBad() {
		System.out.println("isPlayable Bad");

		int trickSize = 2;
		Card.Rank selection = Card.Rank.FOUR;
		Card.Rank lastCard = Card.Rank.FIVE;

		boolean expResult = false;
		boolean result = instance.isPlayable(trickSize, selection, lastCard);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsPlayableBoundary() {
		System.out.println("isPlayable Boundary");

		int trickSize = 2;
		Card.Rank selection = Card.Rank.TEN;
		Card.Rank lastCard = Card.Rank.NINE;

		boolean expResult = true;
		boolean result = instance.isPlayable(trickSize, selection, lastCard);
		assertEquals(expResult, result);
	}
}
