package ca.sheridancollege.project;

/**
 * Singleton class representing the only area where cards are placed.
 *
 * @author Brandon Yaeck, April 2023
 */
public class PlayArea extends GroupOfCards {

	private static PlayArea onlyObject = null;

	private PlayArea() {
	}

	public static PlayArea getInstance() {
		if (onlyObject == null) {
			onlyObject = new PlayArea();
		}

		return onlyObject;
	}

}
