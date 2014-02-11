package game_res;

public class Enemy extends Player {
	public int oppNumber;
	public PokerHand hdo;
	int[] toDiscard = new int[0];
	
	/**
	 * Basically an automated extension of
	 * the Player class
	 */
	public Enemy(Deck deckToUse, int i) {
		super(deckToUse);
		hdo = new PokerHand(deckToUse);
		oppNumber = i+1; 
		System.out.println("Opponent " + oppNumber + " drew 5 cards.");
	}
	
	/**
	 * Calling method of Enemy class; initiates opponents turn
	 */
	public void opponentTurn() {
		this.showOppHand();
		this.oppDiscardPhase();
	}
	
	/**
	 * Variant of Player class method, simply calls method to display the
	 * current opponents hand
	 */
	public void showOppHand() {
		System.out.print("Opp" + oppNumber + " hand: ");
		for(int i=0; i<super.hand; i++){
			System.out.print(i+1 +") [" + hdo.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}
	
	/**
	 * AI implication should begin here...
	 */
	private void oppDiscardPhase() {
		// follow AI outline
		int[] matchesPerCard = new int[5];
		for(int i=0; i<5; i++) {
		    Card tester = hdo.get(i);
		    int matches = 0;
			for(int j=0; j<5; j++) {
				if(tester.rank == hdo.get(j).rank) {
					matches++; }
		    }
			matchesPerCard[i] = matches;
		}
		/*** Shows for each index how many matches of card in the hand ***
		System.out.println("Number matches per index: " + matchesPerCard[0]
				+ matchesPerCard[1]  + matchesPerCard[2]  + matchesPerCard[3]
				+ matchesPerCard[4]);
		*/
		/* look at comments below*/
		for(int i=0; i<5; i++) {					
			if(matchesPerCard[i] == 4) {			
				int[] toDiscard = new int[1];		
				for(int k=0; k<5; k++) {			
					if(matchesPerCard[k] != 4) {	
						toDiscard[0] = matchesPerCard[k];	 
						oppRedrawPhase();	
						return;				
					}
				}
			}
		}
		for(int i=0; i<5; i++) {				//scan through the index list
			if(matchesPerCard[i] == 3) {		//see if any index has value of 4
				int[] toDiscard = new int[2];	//create array to hold value(s)
				int counter = 0;				//create counter for indices found
				for(int k=0; k<5; k++) {		//scan through again, to look for !match
					if(matchesPerCard[k] != 3) {	//this time accept on complement
						toDiscard[counter] = matchesPerCard[k]; //throw those indices in
						counter++;					//increment counter when index found
						if(counter == 2) {		//accept when discard = hand - matches
							oppRedrawPhase();	//call the replace with gathered indices
							return; }			//make sure to leave method after the swap
					}	
				}
			}
		}
		/* look at previous comments */
		for(int i=0; i<5; i++) {
			if(matchesPerCard[i] == 2) {
				int[] toDiscard = new int[3];
				int counter = 0;
				for(int k=0; k<5; k++) {
					if(matchesPerCard[k] != 2) {
						toDiscard[counter] = matchesPerCard[k];
						counter++;
						if(counter == 3) {
						   oppRedrawPhase();
						   return; }
					}
				}
			}
		}
		
		/* else top two valued cards */
		for(int i=0; i<5; i++) {
			
		}
	}
    
	/**
	 * Replaces cards to be discarded with new ones
	 */
	private void oppRedrawPhase() {
		try { hdo.replace(toDiscard); } 
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			oppDiscardPhase();
		}
	}
	
	/**
	 * Handles comparing one opponents hand to another
	 * @param Enemy other
	 * 		an opponents hand being compared to current opponent
	 * @return 
	 * 		returns either negative, zero, or positive number
	 */
	public int compareTo(Enemy other) {
		return this.hdo.compareTo(other.hdo);
	}
}
