import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Project: MainPanel.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 17, 2013
 *
 */

public class GamePanel extends JPanel{

	private Game game;
	private ArrayList<Player> playerArray;
	private ArrayList<HandPanel> handPanelArray;
	private ArrayList<JLabel> labelArray;
	private HandPanel player1, player2, activeHandPanel;
	private JLabel drawPile, discardPile, player1Score, player2Score;
	private Card lastCardPlayed;
	private JPanel chooseSuit, cardPanel, scorePanel;
	private JDialog endMenu = new JDialog();
	private JPanel endMenuPanel;
	private JLabel chooseSuitTitle = new JLabel("Choose a Suit:");
	private JButton newGame,resumeGame, menu;
	private JButton hearts = new JButton("Hearts");
	private JButton diamonds = new JButton("Diamonds");
	private JButton clubs = new JButton("Clubs");
	private JButton spades = new JButton("Spades");

	
	
	public GamePanel(ArrayList<Player> playerArray){
		
		
		this.playerArray = playerArray;
		this.labelArray = new ArrayList<JLabel>();
		this.handPanelArray = new ArrayList<HandPanel>();
		this.chooseSuit = new JPanel();
		
		
		hearts.addActionListener(new buttonListener());
		diamonds.addActionListener(new buttonListener());
		clubs.addActionListener(new buttonListener());
		spades.addActionListener(new buttonListener());
		
		this.chooseSuit.add(chooseSuitTitle);
		this.chooseSuit.add(diamonds);
		this.chooseSuit.add(hearts);
		this.chooseSuit.add(clubs);
		this.chooseSuit.add(spades);
		this.chooseSuit.setVisible(false);
		game = new Game(this.playerArray);
		this.player1 = new HandPanel(this.playerArray.get(0));
		this.player2 = new HandPanel(this.playerArray.get(1));
		handPanelArray.add(player1);
		handPanelArray.add(player2);
		this.activeHandPanel = handPanelArray.get((int)(Math.random()*2));
		
		this.player1Score = new JLabel(playerArray.get(0).getName() + " Score: 0");
		this.player2Score = new JLabel(playerArray.get(1).getName() + " Score: 0");
		
		this.labelArray.add(player1Score);
		this.labelArray.add(player2Score);
		setPlayerLabelBackground(handPanelArray.indexOf(activeHandPanel));
		
		this.drawPile = new JLabel(new ImageIcon("./cards/cardback.jpg"));
		this.drawPile.addMouseListener(new mouseListener());
		
		lastCardPlayed = game.getDeck().draw();
		discardPile = lastCardPlayed.getImageLabel();
		
		//fill in components for JDialog box that displays at end of each game
		endMenu.setTitle("Play a New Game?");
		endMenuPanel = new JPanel();
		newGame = new JButton("New Game");
		newGame.addActionListener(new buttonListener());
		resumeGame = new JButton("Resume Game");
		resumeGame.addActionListener(new buttonListener());
		menu = new JButton("Menu");
		
		endMenuPanel.add(newGame);
		endMenuPanel.add(resumeGame);
		endMenuPanel.add(menu);
		endMenu.add(endMenuPanel);
		endMenu.setPreferredSize(new Dimension(200,200));
		endMenu.pack();
		endMenu.setVisible(false);
		
		//adds mouse listener to all the cards in each handpanel
		addMouseListener();
		cardPanel = new JPanel();
		scorePanel = new JPanel();
		cardPanel.add(this.chooseSuit);
		cardPanel.add(this.player1);
		cardPanel.add(drawPile);
		cardPanel.add(discardPile);
		cardPanel.add(this.player2);
		cardPanel.setLayout(new GridLayout(0,1));
		scorePanel.add(this.player1Score);
		scorePanel.add(player2Score);
		scorePanel.setLayout(new GridLayout(0,1));
		add(cardPanel);
		add(scorePanel);
		setLayout(null);
		cardPanel.setBounds(400, 0, 700, 700);
		scorePanel.setBounds(10, 10, 200, 200);
	}

	private void addMouseListener(){
		
		int arrayLength = playerArray.get(0).getHand().size();
		System.out.println(arrayLength);
		for(Player player : playerArray){
			for(int i = 0; i < arrayLength; i++){
				/*System.out.println(i);*/
				player.getHand().get(i).getImageLabel().addMouseListener(new mouseListener());
				
			}
		}
	}

	public void addCard(HandPanel player){
		
		Card drawnCard = game.getDeck().draw();
		drawnCard.getImageLabel().addMouseListener(new mouseListener());
		player.addCard(drawnCard);
		
	}
	
