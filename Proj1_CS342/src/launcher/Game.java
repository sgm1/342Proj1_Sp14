package launcher;

import java.util.Scanner;
import game_res.*;

public class Game {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner (System.in);
		//final Player winnerThusFar;
		Deck gameDeck = new Deck();
		
		//System.out.println(gameDeck.getNumCardsLeft());
		//t.replace(3);
		//stdIn.close();
		
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
			playerScores[i] = opponents[i].opponentTurn();
		}
		playerScores[numOpponents] = user.evaluateHand();
		
		/**
		 * Compares all metascores and ultimately decides who has the 
		 * best hand. Will eventually print the hand and type
		 */
		int winner = 0;
		int highScore = 0;
		for(int i=0; i<numOpponents+1; i++){
			if(playerScores[i]>highScore) {
				winner = i;
				highScore = playerScores[i];
			}
		}
		if(winner>=0 && winner<(numOpponents+1))
			System.out.println("Player " + (winner+1) + " is the winner, with a...");
		else
			System.out.println("You win! with a...");
		
	}
}
