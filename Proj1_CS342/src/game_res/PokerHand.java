package game_res;

public class PokerHand extends CardPile {
	private static Deck daDeck = null;// shared among all PokerHands
	private int rankCount[] = new int[15];
	private int suitCount[] = new int[5];
	private int typeOfHand;
	private CardPile toDisplay;
	
	/**
	 * Deck to be used for poker game. Currently
	 * restricted to 1 deck for all PokerHand's.
	 * 
	 * @param d The Deck to use (first deck passed will be used).
	 */
	public PokerHand(Deck d) {
		super(0, 1);//zero card deck, sorted by rank
		if (daDeck == null && d != null)
			daDeck = d;
		if (daDeck == null)
			throw new IllegalArgumentException();
		toDisplay = new CardPile();
		// handForDisplay = new ArrayList<Card>();
		drawFiveCards();
		sortBySuit(false);
	}

	/**
	 * Draws the initial 5 cards from deck.
	 */
	private void drawFiveCards() {
		for (int i = 0; i < 15; ++i) {
			rankCount[i] = 0;
			if (i < 5)
				suitCount[i] = 0;
		}
		daDeck.addToPile(this);
		for (int i = 0; i < 5; ++i) {
			Card temp = daDeck.getTop();
			addCard(temp);
			++rankCount[temp.rank];
			if (temp.rank == 14)
				++rankCount[1];// easily check ace straight
			++suitCount[temp.suit];
		}
		sortBySuit(false);
	}

	/**
	 * Replace a specific index of a
	 * Card.
	 * Check validity before call.
	 * 
	 * @param i Index of the card to be replaced.
	 */
	public void replace(int h) {
		if(h < 0 || h > 4 || get(h).rank == 14)
			throw new IllegalArgumentException("Invalid replace card index.");
		Card temp = remove(h);
		--rankCount[temp.rank];
		--suitCount[temp.suit];
		if (temp.rank == 14)
			--rankCount[1];// easily check ace straights
		temp = daDeck.getTop();
		addCard(temp);
		++rankCount[temp.rank];
		++suitCount[temp.suit];
		if (temp.rank == 14)
			++rankCount[1];// easily check ace straights
		sortBySuit(false);
		// return temp;
	}
	
	/**
	 * Practically "overload" for all Poker hands,
	 * Should check for validity before call.
	 * 
	 * @param h
	 * @param otherIndecies
	 */
	public void replace(int h, int ... otherIndecies) {
		if (otherIndecies.length > 4)
			throw new IllegalArgumentException("Too many Cards to replace.");
		if (otherIndecies.length == 4){
			int acesLeft = 0;
			for (int i = 0; i < 5; ++i) {
				if (get(i).rank == 14){
					acesLeft++;
				}
				if (i < otherIndecies.length){
					acesLeft--;
				}
			}
			if (acesLeft < 1)
				throw new IllegalArgumentException("Remaining card must be an ace");
		}
		replace(h);
		for (int i = 0; i < otherIndecies.length; i++){
			replace (otherIndecies[i]);
		}
	}
	
	/**
	 * Examine a specific card index
	 */
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
	
	@Override
	public String toString(){
		if (typeOfHand == 0)
			return super.toString();
		String temp = "";
		
		return temp;
	}

	/**
	 * This function is used to determine if either hand had
	 * a flush and a straight (and if so of leading value
	 * is used to determine winer).
	 * Then it proceeds to determine if either had a flush
	 * and finally if either had a straight.   
	 * 
	 * @param other PokerHand to compare to.
	 * @return Negative = other wins, positive = this wins, 0 = tie
	 */
	private int staightAndOrFlushCompare(PokerHand other) {
		int fl = flushCompare(other);
		int st = straightCompare(other);
		if (other.isFlush() && isFlush() && (other.isFlush() || isFlush())) {//is str8 flush
			return (st / 100) * 10000;// if a str8 flush, no suit info
		} else if (fl != 0)
			return fl;
		else return st;//uses suit info, if perfect tie of rank
	}

	private boolean isFlush() {
		for (int i : suitCount) {//for each loop
			if (i == 5)
				return true;
		}
		return false;
	}

	/**
	 * Compares flush, if one exists between the hands
	 * 
	 * @param other
	 * @return
	 */
	private int flushCompare(PokerHand other) {
		sortBySuit(false);
		boolean isF = isFlush(), otherIsF = other.isFlush();
		if (isF && otherIsF){
			int comp = 0;
			for (int i = 0; i < 5 && comp == 0; ++i){
				comp = get(i).rank - other.get(i).rank;
			}
			return comp; //compare top ranks loop, in cas eof ties
		}else if (isF)
			return 1;
		else if (otherIsF)
			return -1;
		else return 0;
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
		if (count == 5)
			return true;
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
	
	/**
	 * Evaluates a meta value to compare
	 * Best to least powerful:
	 * >Five of a kind (for multiple decks)
	 * >Four of a kind
	 * >Full house
	 * >Three of a kind
	 * >Two pair
	 * >Pair
	 * >High card 
	 * 
	 * @return
	 */
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
		typeOfHand = val;
		return val;
	}
}
