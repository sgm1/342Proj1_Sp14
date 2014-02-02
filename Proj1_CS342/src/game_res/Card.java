package game_res;

public class Card {
	public final int suit;
	public final int rank;

	@SuppressWarnings("unused")
	private Card() {
		suit = 0;
		rank = 0;
	}// means no one will be able to use default constructor

	/**
	 * Allows simplistic creation of custom decks (that allow for duplicate
	 * cards)
	 * 
	 * @param i
	 *            The number based on standard 52 card deck (0 - 51)
	 */
	public Card(int i) {
		if (i < 0)// makes sure that the card number is valid
			throw new IllegalArgumentException();
		suit = (i % 4) + 1;
		rank = (i % 13) + 1;
	}

	/**
	 * Creates a specific instance of a card
	 * 
	 * @param s
	 *            Suit number (1 - 4)
	 * @param r
	 *            Suit number (1 - 13)
	 * @throws Exception
	 */
	public Card(int s, int r) {
		if (s < 1 || r < 1 || s > 4 || r > 13)// makes sure that the suit/rank
												// number is valid
			throw new IllegalArgumentException();
		suit = s;
		rank = r;
	}

	@Override
	public String toString() {
		String temp = "";
		if (rank < 10)
			temp += rank;// 1 to 9
		else {
			switch (rank) {
			case 10:
				temp += 'T';// Ten
				break;
			case 11:
				temp += 'J';// Jack
				break;
			case 12:
				temp += 'Q';// Queen
				break;
			default:
				temp += 'K';// King
				break;
			}
		}
		switch (suit) {
		case 1:
			temp += 'D';// Diamond
			break;
		case 2:
			temp += 'C';// Club
			break;
		case 3:
			temp += 'H';// Heart
			break;
		default:
			temp += 'S';// Spade
			break;
		}
		return temp;
	}
}
