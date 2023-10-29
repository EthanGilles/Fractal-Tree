package graphicspack;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


class Screen extends JPanel implements ActionListener { 
	
	/*
	 * Screen holds all of the info for the tree, including all of its branches in an ArrayList (tree)
	 * it also holds constants like the leaf and branch colors.
	 * 
	 * 
	 * Uses polar coordinates (length, angle) for (n, theta) to recursively build branches.
	 * Calculates all branches when initialized then will slowly print more and more branches
	 * Uses user input to determine initial weight of branches and at a small weight will stop recursion.
	 * 
	 */
	
	static Color green = new Color(52, 133, 60); //leaf color
	static Color brown = new Color(94, 61, 19); //branch color	
	BasicStroke stroke; //branch width
	
	static int trunkLength; // length of initial trunk
	
	static float userNum; // initial stroke
	int numBranches = 0;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // gets screen size
	Timer timer;
	
	
	static int angleDiff = 30; // angle of branches
	int interval = 3; // interval between drawing branches in ms
	
	static ArrayList<Branch> tree = new ArrayList<Branch>();
	
	
	Screen(int user) {
		setBackground(Color.LIGHT_GRAY);
		userNum = user + 5;
		setTrunkLength(user * 50);
		
		Pair topTrunk = new Pair(screenSize.width / 2, (screenSize.height - 10) - trunkLength) ; //top of trunk
		Pair baseTrunk = new Pair(screenSize.width / 2, screenSize.height - 10); // base of trunk
		Branch trunk = new Branch(topTrunk, baseTrunk, brown, userNum); //branch has two points and a color.
		tree.add(trunk);
		
		//Already used userNum in trunk so will iterate down for the first two branches.
		fillTree(topTrunk, 0, trunkLength, (float)(userNum-0.5));
		
		//Timer object that calls action performed every 'interval' of time.
		timer = new Timer(interval, this);
		timer.start();
	}
	
	/*
	 * every time repaint is called, this method happens
	 * 
	 */
    public void paint(Graphics g) 
    { 
    	super.paint(g);
    	Graphics2D g2D = (Graphics2D) g; //2d graphics so we can set stroke width
    	
    	for(int i = 0; i < numBranches && i < tree.size(); i++) {
    		Branch b = tree.get(i);
    		
    		stroke = new BasicStroke(b.sf);
    		g2D.setColor(b.color);
    		g2D.setStroke(stroke);
    		
    		g2D.drawLine(b.p1.x, b.p1.y, b.p2.x, b.p2.y);
    	}

    } 
    

	/*
	 * Will increase the number of branches printed then repaint the screen.
	 */
	public void actionPerformed(ActionEvent e) {
		numBranches+= 2;
		repaint();
	}
    
	
	//fills the tree arrayList with branch objects.
	public static void fillTree(Pair p1, int angle, int length, float sf) {
		//Determine color of branch
		Color c;
    	if(sf <= 0.5) 
    		return;
    	if(length <= 5) {
    		length = 5;
    		c = green;
    	}
    	else
    		c = brown;
    	
    	//determines right branch coordinates
    	int x1 = (int) (Math.sin(Math.toRadians(angle+angleDiff)) * length);
    	int y1 = (int) (Math.cos(Math.toRadians(angle+angleDiff)) * length);
    	
    	//determines left branch coordinates
    	int x2 = (int) (Math.sin(Math.toRadians(angle-angleDiff)) * length);
    	int y2 = (int) (Math.cos(Math.toRadians(angle-angleDiff)) * length);
    	
    	//Assigns coordinates to Pair objects.
    	Pair right = new Pair(p1.x+x1, p1.y-y1);
    	Pair left = new Pair(p1.x+x2, p1.y-y2);
    	
    	//2 new branches, with the previous and new coordinates.
    	tree.add(new Branch(p1, right, c, sf));
    	tree.add(new Branch(p1, left, c, sf));
    	
    	//recursive call on the new branches end points, with smaller values.
    	fillTree(right, angle+angleDiff, length-(trunkLength/10), (float)(sf-0.6));
    	fillTree(left, angle-angleDiff, length-(trunkLength/10), (float)(sf-0.6));
	}
	
	//need a static helper method to set static value trunk length.
	static void setTrunkLength(int n) {
		trunkLength = n;
	}
    
}
