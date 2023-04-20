package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class CardTest {
	
	Card instance = new Card(Card.Suit.CLUBS, Card.Rank.FIVE);

	public CardTest() {
	}

	@Test
	public void testCheckMatch_CardGood() {
		System.out.println("checkMatch (Card) Good");

		Card card = new Card(Card.Suit.CLUBS, Card.Rank.FIVE);
		
		boolean expResult = true;
		boolean result = instance.checkMatch(card);
		assertEquals(expResult, result);
	}
	@Test
	public void testCheckMatch_CardBad() {
		System.out.println("checkMatch (Card) Bad");

		Card card = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);

		boolean expResult = false;
		boolean result = instance.checkMatch(card);
		assertEquals(expResult, result);
	}
	@Test
	public void testCheckMatch_CardBoundary() {
		System.out.println("checkMatch (Card) Boundary");

		Card card = new Card(Card.Suit.HEARTS, Card.Rank.FIVE);

		boolean expResult = true;
		boolean result = instance.checkMatch(card);
		assertEquals(expResult, result);
	}

	@Test
	public void testCheckMatch_CardRankGood() {
		System.out.println("checkMatch (Card.Rank) Good");

		Card.Rank rank = Card.Rank.FIVE;

		boolean expResult = true;
		boolean result = instance.checkMatch(rank);
		assertEquals(expResult, result);
	}
	@Test
	public void testCheckMatch_CardRankBad() {
		System.out.println("checkMatch (Card.Rank) Bad");

		Card.Rank rank = Card.Rank.KING;

		boolean expResult = false;
		boolean result = instance.checkMatch(rank);
		assertEquals(expResult, result);
	}
	// no possible boundary for this method
	
}
