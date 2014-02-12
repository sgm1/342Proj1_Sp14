package game_res;

public class PokerHand extends CardPile {
	private static Deck daDeck = null;// shared among all PokerHands
	private int rankCount[] = new int[15];
	private int suitCount[] = new int[5];
	private int typeOfHand;
	private int printOrder[]; 
	
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
		// handForDisplay = new ArrayList<Card>();
		drawFiveCards();
		theRestVal();
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
	 * If value is greater than 14, better than a pair
	 * 
	 *  Otherwise the greatest card is returned
	 * 
	 * @return Meta value for hand.
	 */
	public int getMetaValue(){
		int temp = theRestVal();//sets type of hand
		if (typeOfHand == 0)
			return 0x7FFFFFFF;
		else return temp;
	}

	/**
	 * Replace a specific index of a
	 * Card.
	 * Check validity before call.
	 * 
	 * @param i Index of the card to be replaced.
	 */
	public void replace(int h) {
		if(h < 0 || h > 4)
			throw new IllegalArgumentException("Invalid replace card index.");
		Card temp = remove(printOrder[h]);
		--rankCount[temp.rank];
		--suitCount[temp.suit];
		if (temp.rank == 14)
			--rankCount[1];// easily check ace 4 straights
		daDeck.discard(temp);
		temp = daDeck.getTop();
		addCard(temp);
		++rankCount[temp.rank];
		++suitCount[temp.suit];
		if (temp.rank == 14)
			++rankCount[1];// easily check ace 4 straights
		sortBySuit(false);
		theRestVal();
		// return temp;
	}
	
	/**
	 * Practically "overload" for all Poker hands,
	 * Should check for validity before call.
	 * 
	 * @param h
	 * @param otherIndecies
	 */
	public void replace(int [] otherIndecies) {
		if (otherIndecies.length > 4)
			throw new IllegalArgumentException("Too many Cards to replace.");
		if (otherIndecies.length == 4){
			int indexToCheck = 1 + 2 + 3 + 4;
			for (int i = 0; i < 4; ++i) {
				indexToCheck -= otherIndecies[i];
			}
			if (getDisplayedCard(indexToCheck).rank != 14)//TODO throw error, or print out error?
				throw new IllegalArgumentException("Remaining card must be an ace, when dicarding 4");
		}
		for (int i = 0; i < otherIndecies.length; i++){
			replaceHelper (otherIndecies[i]);
		}
		sortBySuit(false);
		theRestVal();
	}
	
	/**
	 * Examine a specific card index (same as
	 * printed order).
	 * @param i
	 * @return
	 */
	public Card getDisplayedCard(int i){
		theRestVal();
		setPrintOrder();//sets printOrder
		return get(printOrder[i]);
	}
	
	/**
	 * Maybe want to use getDisplayedCard!!!
	 * 
	 * Examine a specific card index (maybe be different
	 * from printed order)
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
		if (specialsVal != 0){
			typeOfHand = 0;
			return specialsVal;
		}
		else return duplicatesVal;
	}
	
	/**
	 * Prints the major 2/3/4 of a kind first,
	 * then the rest of the cards
	 */
	@Override
	public String toString(){
		//System.out.println("Type of hand: " + typeOfHand);
		if (typeOfHand == 0)
			return super.toString();
		String temp = "";
		setPrintOrder();
		for (int i = 0; i < 5; i++){
			temp += getDisplayedCard(i) + ", ";
		}
		//System.out.println("Type: " + typeOfHand + " Actual :" + super.toString());
		return temp;
	}
	
	@Override
	protected void sortBySuit(boolean b){
		super.sortBySuit(b);
		printOrder = new int[5];
		for (int i = 0; i < 5; ++i) {
			printOrder[i] = i;
		}
	}
	
	private void setPrintOrder(){
		//System.out.println(super.toString() + "Type o hand: " + typeOfHand);
		if (typeOfHand == 1){// pairf
			for (int i = 0, j = 0, k = 2; i < 5; i++){
				if (rankCount[get(i).rank] == 2){
					printOrder[j] = i;
					j++;
				}else if (rankCount[get(i).rank] == 1){
					printOrder[k] = i;
					k++;
				}
			}
		}else if (typeOfHand == 2){// 2 pair
			for (int i = 0, j = 0; i < 5; i++){
				if (rankCount[get(i).rank] == 2){
					printOrder[j] = i;
					j++;
				}
				else if (rankCount[get(i).rank] == 1)
					printOrder[4] = i;
			}
		}else if (typeOfHand == 3){ // 3 of a kind or full house
			for (int i = 0, j = 0, k = 3; i < 5; i++){
				if (rankCount[get(i).rank] == 3){
					printOrder[j] = i;
					j++;
				}else{
					printOrder[k] = i;
					k++;
				}
			}
		}else if (typeOfHand == 4){// four of a kind
			for (int i = 0, j = 0; i < 5; i++){
				if (rankCount[get(i).rank] == 4){
					printOrder[j] = i;
					j++;
				}else{
					printOrder[4] = i;
				}
			}
		}
	}
	
	private void replaceHelper(int h) {
		if(h < 0 || h > 4)
			throw new IllegalArgumentException("Invalid replace card index.");
		
		Card temp = remove(printOrder[h]);
		--rankCount[temp.rank];
		--suitCount[temp.suit];
		if (temp.rank == 14)
			--rankCount[1];// easily check ace 4 straights
		daDeck.discard(temp);
		temp = daDeck.getTop();
		addCard(temp, printOrder[h]);
		++rankCount[temp.rank];
		++suitCount[temp.suit];
		if (temp.rank == 14)
			++rankCount[1];// easily check ace 4 straights
		// return temp;
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
	 * after straights and flushes
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
		typeOfHand = -1;
		if (isFlush() || isStraight()){
			typeOfHand = 0;
			return 0x7FFFFFFF;// this return shouldn't really be used
		}
			
		for (int i = 0; i < 15; i++) {
			if (rankCount[i] == 1 && i > base){
				base = i;
			}
			if(!foundTwoOfAKind && rankCount[i] == 2){
				typeOfHand = 1;
				val += i * 100;// put 2nd 2 of a kind in 100's place
				foundTwoOfAKind = true;
			}else if(foundTwoOfAKind && rankCount[i] == 2){
				typeOfHand = 2;
				val += i * 10000;// put 2nd 2 of a kind in 10 thousands place
			}else if(rankCount[i] == 3){
				typeOfHand = 3;// should take care of fullhouse too
				val += i * 1000000;// put 3 of a kind in 1 millions place
			}else if(rankCount[i] == 4){
				typeOfHand = 4;
				val += i * 100000000;//put 4 of a kind value in 100 millions place
			}else if(rankCount[i] == 5){//multiple decks?
				typeOfHand = 0;
				val = 0x7FFFFFFF;//max_int32
			}
		}
		val += base;
		if (typeOfHand == -1){
			typeOfHand = 0;
		}
		//System.out.println("DEBUG: VAL:" + val);
		return val;
	}
}
