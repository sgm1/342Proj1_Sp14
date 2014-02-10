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
		System.out.print("Opponent " + oppNumber + "'s hand: ");
		for(int i=0; i<super.hand; i++){
			System.out.print(i+1 +") [" + hdo.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}
	
	private void oppDiscardPhase() {
		// follow AI outline 
		System.out.println("Player " + oppNumber + " discarded nothing, cause he can't think yet!" );
		evaluateOppPhase();
	}

	private int evaluateOppPhase() {
		// evaluate opponent hand
		return hdo.getMetaValue();
	}
}
