package game_res;

import java.util.ArrayList;

public class CardPile {
	private ArrayList<Card> pile;
	private int isSorted = 0;// 0 means false, 1 means rank major, 2 means suit major
	
	public CardPile(){//empty pile
		pile = new ArrayList<Card>();
	}
	
	public CardPile(int i){
		if (i < 0 || i > 2)
			throw new IllegalArgumentException();
		pile = new ArrayList<Card>();
		isSorted = i;
	}
	
	public CardPile(CardPile temp){//copy constructor
		pile = new ArrayList<Card>(temp.pile);
		isSorted = temp.isSorted;
	}
	
	private CardPile(ArrayList<Card> daPile, int sorted){
		pile = new ArrayList<Card>(daPile);
		isSorted = sorted;
	}
	
	
	public CardPile getRankMajorOrdered(){//doesn't change actually pile
		ArrayList<Card> temp =  new ArrayList<Card>(pile);
		if (isSorted == 1)
			return new CardPile(this);
		//order rank here, then suit
		return new CardPile(temp, 1);
	}
	
	public CardPile getSuitMajorOrdered(){//doesn't change actually pile
		ArrayList<Card> temp =  new ArrayList<Card>(pile);
		if (isSorted == 2)
			return new CardPile(this);
		//order suit here, then rank
		return new CardPile(temp, 2);
	}
	
	public void shuffle(){
		//shuffle pile
	}
	
	public void add(Card temp){
		int i = (int) (Math.random() * pile.size());//to a randomePos
	}
}
