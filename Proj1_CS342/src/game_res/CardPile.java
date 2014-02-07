package game_res;

import java.util.ArrayList;
import java.util.Random;

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
			shuffle();
		} else if (sort == 1)
			sortBySuit(false);// sort rank major
		else
			sortBySuit(true);// sort suit
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

	public CardPile(CardPile temp) {// copy constructor
		pile = new ArrayList<Card>(temp.pile);
		isSorted = temp.isSorted;
	}
	
	@Override
	public String toString(){
		int len = pile.size();
		String temp = "";
		for(int i = 0; i < len; ++i){
			temp += pile.get(i) + ", ";
		}
		return temp;
	}
	public void addToPile(CardPile other){
		Card temp = other.getTop();
		while (temp != null){
			addCard(temp);
			temp = other.getTop();
		}
		shuffle();
	}

	/**
	 * Sorts by suit if true, rank if false.
	 * 
	 * @param bySuit
	 */
	protected void sortBySuit(boolean bySuit) {
		int size = pile.size();
		int curI;
		for (int k = 1; k < size; k++) {
			for (curI = k - 1; curI >= 0 && pile.get(k).compareTo(pile.get(curI), bySuit) > 0; curI--);
			pile.add(curI + 1, pile.remove(k));// insert
		}
		if (bySuit)
			isSorted = 2;
		else isSorted = 1;
	}
	
	/**
	 * Specifies if the pile already contains at least
	 * an instance of a Card.
	 * 
	 * @param c Card to check
	 * @return card
	 */
	protected boolean hasCard(Card c){
		return pile.contains(c);
	}

	/**
	 * Uses Math.random() to generate random shuffle.
	 */
	protected void shuffle() {
		// shuffle pile
		final Random rd = new Random();
		int i = 0;
		isSorted = 0;
		for (int k = 0; i < pile.size(); i++) {
			i = rd.nextInt(52);
			if (i != k) {
				pile.add(i, pile.remove(k));
			}
		}
	}

	protected void addCard(Card temp) {
		if (isSorted == 0) {
			//int i = (int) (Math.random() * pile.size());// to a random pos
			pile.add(0, temp);
		}else if (isSorted == 1){
			pile.add(temp);
			sortBySuit(false);//lazy way
		}else{//if isSorted == 2
			pile.add(temp);
			sortBySuit(true);//lazy way
		}
	}
	
	/**
	 * Removes all instances of specified Card
	 * 
	 * @param c Card to Remove
	 * @return Success of removal
	 */
	protected boolean removeAllCard(Card c){
		if (pile.contains(c)){
			pile.remove(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an instance of specified Card
	 * 
	 * @param c Card to remove
	 * @return Success of removal
	 */
	protected boolean removeCard(Card c){
		if (pile.contains(c)){
			pile.remove(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Pops off and returns top of pile
	 * 
	 * @return Top card
	 */
	protected Card getTop(){
		if (pile.size() == 0)
			return null;
		else return pile.remove(0);
	}
	
	protected Card get(int i){
		if (i < 0 || i > pile.size())
			throw new IllegalArgumentException();
		return pile.get(i);
	}
	
	protected Card remove(int i){
		if (i < 0 || i > pile.size())
			throw new IllegalArgumentException();
		return pile.remove(i);
	}
}
