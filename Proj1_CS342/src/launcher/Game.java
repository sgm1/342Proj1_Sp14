package launcher;

import java.util.Scanner;
import game_res.*;

public class Game {

	public static void main(String[] args) {
		//Scanner stdIn = new Scanner (System.in);
		//System.out.print("Enter number of players (max 9) : ");// PLEASE RESTRICT TO MAX 9
		//int i = stdIn.nextInt();
		//System.out.println(i + " players will be created");
		Deck temp = new Deck();
		PokerHand s = new PokerHand(temp);
		PokerHand t = new PokerHand(temp);
		System.out.println(temp.getNumCardsLeft());
		System.out.println("S: " + s);
		System.out.println("T: " + t);
		System.out.println("   0   1   2   3   4");
		t.replace(0, 1);
		//t.replace(3);
		System.out.println("Replace 0, 1 (may be reordered), T: " + t);
		System.out.print(s.compareTo(t) > 0? "S wins": "T wins");

		
		//stdIn.close();
		//>>>>>>>>>>>>>>>>>>Let's see if you see this<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//more comments
	}
}
