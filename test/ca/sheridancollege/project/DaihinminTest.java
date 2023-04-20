package ca.sheridancollege.project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Brandon Yaeck, April 2023
 */
public class DaihinminTest {
	
	Daihinmin instance = new Daihinmin();

	public DaihinminTest() {
	}
	
	@Test
	public void testGetTrickSizeGood() {
		System.out.println("getTrickSize Good");

		int maxTrickSize = 4;
		String input = "2";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		

		int expResult = 2;
		int result = instance.getTrickSize(maxTrickSize);
		assertEquals(expResult, result);
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@Test
	public void testGetTrickSizeBad() {
		System.out.println("getTrickSize Bad");

		int maxTrickSize = 4;
		String input = "9";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		

		// expecting exception for bad input
		exception.expect(NoSuchElementException.class);
		instance.getTrickSize(maxTrickSize);
	}

	@Test
	public void testGetTrickSizeBoundary() {
		System.out.println("getTrickSize Boundary");

		int maxTrickSize = 4;
		String input = "4";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		

		int expResult = 4;
		int result = instance.getTrickSize(maxTrickSize);
		assertEquals(expResult, result);
	}
	
}
