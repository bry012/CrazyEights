import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Project: NewPlayerFrame.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 22, 2013
 *
 */

public class NewPlayerFrame extends JFrame{

	Player player;
	ArrayList<Player> playerArray, chosenPlayersArray;
	JPanel mainPanel;
	JLabel title;
	JButton submit;
	JComboBox<Player> playersList;
	JTextField input;
	JDialog pane;
	GamePanel panel;
	
	public NewPlayerFrame(ArrayList<Player> playerArray){
		
		pane = new JDialog();
		pane.setTitle("Hello");
		pane.setPreferredSize(new Dimension(200,200));
		this.playerArray = playerArray;
		playersList = new JComboBox<Player>();
		for(Player player : playerArray){
			playersList.addItem(player);
		}
		chosenPlayersArray = new ArrayList<Player>();
		chosenPlayersArray.add(new Player("Computer"));
		chosenPlayersArray.add(new Player("Computer 2"));
		panel = new GamePanel(chosenPlayersArray);
		title = new JLabel("Herro");
		input = new JTextField(10);
		submit = new JButton("Submit");
		submit.addActionListener(new buttonListener());
		panel.getMenu().addActionListener(new buttonListener());
		JPanel panels = new JPanel();
		panels.add(title);
		panels.add(playersList);
		panels.add(input);
		panels.add(submit);
		pane.add(panels);
		pane.setPreferredSize(new Dimension(200,200));
		pane.setBounds(500, 200, 200, 300);
		pane.pack();
		pane.setVisible(true);
		setPreferredSize(new Dimension(500,500));
		pack();
		setVisible(false);
		pane.setVisible(true);
		
		
	}
	

		public void createGame() {
			this.chosenPlayersArray.get(0).getHand().clear();
			chosenPlayersArray.remove(1);
			System.out.println(chosenPlayersArray.get(0).getName());
			if(input.getText().equals("")){
				chosenPlayersArray.add((Player) playersList.getSelectedItem());
			}
			
			else{
				Player newPlayer = new Player(input.getText());
				chosenPlayersArray.add(newPlayer);
				playersList.addItem(newPlayer);
			}
			remove(panel);	
			panel = new GamePanel(chosenPlayersArray);
			panel.getMenu().addActionListener(new buttonListener());
			add(panel);
			revalidate();
			repaint();
			
			setVisible(true);
			pane.setVisible(false);
			
		}
		
		


	/**
	 * @return the chosenPlayersArray
	 */
	public ArrayList<Player> getChosenPlayersArray() {
		return chosenPlayersArray;
	}
	
	private class buttonListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == submit){
				createGame();
			}
			else if(e.getSource() == panel.getMenu()){
				System.out.println("Menu Pressed");
				setVisible(false);
				pane.setVisible(true);
				panel.getEndMenu().setVisible(false);
				panel.resetPlayersScores();
			}
			
		}
	}	

}
