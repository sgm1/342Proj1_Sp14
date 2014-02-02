package game_res;

import java.util.ArrayList;

public class CardPile {
	private ArrayList<Card> pile;
	private int isSorted;// 0 means false, 1 means rank major, 2 means suit major
	
	public CardPile(){//empty pile
		pile = new ArrayList<Card>();
		isSorted = 0;
	}

	public CardPile(int numCards, int sort){
		if (numCards < 0)
			throw new IllegalArgumentException();
		pile = new ArrayList<Card>();
		isSorted = numCards;
		if (sort == 1)
			;//sort rank major
		else
			;//sort suit
	}
	
	public CardPile(int numCards){
		this(numCards, 0);
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
		int i = 0;
		Card temp;
		for (int k = 0; i < pile.size(); i++){
			i = (int) (Math.random() * pile.size());
			if (i != k){
				temp = pile.get(k);
				pile.set(k, pile.get(i));
				pile.set(i, temp);
			}
		}
	}
	
	public void add(Card temp){
		int i = (int) (Math.random() * pile.size());//to a randomePos
		pile.add(i, temp);
	}
}
