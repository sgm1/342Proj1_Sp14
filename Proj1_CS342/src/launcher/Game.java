package launcher;

import java.util.Scanner;

import game_res.*;

public class Game {

	public static void main(String[] args) {
		//Scanner stdIn = new Scanner (System.in);
		//System.out.print("Enter number of players : ");
		//int i = stdIn.nextInt();
		//System.out.println(i + " players will be created");
		Deck temp = new Deck();
		PokerHand s = new PokerHand(temp);
		System.out.println(temp.getNumCardsLeft());
		System.out.println(s);
		
		//stdIn.close();
		//>>>>>>>>>>>>>>>>>>Let's see if you see this<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//more comments
	}
}
