package game_res;

import java.util.ArrayList;

public class CardPile {
	private ArrayList<Card> pile;
	private int isSorted;// 0 means false, 1 means rank major, 2 means suit
							// major

	/**
	 * Empty pile
	 */
	public CardPile() {// empty pile
		pile = new ArrayList<Card>();
		isSorted = 0;
	}

	/**
	 * Creates a deck of cards.
	 * 
	 * Type of cards generated based on:
	 * numCards % 52 -> 0-12 = Diamonds, 13-25 = Clubs, 26-38 = Hearts, 39-51 =
	 * Spades
	 * 
	 * Ace = 0, 2 = 1..., T = 9, J = 10
	 * 
	 * @param numCards
	 *            Number of cards
	 * @param sort
	 *            Type of sort, 0 = None, 1 = by rank, 2 = by suit
	 */
	public CardPile(int numCards, int sort) {
		if (numCards < 0 || sort < 0 || sort > 2 || numCards % 52 != 0)
			throw new IllegalArgumentException();
		pile = new ArrayList<Card>();
		//isSorted = sort;
		while (numCards > 0) {
			--numCards;
			pile.add(new Card(numCards));
		}
		if (sort == 0) {
			//shuffle();
		} else if (sort == 1)
			sortMajor(false);// sort rank major
		else
			sortMajor(true);// sort suit
	}

	/**
	 * Creates a pile of a specific number of cards
	 * 
	 * Type of cards generated based on:
	 * numCards % 52 -> 0-12 = Diamonds, 13-25 = Clubs, 26-38 = Hearts, 39-51 = Spades
	 * 
	 * Ace = 0, 2 = 1..., T = 9, J = 10
	 * 
	 * @param numCards
	 */
	public CardPile(int numCards) {
		this(numCards, 0);
	}

	/**
	 * Sorts by suit if true, rank if false.
	 * 
	 * @param bySuit
	 */
	public void sortMajor(boolean bySuit) {
		int size = pile.size();
		int curI;
		for (int k = 1; k < size; k++) {
			for (curI = k - 1; curI >= 0 && pile.get(k).compareTo(pile.get(curI), bySuit) > 0; curI--);
			pile.add(curI + 1, pile.remove(k));// insert
		}
	}

	public CardPile(CardPile temp) {// copy constructor
		pile = new ArrayList<Card>(temp.pile);
		isSorted = temp.isSorted;
	}

	/**
	 * Uses Math.random() to generate random shuffle.
	 */
	public void shuffle() {
		// shuffle pile
		int i = 0;
		isSorted = 0;
		Card temp;
		for (int k = 0; i < pile.size(); i++) {
			i = (int) (Math.random() * pile.size());
			if (i != k) {
				temp = pile.get(k);
				pile.set(k, pile.get(i));
				pile.set(i, temp);
			}
		}
	}
	
	@Override
	public String toString(){
		int len = pile.size();
		String temp = "";
		for(int i = 0; i < len; ++i){
			temp += pile.get(i) + "\n";
		}
		return temp;
	}

	public void addCard(Card temp) {
		if (isSorted == 0) {
			int i = (int) (Math.random() * pile.size());// to a random pos
			pile.add(i, temp);
		}
	}
}
