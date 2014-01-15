import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
		chosenPlayersArray = new ArrayList<Player>();
		playersList = new JComboBox<Player>();
		for(Player player : playerArray){
			if (player.getName().equals("Computer")){
				chosenPlayersArray.add(player);
			}
			
			else{
				playersList.addItem(player);
			}
		}
		
		chosenPlayersArray.add(new Player("Computer 2"));
		panel = new GamePanel(chosenPlayersArray);
		title = new JLabel("Herro");
		input = new JTextField(10);
		submit = new JButton("Submit");
		submit.addActionListener(new buttonListener());
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setVisible(true);
		
		
	}
	

		public void createGame() {
			this.chosenPlayersArray.get(0).getHand().clear();
			chosenPlayersArray.remove(1);
			if(input.getText().equals("")){
				chosenPlayersArray.add((Player) playersList.getSelectedItem());
			}
			
			else{
				Player newPlayer = new Player(input.getText());
				chosenPlayersArray.add(newPlayer);
				playerArray.add(newPlayer);
				playersList.addItem(newPlayer);
			}
			remove(panel);	
			panel = new GamePanel(chosenPlayersArray);
			panel.getMenu().addActionListener(new buttonListener());
			panel.getSave().addActionListener(new buttonListener());
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
			if(e.getSource() == panel.getMenu()){
				setVisible(false);
				pane.setVisible(true);
				panel.getEndMenu().setVisible(false);
				panel.resetPlayersScores();
			}
			if(e.getSource() == panel.getSave()){
				PrintWriter writer;
				try {
					writer = new PrintWriter("./src/players/players.txt", "UTF-8");
					for(Player player : playerArray){
						writer.println(player.getName() + "," + player.getId() + "," + player.getWins() + "," + player.getLosses());

					}
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}
	}	

}
