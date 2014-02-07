package game_res;

public final class Card {
	public final int suit;
	public final int rank;

	/**
	 * Allows simplistic creation of custom decks (that allow for duplicate
	 * cards), 0-12 = Diamonds, 13-25 = Clubs, 26-38 = Hearts, 39-51 = Spades 
	 * 
	 * @param cNum
	 *            The number based on standard 52 card deck (0 - 51)
	 */
	public Card(int cNum) {
		if (cNum < 0)// makes sure that the card number is valid
			throw new IllegalArgumentException();
		suit = (cNum % 4) + 1;
		if (cNum % 13 == 0)
			rank  = 14;
		else
			rank = (cNum % 13) + 1;
	}

	/**
	 * Creates a specific instance of a card
	 * 
	 * @param s
	 *            Suit number (1 - 4)
	 * @param r
	 *            Rank number (2 - 14)
	 */
	public Card(int s, int r) {
		if (s < 1 || r < 2 || s > 4 || r > 14)// makes sure that the suit/rank
												// number is valid
			throw new IllegalArgumentException();
		suit = s;
		rank = r;
	}

	//@Override annotation just to make sure I'm overriding
	//    Object.toString()
	//Not necessary
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
			case 13:
				temp += 'K';// King
				break;
			default:
				temp += 'A';// Ace
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
	
	/**
	 * Custom compareTo function
	 * 
	 * @param other The other Card
	 * @param sMajor If it is suit major
	 * @return 0 = equal, 1 = this > other, -1 = this < other
	 */
	public int compareTo(Card other, boolean sMajor){
		if (other.rank == this.rank && other.suit == this.suit)
			return 0;
		else if (sMajor){
			if (this.suit > other.suit)
				return 1;
			else if (this.suit < other.suit)
				return -1;
			//intentional passing of == case
			if (this.rank > other.rank)
				return 1;
			return 0;
		} else{
			if (this.rank > other.rank)
				return 1;
			else if (this.rank < other.rank)
				return -1;
			//intentional passing of == case
			if (this.suit > other.suit)
				return 1;
			return 0;
		}
		
	}
}
