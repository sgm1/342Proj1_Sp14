package game_res;


public class PokerHand extends CardPile {
	private static Deck daDeck = null;// shared among all PokerHands
	private int rankCount[] = new int[15];
	private int suitCount[] = new int[5];
	private CardPile toDisplay;

	// private ArrayList<Card> handForDisplay;

	public PokerHand(Deck d) {
		super(0, 1);
		if (daDeck == null && d != null)
			daDeck = d;
		else if (daDeck != null)
			throw new IllegalArgumentException();
		toDisplay = new CardPile();
		// handForDisplay = new ArrayList<Card>();
		drawFiveCards();
		sortBySuit(false);
	}

	private void drawFiveCards() {
		for (int i = 0; i < 15; ++i) {
			rankCount[i] = 0;
			if (i < 5)
				suitCount[i] = 0;
		}
		addToPile(daDeck);
		for (int i = 0; i < 5; ++i) {
			Card temp = daDeck.getTop();
			addCard(temp);
			++rankCount[temp.rank];
			if (temp.rank == 14)
				++rankCount[1];// easily check ace straights
			++suitCount[temp.suit];
		}
		sortBySuit(false);
	}

	public void replace(int i) {
		Card temp = remove(i);
		--rankCount[temp.rank];
		--suitCount[temp.suit];
		temp = daDeck.getTop();
		addCard(temp);
		++rankCount[temp.rank];
		++suitCount[temp.suit];
		sortBySuit(false);
		// return temp;
	}
	
	public void replace(int h, int ... others) {
		replace(h);
		for (int i = 0; i < others.length; i++){
			replace (others[i]);
		}
	}

	public Card get(int i) {
		return super.get(i);
	}

	/**
	 * The function to call to compare two poker hands
	 * 
	 * @param other
	 * @return
	 */
	public int compareTo(PokerHand other) {
		int specialsVal = staightAndOrFlushCompare(other);
		int duplicatesVal = theRestVal() - other.theRestVal(); 
		if (specialsVal != 0)
			return specialsVal;
		else return duplicatesVal;
	}

	private int staightAndOrFlushCompare(PokerHand other) {
		int fl = flushCompare(other);
		int st = straightCompare(other);
		if (fl == 0 && isFlush()) {
			return (st / 100) * 10000;// if a str8 flush, no suit info
		} else if (fl != 0)
			return fl;
		else return st;
	}

	private boolean isFlush() {
		for (int i : suitCount) {
			if (i == 5)
				return true;
		}
		return false;
	}

	private int flushCompare(PokerHand other) {
		sortBySuit(false);
		int thisVal = 0, otherVal = 0;
		if (isFlush())
			thisVal = get(4).rank;// easily compare rank of high
		if (other.isFlush())
			otherVal = other.get(4).rank;// easily compare rank of high
		return thisVal - otherVal;
	}

	private boolean isStraight() {
		int count = 0;
		for (int i = 0; i < 15; i++) {
			if (count == 5) {
				return true;
			} else if (rankCount[count] == 1) {
				count++;
			} else {
				count = 0;
			}
		}
		return false;
	}

	private int straightCompare(PokerHand other) {
		sortBySuit(false);
		int thisVal = 0, otherVal = 0;
		if (isStraight())
			thisVal = get(4).rank * 100 + get(4).suit;// easily compare rank
														// then suit
		if (other.isStraight())
			otherVal = other.get(4).rank * 100 +
				other.get(4).suit;// easily compare rank then suit
		return thisVal - otherVal;
	}
	
	private int theRestVal(){
		int val = 0;
		int base = 0;//to take care of high card
		boolean foundTwoOfAKind = false;
		for (int i = 0; i < 15; i++) {
			if (rankCount[i] == 1 && i > base)
				base = i;
			if(!foundTwoOfAKind && rankCount[i] == 2)
				val += rankCount[i] * 100;
			if(foundTwoOfAKind && rankCount[i] == 2)
				val += rankCount[i] * 10000;
			if(rankCount[i] == 3)
				val += rankCount[i] * 1000000;
			if(rankCount[i] == 4)
				val += rankCount[i] * 100000000;
			if(rankCount[i] == 5)//multiple decks?
				val = 0x7FFFFFFF;//max_int32
		}
		val += base;
		return val;
	}
}
