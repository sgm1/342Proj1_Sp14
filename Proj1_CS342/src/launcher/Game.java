package launcher;

import java.util.Scanner;
import game_res.*;

public class Game {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner (System.in);
		int winnerThusFar;
		Deck gameDeck = new Deck();
		
		//System.out.println(gameDeck.getNumCardsLeft());
		//t.replace(3); merged
		
		System.out.print("How many players? (max 10) : ");
		int numOpponents = stdIn.nextInt()-1;
		System.out.println("Creating 1 user and " + (numOpponents) +  " opponents.");
		
		/**
		 * Initializes the appropriate number of players and opponents, 
		 * deals everyone their hands, and creates array to store their scores
		 */
		int[] playerScores = new int[numOpponents+1];;
		Player user = new Player(numOpponents,gameDeck);
		Enemy[] opponents = new Enemy[numOpponents];
		for(int i=0; i<numOpponents; i++) {
			opponents[i] = new Enemy(gameDeck,i);
		}
		
		/**
		 * Handles the users discard and redraw turn; all of the opponents discards, 
		 * redraws, and final scores; then lastly returns to user to give score
		 */
		user.decideHand();
		for(int i=0; i<numOpponents; i++) {
			opponents[i].opponentTurn();
		}
		
		/**
		 * Compares all hands and decides who has the 
		 * best hand. Assumes user is winner, checks if valid..
		 * Will eventually print the hand and type
		 */
		user.showHand();
		winnerThusFar = 0; // initialized to player
		
		for(int i=0; i<numOpponents; i++) {
			opponents[i].showOppHand();
			if(user.compareTo(opponents[i]) < 0) {
				winnerThusFar = opponents[i].oppNumber;
			}
		}
		if(winnerThusFar==0) {
			System.out.println("You win! with a...");
			user.showHand(); }
		else {
			System.out.println("Opponent " + winnerThusFar + " is the winner, with a...");
			opponents[winnerThusFar-1].showOppHand(); }
		
		System.out.println("Thank you for paying!");
		stdIn.close();
	}
}