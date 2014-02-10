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
		System.out.println(temp.getNumCardsLeft());
		//System.out.println("   0   1   2   3   4");
		Player theEntireGame = new Player(2, temp);
		//t.replace(3);
		
		//stdIn.close();
		//>>>>>>>>>>>>>>>>>>Let's see if you see this<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//more comments
		
		//System.out.println("How many players total?");
	}
}
