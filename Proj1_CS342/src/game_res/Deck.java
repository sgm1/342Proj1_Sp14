package game_res;

public class Deck extends CardPile{
	public final int numDecks;
	private CardPile discardPile;
	
	/**
	 * Default of a single standard 52 card deck
	 */
	public Deck() {
		this(1);
	}

	/**
	 * Creates a deck composed of specified number of
	 * standard decks
	 * 
	 * @param decks Number of standard decks to combine
	 */
	public Deck(int decks) {
		super(52);
		if (decks < 1)
			throw new IllegalArgumentException();
		numDecks = decks;
		discardPile = new CardPile();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Adds the card to the discard pile.
	 * 
	 * @param c The card being discarded
	 */
	void discard(Card c){
		if (!hasCard(c))
			throw new IllegalArgumentException();
		removeCard(c);
		discardPile.addCard(c);
	}
	
	/**
	 * Adds back the discard to the deck
	 */
	void reshuffle(){
		addToPile(discardPile);//should empty the discard
	}

}
