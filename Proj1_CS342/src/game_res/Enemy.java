package game_res;

/**
 * Automated version of player
 * If contains any type of hand (other than a 
 * high card hand). then discards and replaces
 * the cards that do not make up the value
 * of the hand
 *
 */
public class Enemy extends Player {
	public int oppNumber;
	int[] toDiscard = new int[0];

	/**
	 * Basically an automated extension of the Player class
	 */
	public Enemy(Deck deckToUse, int i) {
		super(deckToUse);
		hd = new PokerHand(deckToUse);
		oppNumber = i + 1;
		System.out.println("Opponent " + oppNumber + " drew 5 cards.");
	}

	/**
	 * Calling method of Enemy class; initiates opponents turn
	 */
	public void opponentTurn() {
		//this.showHand();
		this.oppDiscardPhase();
		return;
	}

	/**
	 * Variant of Player class method, simply calls method to display the
	 * current opponents hand
	 */
	public void showHand() {
		System.out.print("Opp" + oppNumber + " hand: ");
		for (int i = 0; i < super.hand; i++) {
			System.out.print(i + 1 + ") [" + hd.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}

	/**
	 * AI implication should begin here...
	 */
	private void oppDiscardPhase() {
		// follow AI outline //
		
		/* check four of same suit, discard remainder */
		/* check four in sequence, discard remainder */
		/* if have Ace, discard all others */
		/* else top two valued cards */
		
		int val = hd.getMetaValue();
		//System.out.println(">>>>>>>" + val);
		int temp[];
		//showHand();
		if (val == 0x7FFFFFFF) {
			//straight and or flush
		} else if (val / 100000000 > 0) {
			System.out.println("Opp" + oppNumber + " replaced card 4");
			try {
				hd.replace(4);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} else if (val / 10000 > 0) {//at least 2 pair
			if ((val / 1000000) > 0 && ((val % 10000) / 100 > 0)) {// a full
																	// house
				// replace nothing
			} else if (val / 1000000 > 0) {// only a three of a kind
				temp = new int[2];
				temp[0] = 3;
				temp[1] = 4;
				System.out.println("Opp" + oppNumber + " replaced cards 3 and 4");
				try {
					hd.replace(temp);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else if (val / 10000 > 0) { //2 pair
				System.out.println("Opp" + oppNumber + " replaced card 4"); 
				try {
					hd.replace(4);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

		} else if (!almostSomething()){// two of a kind or 2 high cards
			temp = new int[3];
			temp[0] = 2;
			temp[1] = 3;
			temp[2] = 4;
			System.out.println("Opp" + oppNumber + " replaced cards 3, 4, 5");
			try{
				hd.replace(temp);
			}
			catch (IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Returns if the replace for almost straight or
	 * flush is successful
	 * @return
	 */
	private boolean almostSomething(){
		if (hd.getMetaValue() / 100 > 0)// at least 2 of a kind
			return false;
		//guaranteed no duplicates beyond this point
		int rc[] = new int[15];
		int sc[] = new int[5];
		int st = -1;
		boolean isAlmostFlush = false;
		for (int i = 0; i < 5; ++i) {
			Card temp = hd.get(i);
			++rc[temp.rank];
			if (temp.rank == 14)
				++rc[1];// easily check ace straight
			++sc[temp.suit];
			if (sc[temp.suit] == 4){//4 of a suit
				isAlmostFlush = true;
				st = temp.suit;// suit that is almsot flush
			}
		}
		for (int i = 1; i < 9; i++){
			if (rc[i] + rc[i + 1] + rc[i + 2] + rc[i + 3] + rc[i + 4] == 4){
				int toRep = 0;
				for (; toRep < 5; toRep++){
					if (rc[toRep] == 0)
						break;
				}
				hd.replace(toRep + 1);
				System.out.println("Opp" + oppNumber + " replaced card " + (toRep + 1));
				break;
			}
		}
		if (isAlmostFlush){
			int toRep = 0;
			for (; toRep < 5; toRep++){
				if (hd.get(toRep).suit != st)
					break;
			}
			hd.replace(toRep + 1);
			System.out.println("Opp" + oppNumber + " replaced card " + (toRep + 1));
			
		}
		return true;
	}
}
