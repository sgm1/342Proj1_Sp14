package game_res;

public class Enemy extends Player {
	int oppNumber;
	private PokerHand hdo;
	boolean ace = false;
	int numToReplace = 0;
	int aceCount = 0;
	
	/**
	 * Basically an automated version
	 * of the Player
	 */
	
	public Enemy(Deck deckToUse, int i) {
		super(deckToUse);
		hdo = new PokerHand(deckToUse);
		oppNumber = i+1;
		System.out.println("Opponent " + oppNumber + " drew 5 cards.");
	}
	
	public int opponentTurn() {
		showOppHand();
		oppDiscardPhase();
		showOppHand();
		return evaluateOppPhase();
	}
	
	public void showOppHand() {
		System.out.print("Opponent " + oppNumber + "'s current hand: ");
		for(int i=0; i<super.hand; i++){
			System.out.print(i+1 +") [" + hdo.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
		oppDiscardPhase();
	}
	
	private void oppDiscardPhase() {
		// follow AI outline 
		evaluateOppPhase();
	}

	private int evaluateOppPhase() {
		// evaluate opponent hand
		return 0; // for now, ultimately return metascore?
	}
}
