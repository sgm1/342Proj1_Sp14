package launcher;

import java.util.Scanner;
import game_res.*;

public class Game {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner (System.in);
		int winnerThusFar;	//tracks current winner
		Deck gameDeck = new Deck();	//deck to be used through program
		
		System.out.print("How many players? (max 10) : ");
		int numOpponents = stdIn.nextInt()-1;
		System.out.println("Creating 1 user and " + (numOpponents) +  " opponents.");
		
		/**
		 * Initializes the appropriate number of players and opponents, 
		 * deals everyone their hands
		 */
		Player user = new Player(numOpponents,gameDeck);
		Enemy[] opponents = new Enemy[numOpponents];
		for(int i=0; i<numOpponents; i++) {
			opponents[i] = new Enemy(gameDeck,i);
		}
		
		/**
		 * Handles the users discard+redraw turn, all of the opponents turn,
		 * and then lastly *returns to user to give score
		 */
		user.decideHand();
		for(int i=0; i<numOpponents; i++) {
			opponents[i].opponentTurn();
		}
		
		/**
		 * Compares all hands and decides who has the best hand. 
		 * Assumes user is winner, then checks against opponents..
		 * Will eventually print the hand and type
		 */
		user.showHand();
		winnerThusFar = 0; // initialized to player
		boolean userWinning = true;
		for(int i=0; i<numOpponents; i++) {
			opponents[i].showOppHand();
			if(userWinning) {
				/* user does not have better hand */
				if(user.compareTo(opponents[i]) < 0) { 
					winnerThusFar = opponents[i].oppNumber; 
					userWinning = false; }
			}
			else {
				/* following compareTo is slightly different than previous */
				if(opponents[winnerThusFar-1].compareTo(opponents[i]) < 0) {
					winnerThusFar = opponents[i].oppNumber; }
			}	
		}
		if(winnerThusFar==0) {
			System.out.println("You win! with a...");
			user.showHand(); }
		else {
			/* sometimes last player loses to previous for no reason */
			System.out.println("Opponent " + winnerThusFar + " is the winner, with a...");
			opponents[winnerThusFar-1].showOppHand(); }
		
		System.out.println("Thank you for paying!");
		stdIn.close();
	}
}