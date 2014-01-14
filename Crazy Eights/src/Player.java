import java.util.ArrayList;



/**
 * Project: Player.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 15, 2013
 *
 */

public class Player implements Comparable<Object>{
	
	private String name;
	private int id;
	private int score;
    private ArrayList<Card> hand = new ArrayList<Card>();
    
    public Player(String name){
    	
    	this.name = name;
    }
    
    public Card playCard(int cardIndex){
    	
    	Card playedCard = hand.get(cardIndex);
    	hand.remove(cardIndex);
    	return playedCard;
    	
    }
    
    public void drawCard(Card card){
    	
    	this.hand.add(card);
    }
    
    public void resetScore(){
    	this.score = 0;
    }
    
	public int compareTo(Object o){
		
		//sets price equal to the price of the bike object passed as a parameter
		int score = ((Player)o).getScore();
		if(this.score > score){
			return 1;
		}
		//tolerance used to check whether prices are equal up to a certain decimal place
		else if(this.score == score){
			return 0;
		}
		else{
			return -1;
		}

	}
    
    public String toString(){
    	
    	return this.name;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
    
}
