package game_res;

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
		int temp[];
		//showHand();
		if (val == 0x7FFFFFFF) {
			//straight and or flush
		} else if (val / 100000000 > 0) {
			// at least 3 of a kind
			System.out.println("Opp" + oppNumber + " replaced card 4");
			try {
				hd.replace(4);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			//System.out.println("DEBUG:");
			//showHand();
			//System.out.println("DEBUG:");
		} else if (val / 10000 > 0) {
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
				// System.out.println("DEBUG:");
				// showHand();
				// System.out.println("DEBUG:");
			} else if (val / 10000 > 0) {
				System.out.println("Opp" + oppNumber + " replaced card 4"); 
				try {
					hd.replace(4);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				// System.out.println("DEBUG:");
				// showHand();
				// System.out.println("DEBUG:");
			}

		} else {// two of a kind or 2 high cards
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
	 * Replaces cards to be discarded with new ones
	 */
	private void oppRedrawPhase() {
		try {
			hd.replace(toDiscard);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			oppDiscardPhase();
		}
	}

}
