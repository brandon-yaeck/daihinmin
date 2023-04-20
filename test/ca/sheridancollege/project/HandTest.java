package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class HandTest {
	Hand instance = new Hand();
	
	public HandTest() {
		instance.getCards().add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		instance.getCards().add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
		instance.getCards().add(new Card(Card.Suit.CLUBS, Card.Rank.THREE));
	}


	@Test
	public void testIsValidIndexGood() {
		System.out.println("isValidIndex Good");

		int index = 1;

		boolean expResult = true;
		boolean result = instance.isValidIndex(index);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsValidIndexBad() {
		System.out.println("isValidIndex Bad");

		int index = 5;

		boolean expResult = false;
		boolean result = instance.isValidIndex(index);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsValidIndexBoundary() {
		System.out.println("isValidIndex Boundary");

		int index = 2;

		boolean expResult = true;
		boolean result = instance.isValidIndex(index);
		assertEquals(expResult, result);
	}
}
