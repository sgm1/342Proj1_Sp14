package game_res;

import java.util.Scanner;

public class Player {
	
	private PokerHand hd;
	
	protected static Player winnerThusFar;
	
	protected Player(Deck deckToUse){ }

	/**
	 * Recursively creates a group of players to
	 * player poker. Practically a Linked List. 
	 * 
	 * Player will go first, followed by numPlayerAndEnemies - 1
	 * AI players
	 * 
	 * @param numPlayerAndEnemies Total group size
	 * @param deckToUse Deack used for the game
	 */
	public Player(int numPlayerAndEnemies, Deck deckToUse){
		if (numPlayerAndEnemies > 10 && deckToUse.getMaxSize() == 52){
			throw new IllegalArgumentException("Can't use more than 10 player with 1 deck");
		}
		hd = new PokerHand(deckToUse);
		winnerThusFar = this;
		if (numPlayerAndEnemies > 1)
			nextPlayer = new Enemy(numPlayerAndEnemies - 1, deckToUse);
	}
	
	
}
