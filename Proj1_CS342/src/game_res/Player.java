package game_res;

import java.util.Scanner;

public class Player {
	Scanner stdIn = new Scanner(System.in);
	private PokerHand hd;

	protected Player(Deck deckToUse) {
	}

	protected int hand = 5;
	int aceCount = 0;
	boolean ace = false;
	boolean flush = false;
	boolean straight = false;
	int numToReplace = 0;
	int[] indices = new int[0];

	/**
	 * @param numEnemies
	 *            total group size minus user
	 * @param deckToUse
	 *            deck used for the game
	 */

	public Player(int numEnemies, Deck deckToUse) {
		if (numEnemies > 9 && deckToUse.getMaxSize() == 52) {
			throw new IllegalArgumentException("Can't use more than "
					+ "10 players with 1 deck");
		}
		hd = new PokerHand(deckToUse);
		System.out.println("You drew 5 cards.");
		// winnerThusFar = this;
	}

	public void decideHand() {
		this.showHand();
		this.discardPhase();
		this.redrawPhase();
		return;
	}

	public int evaluateHand() {
		return hd.getMetaValue();
	}

	/**
	 * Displays users current hand
	 */
	public void showHand() {
		System.out.print("Your hand: ");
		for (int i = 0; i < hand; i++) {
			System.out.print(i + 1 + ") [" + hd.getDisplayedCard(i) + "]  ");
		}
		System.out.println("");
	}

	/**
	 * Handles the users discarding phase checks for ace; if yes allows user to
	 * discard 4 cards. otherwise only allows discarding up to 3 cards also
	 * handles error checking
	 */
	private void discardPhase() {
		if (hd.get(0).rank == 14) {
			System.out.println("You drew an Ace! You can choose to keep "
					+ "the Ace and discard your remaining 4 cards.");
			ace = true;
		}

		System.out.print("How many cards do you want to discard? : ");
		numToReplace = stdIn.nextInt();
		/*
		if (numToReplace > 4 || numToReplace < 1)
			throw new IllegalArgumentException("Can only discard up 0 to 4 cards");
		if (numToReplace == 4 && hd.get(0).rank != 14)
			throw new IllegalArgumentException("Can only discard up to three "
					+ "cards without an Ace, and only up to 4 with");
					*/
		if (numToReplace > 0) {
			indices = new int[numToReplace];
			System.out.print("List the position of the cards to discard? : ");
			for (int i = 0; i < numToReplace; i++) {
				// Java have buffer overflow??
				int index = stdIn.nextInt() - 1;
				if (index > (0 - 1) && index < (5 + 1))
					indices[i] = index;
				/*
				else
					throw new IllegalArgumentException(
							"Can only discard cards with and index of 1 through 5");
							*/
				System.out.println("Replacing user card at index " + (index + 1));
			}
		}
	}

	private void redrawPhase() {
		try{
			hd.replace(indices);
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			discardPhase();
		}
	}
}