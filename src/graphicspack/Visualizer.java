package graphicspack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class Visualizer extends JFrame {
	
	/* Visualizer prompts the user and creates the frame for the tree panel to fill
	 * It transfers over the user number to the screen class for calculations on building the tree based on input.
	 */
	static int userNum;
	
	
	public Visualizer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Screen(userNum)); // adds in Screen.java 
		setUndecorated(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize()); //set to full width of screen might change later
		setVisible(true);
		
	}
	
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a number between 1 and 5: ");
		System.out.println("Your number will affect run-time. Bigger number = bigger tree.");
		userNum = scan.nextInt();
		while(userNum < 1 || userNum > 5 ) {
			System.out.println("Try again. Enter a number between 1 and 10: ");
			userNum = scan.nextInt();
		}
		
		JFrame frame = new Visualizer();
		
		scan.close();
	}

} 
