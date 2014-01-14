import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Project: HandPanel.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 17, 2013
 *
 */

public class HandPanel extends JPanel{

	private Player player;
	private Card lastPlayedCard;
	
	
	public HandPanel(Player player){
		this.player = player;
		newGame();
		
	}
	
	public void newGame(){
		
		removeAll();
		for(Card card : this.player.getHand()){
			add(card.getImageLabel());
			
		}
		
		revalidate();
		repaint();
	}

	public void addCard(Card newCard){
		
		add(newCard.getImageLabel());
		this.player.getHand().add(newCard);
		revalidate();
		repaint();
		
		
	}

	/**
	 * @return the lastPlayedCard
	 */
	public Card getLastPlayedCard() {
		return lastPlayedCard;
	}

	/**
	 * @param lastPlayedCard the lastPlayedCard to set
	 */
	public void setLastPlayedCard(Card lastPlayedCard) {
		this.lastPlayedCard = lastPlayedCard;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}


}