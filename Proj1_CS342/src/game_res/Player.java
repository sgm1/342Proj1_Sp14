package game_res;

import java.util.Scanner;

public class Player {
	
	private PokerHand hd;
	protected Player(Deck deckToUse){ }

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
	
}
