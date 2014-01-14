import java.util.ArrayList;
import java.util.Collections;

/**
 * Project: Game.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 15, 2013
 *
 */

public class Game {
	
	private int winnterId;
	private int loserId;
	private ArrayList<Player> playerArray;
	private int playerArraySize;
	private Deck deck;
	final int NUMDECKS = 1;
	
	public Game(ArrayList<Player> playerArray){
		this.playerArray = playerArray;
		playerArraySize = this.playerArray.size();
		this.deck = new Deck(NUMDECKS);
		dealCards();
		
	}
	
	public void newGame(){
		
		deck.createFullDeck(NUMDECKS);
		for(int i = 0; i < playerArraySize; i++){
			playerArray.get(i).getHand().clear();
		}
		dealCards();
	}
	
	public void dealCards(){
		
		
		//for loop used to deal cards to each player in playerArray
		for(int i = 0; i < 7 * playerArraySize; i ++){
			//modular arithmetic used to get index of player that is to receive a card
			this.playerArray.get(i % playerArraySize).getHand().add(this.deck.draw());
		}
		
	}
	
	
	public void calcWinner(){
		
		Collections.sort(playerArray, Collections.reverseOrder());
		this.winnterId = playerArray.get(0).getId();
		this.loserId = playerArray.get(1).getId();
	}

	/**
	 * @return the winnterId
	 */
	public int getWinnterId() {
		return winnterId;
	}

	/**
	 * @param winnterId the winnterId to set
	 */
	public void setWinnterId(int winnterId) {
		this.winnterId = winnterId;
	}

	/**
	 * @return the loserId
	 */
	public int getLoserId() {
		return loserId;
	}

	/**
	 * @param loserId the loserId to set
	 */
	public void setLoserId(int loserId) {
		this.loserId = loserId;
	}

	/**
	 * @return the playerArray
	 */
	public ArrayList<Player> getPlayerArray() {
		return playerArray;
	}

	/**
	 * @param playerArray the playerArray to set
	 */
	public void setPlayerArray(ArrayList<Player> playerArray) {
		this.playerArray = playerArray;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
}
