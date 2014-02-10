package game_res;

import java.util.Scanner;

public class Player {
	Scanner stdIn = new Scanner (System.in);
	private PokerHand hd;
	protected Player(Deck deckToUse){ }
	private int hand = 5;

	/**
	 * 
	 * @param numEnemies total group size minus user
	 * @param deckToUse deck used for the game
	 */
	
	public Player(int numEnemies, Deck deckToUse){
		if (numEnemies > 9 && deckToUse.getMaxSize() == 52){
			throw new IllegalArgumentException("Can't use more than "
					+ "10 players with 1 deck");
		}
		hd = new PokerHand(deckToUse);
		System.out.println("You drew 5 cards.");
		//winnerThusFar = this;
	}
	
	public void showHand() {
		System.out.print("Your current hand: ");
		for(int i=0; i<hand; i++){
			System.out.print(i+1 +") " + hd.getDisplayedCard(i) + "  ");
		}
		System.out.println("");
	}
	
	public void discardPhase() {
		boolean ace = false;
		for(int i=0; i<hand; i++) {
			Card possibleAce = hd.get(i);
			if(possibleAce.rank == 14) {
				System.out.println("You drew an Ace! You can choose to keep " +
						"the Ace and discard your remaining 4 cards.");
				ace = true; }
		}
		
		System.out.print("How many cards do you want to discard? : ");
		int numToReplace = stdIn.nextInt();
		if((numToReplace==4 && ace==false) || (numToReplace>4 || numToReplace<0))
			throw new IllegalArgumentException("Can only discard up to three " +
					"cards without an Ace, and only up to 4 with");
		
		System.out.print("List the indices of the cards to discard? : ");
		for(int i=0; i<numToReplace; i++) {
			//Java have buffer overflow??
			int index = stdIn.nextInt();
			
			if(index>0 && index<6)
				hd.replace(i);
			else throw new IllegalArgumentException("Can only discard cards " +
				"with and index of 1 through 5");
			
			System.out.println("Discarded card at index " + (i+1));
		}
	}
}
