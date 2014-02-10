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
	public void discardPhase() {
		boolean ace = false;
		int aceCount = 0;
		for(int i=0; i<hand; i++) {
			Card possibleAce = hd.get(i);
			if(possibleAce.rank == 14) {
				System.out.println("You drew an Ace! You can choose to keep " +
						"the Ace and discard your remaining 4 cards.");
				ace = true; 
				aceCount+=1; }
		}
		
		System.out.print("How many cards do you want to discard? : ");
		int numToReplace = stdIn.nextInt();
		if((numToReplace==4 && ace==false) || (numToReplace>4 || numToReplace<0))
			throw new IllegalArgumentException("Can only discard up to three " +
					"cards without an Ace, and only up to 4 with");
		
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
			    
				System.out.println("Discarded card at index " + (index+1));
			}
		}
        // need to actually apply replace function.. couldnt figure out how to
	}
}