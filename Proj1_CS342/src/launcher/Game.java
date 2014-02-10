package launcher;

import java.util.Scanner;
import game_res.*;

public class Game {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner (System.in);
		final Player winnerThusFar;
		Deck gameDeck = new Deck();
		
		//System.out.println(gameDeck.getNumCardsLeft());
		//t.replace(3);
		//stdIn.close();
		
		System.out.print("How many players? (max 10) : ");
		int numOpponents = stdIn.nextInt()-1;
		System.out.println("Creating 1 user and " + (numOpponents) +  " opponents.");
		
		/**
		 * Initializes the appropriate number of players and opponents, 
		 * deals them their hands, and *evaluates their moves
		 */
		Player user = new Player(numOpponents,gameDeck);
		Enemy[] opponents = new Enemy[numOpponents];
		for(int i=0; i<numOpponents; i++) {
			opponents[i] = new Enemy(gameDeck,i);
		}
		
		user.decideHand();
		for(int i=0; i<numOpponents; i++) {
			opponents[i].opponentTurn();
		}
	}
}
