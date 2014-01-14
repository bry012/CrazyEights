import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

/**
 * Project: Deck.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 15, 2013
 *
 */

public class Deck {

	private ArrayList<Card> deck;
	private ArrayList<Card> played;
	private char[] suits = {'s','h','d','c'};
	
	private Card lastPlayed;
	
	public Deck(int numDecks){
		
		deck = new ArrayList<Card>();
		createFullDeck(numDecks);
		
	}
	
	//method that combines multiple(or single) decks using the private method createDeck
	public void createFullDeck(int numDecks){
		
		this.deck.clear();
		for(int i = 0; i < numDecks; i++){
			this.deck.addAll(createDeck());
		}
		Collections.shuffle(this.deck);
		
	}
	
	//used to make single deck
	private ArrayList<Card> createDeck(){
		
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i = 1; i <= 13; i++){
			for(char suit : suits){
				
				//create image path for ImageIcon
				String path = "./cards/" + i + "_" + suit + ".jpg";
				deck.add(new Card(suit,i,new ImageIcon(path)));
			}
		}
		return deck;
		
		
	}
	
	public Card draw(){
		
		Card drawnCard = deck.get(0);
		deck.remove(0);
		return drawnCard;
	}
	
	public String toString(){
		
		String output = "";
		for(Card card : deck){
			output+="\nSuit: " + card.suit + "\nValue: " + card.value + "\n" + card.image + "\n";
		}
		
		return output;
	}


	/**
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}


	/**
	 * @param deck the deck to set
	 */
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}


	/**
	 * @return the played
	 */
	public ArrayList<Card> getPlayed() {
		return played;
	}


	/**
	 * @param played the played to set
	 */
	public void setPlayed(ArrayList<Card> played) {
		this.played = played;
	}



	/**
	 * @return the lastPlayed
	 */
	public Card getLastPlayed() {
		return lastPlayed;
	}


	/**
	 * @param lastPlayed the lastPlayed to set
	 */
	public void setLastPlayed(Card lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
}
