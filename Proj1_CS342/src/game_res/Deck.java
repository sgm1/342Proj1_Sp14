package game_res;

public class Deck extends CardPile{
	public final int numDecks;
	private CardPile discardPile;
	
	/**
	 * A single standard
	 * (shuffled) 52 card deck
	 */
	public Deck() {
		this(1);
	}

	/**
	 * Creates a deck composed of specified number of
	 * standard 52 card decks
	 * 
	 * @param decks Number of standard decks to combine, must be greater then 0
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
	public void discard(Card c){
		if (!hasCard(c))
			throw new IllegalArgumentException();
		removeCard(c);
		discardPile.addCard(c);
		
	}
	
	public Card getTop(){
		Card temp = super.getTop();
		if (temp == null){
			reshuffle();
			return super.getTop();
		}
		return temp;
	}
	
	/**
	 * Adds back the discard pile to the deck
	 */
	public void reshuffle(){
		addToPile(discardPile);//should empty (modify) the discardPile object
	}

}
