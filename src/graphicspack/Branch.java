package graphicspack;

import java.awt.Color;

public class Branch {
	
	/*
	 * All branches on the tree have two pairs of points, a color and a stroke, or weight.
	 * p1 represents the point connecting it to the previous branch
	 * p2 represents the point connecting it to its children
	 * color is determined while building the tree, all small branches are green, big ones are brown
	 * stroke is the weight of the line being drawn, or its thickness.
	 */
	float sf; //stroke-float
	Pair p1, p2;
	Color color;
	
	Branch(Pair p1, Pair p2, Color c, float stroke) {
		this.p1 = p1;
		this.p2 = p2;
		color = c;
		sf = stroke;
	}
}
