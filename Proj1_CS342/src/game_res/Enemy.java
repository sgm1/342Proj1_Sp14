package game_res;

public class Enemy extends Player {
	public int oppNumber;
	public PokerHand hdo;
	int numToReplace = 0;
	int matches = 0;
	int[] toDiscard = new int[0]; //indices
	
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
		//this.showOppHand();
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
		Card tester;
		for(int i=0; i<5; i++) {
		    tester = hdo.get(i);
		    matches = 0;
			numToReplace = 0;
			int[] toDiscard = new int[5];
			for(int j=0; j<5; j++) {
				if(tester.rank == hdo.get(j).rank) {
					matches++; }
				else {
				   toDiscard[numToReplace] = j;
				   numToReplace++; }
				if(matches>1) {
					oppRedrawPhase();
					break; }	
		    }
		}
		System.out.println("Opponent " + oppNumber + " discarded " + matches + " cards.");
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
