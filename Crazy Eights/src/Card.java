import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Project: Card.java
 *
 * Coder: Bryan Schmidt
 *
 * Purpose:
 *
 * Date:Dec 15, 2013
 *
 */

public class Card {

	char suit;
	int value;
	ImageIcon image;
	JLabel imageLabel;
	
	public Card(char suit, int value, ImageIcon image){
		this.suit = suit;
		this.value = value;
		this.image = image;
		this.imageLabel = new JLabel(this.image);
	}

	/**
	 * @return the suit
	 */
	public char getSuit() {
		return suit;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * @return the imageLabel
	 */
	public JLabel getImageLabel() {
		return imageLabel;
	}
}
