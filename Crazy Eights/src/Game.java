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
	
	private String winnerName;
	private String loserName;
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
		this.winnerName = playerArray.get(0).getName();
		this.loserName = playerArray.get(1).getName();
	}

	/**
	 * @return the winnerName
	 */
	public String getWinnterName() {
		return winnerName;
	}

	/**
	 * @param winnerName the winnerName to set
	 */
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	/**
	 * @return the loserName
	 */
	public String getLoserId() {
		return loserName;
	}

	/**
	 * @param loserName the loserName to set
	 */
	public void setLoserId(String loserName) {
		this.loserName = loserName;
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
