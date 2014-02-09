package game_res;

public class Enemy extends Player {

	/**
	 * Basically an automated version
	 * of the Player
	 */
	public Enemy(int numEnemies, Deck deckToUse){
		super(deckToUse);
		if (numEnemies > 1)
			nextPlayer = new Enemy(numEnemies - 1, deckToUse);
		
	}

}
