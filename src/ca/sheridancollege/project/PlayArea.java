package ca.sheridancollege.project;

/**
 * Singleton class representing the only area where cards are placed.
 *
 * @author Brandon Yaeck, April 2023
 */
public class PlayArea {

	private static PlayArea onlyObject = null;
	private GroupOfCards cards = new GroupOfCards();

	private PlayArea() {
	}

	public GroupOfCards getCards() {
		return cards;
	}

	public void clearPlay() {
		cards.clearCards();
	}

	public static PlayArea getInstance() {
		if (onlyObject == null) {
			onlyObject = new PlayArea();
		}

		return onlyObject;
	}
}
