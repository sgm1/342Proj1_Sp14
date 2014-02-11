package game_res;

public class Enemy extends Player {
	public int oppNumber;
	public PokerHand hdo;
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
		oppDiscardPhase();
		//showOppHand();
		return 0;
	}
	
	public void showOppHand() {
		System.out.print("Opp" + oppNumber + " hand: ");
		for(int i=0; i<super.hand; i++){
			System.out.print(i+1 +") [" + hdo.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}
	
	private void oppDiscardPhase() {
		// follow AI outline 
		System.out.println("Opponent " + oppNumber + " discarded nothing, as can't think yet!" );
		evaluateOppPhase();
	}

	private int evaluateOppPhase() {
		return hdo.getMetaValue();
	}
	
	public int compareTo(Enemy other) {
		return this.hdo.compareTo(other.hdo);
	}
}
