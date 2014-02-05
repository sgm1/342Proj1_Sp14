package game_res;

import java.util.ArrayList;

public class PokerHand extends CardPile {
	private static Deck daDeck = null;//shared among all PokerHands
	
	//private ArrayList<Card> handForDisplay;
	
	public PokerHand(Deck d) {
		if (daDeck == null && d != null)
			daDeck = d;
		else if (daDeck != null)
			throw new IllegalArgumentException();
		//handForDisplay = new ArrayList<Card>();
		drawFiveCards();
		sortBySuit(false);
	}
	
	private void drawFiveCards(){
		for (int i = 0; i < 5; ++i){
			this.addCard(daDeck.getTop());
		}
	}
	
	public Card replace(int i){
		Card temp = super.remove(i);
		addCard(daDeck.getTop());
		return temp;
	}
	
	public Card get(int i){
		return super.get(i);
	}
	
	private int isFlush(){
		if (get(0) == get(1) && get(3) == get(4) && get(2) == get (1))
			return get(4).rank * 10 + get(4).suit;//easily compare rank then suit
		return 0;
	}
	
	private int isStraight(){
		if (get(0).rank + 1 == get(1).rank &&
				get(3).rank + 1 == get(4).rank &&
				get(1).rank + 1 == get (2).rank)
			return get(4).rank * 10 + get(4).suit;//easily compare rank then suit
		return 0;
	}
	
	private int isFullHouse(){
		return 0;
	}
}
