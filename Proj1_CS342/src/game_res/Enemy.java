package game_res;

public class Enemy extends Player {

	/**
	 * Basically an automated version
	 * of the Player
	 */
	
	public Enemy(Deck deckToUse, int i){
		super(deckToUse);
		System.out.println("Opponent " + (i+1) + " drew 5 cards.");
	}
	

}
