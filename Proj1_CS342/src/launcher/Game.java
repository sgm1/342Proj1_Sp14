package launcher;

import java.util.Scanner;

import game_res.*;

public class Game {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner (System.in);
		System.out.print("Enter number of players : ");
		int i = stdIn.nextInt();
		System.out.println(i + " players will be created");
	}

}
