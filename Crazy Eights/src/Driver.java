import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Project: Driver.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 15, 2013
 *
 */

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		JButton button = new JButton("Submit");
		Player player1 = new Player("Bryan");
		Player player2 = new Player("Kaci");
		ArrayList<Player> playerArray = new ArrayList<Player>(); 
		playerArray.add(player1);
		playerArray.add(player2);
		NewPlayerFrame frame = new NewPlayerFrame(playerArray);
		
		

	}

}
