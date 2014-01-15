import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Player> playerArray = new ArrayList<Player>();
		Scanner playerScanner = new Scanner(new File("./src/players/players.txt"));
		while(playerScanner.hasNext()){
			String statsLine = playerScanner.nextLine();
			if(statsLine.length() > 0){
				Scanner statsScan = new Scanner(statsLine).useDelimiter(",");
				Player player = new Player(statsScan.next());
				playerArray.add(player);
				player.setId(statsScan.nextInt());
				player.setWins(statsScan.nextInt());
				player.setLosses(statsScan.nextInt());
				System.out.println(player.toString());
				System.out.println(player.getWins());
				System.out.println(player.getLosses());
			}
			
		}
		 
		NewPlayerFrame frame = new NewPlayerFrame(playerArray);
		
		

	}

}
