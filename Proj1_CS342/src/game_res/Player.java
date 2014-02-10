package game_res;

import java.util.Scanner;

public class Player {
	Scanner stdIn = new Scanner (System.in);
	private PokerHand hd;
	protected Player(Deck deckToUse){ }
	protected int hand = 5;
	boolean ace = false;
	int numToReplace = 0;
	int aceCount = 0;

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
	
	public void decideHand() {
		this.showHand();
		this.discardPhase();
		this.redrawPhase();
		this.showHand();
		this.evaluate();
		return;
	}
	
	 /**
	  * Displays users current hand
	  */
	public void showHand() {
		System.out.print("Your current hand: ");
		for(int i=0; i<hand; i++){
			System.out.print(i+1 +") [" + hd.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}
	
	/**
	 * Handles the users discarding phase
	 * checks for ace, if yes allows user to discard 4 cards,
	 * otherwise only allows discarding up to 3 cards
	 * also handles error checking
	 */
	private void discardPhase() {
		for(int i=0; i<hand; i++) {
			Card possibleAce = hd.get(i);
			if(possibleAce.rank == 14) {
				System.out.println("You drew an Ace! You can choose to keep " +
						"the Ace and discard your remaining 4 cards.");
				ace = true; 
				aceCount+=1; }
		}
		
		System.out.print("How many cards do you want to discard? : ");
		numToReplace = stdIn.nextInt();
		if((numToReplace==4 && ace==false) || (numToReplace>4 || numToReplace<0))
			throw new IllegalArgumentException("Can only discard up to three " +
					"cards without an Ace, and only up to 4 with");
		
	}
	
	private void redrawPhase() {
		int[] indices = {0,0,0,0,0};
		if(numToReplace!=0) {
			System.out.print("List the indices of the cards to discard? : ");
			for(int i=0; i<numToReplace; i++) {
				//Java have buffer overflow??
				int index = stdIn.nextInt()-1;
			
				if(ace==true && numToReplace==4 && index>(0-1) && index<(4+1)) {
					Card possibleAce = hd.get(index);
					if(possibleAce.rank == 14) {
						aceCount-=1;
						if(aceCount<1) 
							throw new IllegalArgumentException("When choosing to " 
								+ "discard 4 cards, cannot discard last Ace");
					}
				}
				
				if(index>(0-1) && index<(5+1))
					indices[i] = index;
				else throw new IllegalArgumentException("Can only discard cards " 
					+ "with and index of 1 through 5");
			    //need help implementing either replace method, tried both; h??
				System.out.println("Discarded card at index " + (index+1));
			}
		}
	}
	
	private int evaluate() {
		return 0; // for now, ultimately return metascore?
	}
}