	private void switchPlayer(){
		//use modular arithmetic to get the player in line
		removePlayerLabelBackground(handPanelArray.indexOf(activeHandPanel));
		this.activeHandPanel = handPanelArray.get((handPanelArray.indexOf(activeHandPanel) + 1) % handPanelArray.size()); 
		setPlayerLabelBackground(handPanelArray.indexOf(activeHandPanel));
	}
	
	private void setPlayerLabelBackground(int index){
		System.out.println(labelArray.size());
		this.labelArray.get(index).setBackground(Color.cyan);
		this.labelArray.get(index).setOpaque(true);
	}
	
	private void removePlayerLabelBackground(int index){
		this.labelArray.get(index).setOpaque(false);
	}
	
	private void newGame(){
		resumeGame();
		resetPlayersScores();
		resetPlayersTally();
	}
	
	private void resumeGame(){
		this.game.newGame();
		addMouseListener();
		for(HandPanel panel : handPanelArray){
			panel.newGame();
		}
	}
	
	private void endGame(){
		this.endMenu.setVisible(true);
		for(Player player : playerArray){
			player.getHand().clear();
		}
	}
	
	public void resetPlayersScores(){
		for (Player player : playerArray){
			player.resetScore();
		}
	}
	
	private void resetPlayersTally(){
		this.player1Score.setText(playerArray.get(0).getName() + " Score: 0");
		this.player2Score.setText(playerArray.get(1).getName() + " Score: 0");
		revalidate();
		repaint();
	}
	
	
	private class buttonListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
		
			if(e.getSource() == newGame){
				newGame();
				resetPlayersTally();
				endMenu.setVisible(false);
			}
			else if(e.getSource() == resumeGame){
				resumeGame();
				endMenu.setVisible(false);
			}
			
			if(e.getSource() == hearts){
				lastCardPlayed = new Card('h',8,new ImageIcon("./cards/8_h.jpg"));
			}
			else if(e.getSource() == diamonds){
				lastCardPlayed = new Card('d',8,new ImageIcon("./cards/8_d.jpg"));
			}
			else if(e.getSource() == clubs){
				lastCardPlayed = new Card('c',8,new ImageIcon("./cards/8_c.jpg"));
			}
			else{
				lastCardPlayed = new Card('s',8,new ImageIcon("./cards/8_s.jpg"));
			}
			discardPile.setIcon(lastCardPlayed.getImage());
			chooseSuit.setVisible(false);
			revalidate();
			repaint();
		}
		
	}
	
	
	private class mouseListener extends MouseAdapter{
		
		
		public void mouseClicked(MouseEvent e) {
			
			HandPanel currentHandPanel = handPanelArray.get(handPanelArray.indexOf(activeHandPanel));
			Player currentPlayer = currentHandPanel.getPlayer();
			
			if(e.getSource() == drawPile){
				addCard(currentHandPanel);
				switchPlayer();
				
			}
			
			else{
				Card[] iterableCardArray = new Card[currentPlayer.getHand().size()];
				currentPlayer.getHand().toArray(iterableCardArray);
			//iterates through cardArray to find which card object matches the the JLabel that was clicked
				for(Card card : iterableCardArray){
					if(e.getSource() == card.getImageLabel()){
						
						System.out.println("Fuck");
						//checks if the face up card has the same suit or value as the clicked card.
						if(lastCardPlayed.getSuit() == card.getSuit() || lastCardPlayed.getValue() == card.getValue() || card.getValue() == 8){
							
							if(card.getValue() == 8){
								chooseSuit.setVisible(true);
							}
							else{
								lastCardPlayed = card;
							}
							discardPile.setIcon(card.getImage());;
							currentHandPanel.remove(e.getComponent());
							

							currentPlayer.getHand().remove(card);
							
							if(currentPlayer.getHand().size() < 1){
								currentPlayer.setScore(currentPlayer.getScore() + 1);
								
								int index = handPanelArray.indexOf(currentHandPanel);
								if(index == 0)
									player1Score.setText(playerArray.get(index).getName() + " Score: " + playerArray.get(index).getScore());
								else
									player2Score.setText(playerArray.get(index).getName() + " Score: " + playerArray.get(index).getScore());
							}
							System.out.println(currentPlayer.getName());
							System.out.println(currentPlayer.getHand().size());
							if (currentPlayer.getHand().size() < 1){
								endGame();
							}
							switchPlayer();
						
						}
					}
					
				}
			
			}
		
			revalidate();
			repaint();
			
		}
		
	}


	/**
	 * @return the menu
	 */
	public JButton getMenu() {
		return menu;
	}

	/**
	 * @return the playerArray
	 */
	public ArrayList<Player> getPlayerArray() {
		return playerArray;
	}

	/**
	 * @return the endMenu
	 */
	public JDialog getEndMenu() {
		return endMenu;
	}

	/**
	 * @return the endMenuPanel
	 */
	public JPanel getEndMenuPanel() {
		return endMenuPanel;
	}
}
