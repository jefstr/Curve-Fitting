///
/// Contents: Graph class
/// Author:   Jeff Parvin
/// Date:     18 March 2012
///

import java.awt.* ;
import java.util.ArrayList;

import javax.swing.* ;

public class Graph extends JPanel {

	public int PEN_WIDTH = 10;
	public Color PEN_COLOR = Color.black ;
	
	private ArrayList<Point> points = new ArrayList<Point>();

	private double xmin, xmax, ymin, ymax, xrange, yrange , xscale, yscale;
	private int width, height;
	private int oval_x, oval_y;
	
	public Graph(double x_min, double x_max, double y_min, double y_max, int w, int h) {
		
		xmin = x_min;
		xmax = x_max;
		ymin = y_min;
		ymax = y_max;
		width = w ;
		height = h ;
		
		xrange = x_max-x_min;
		yrange = y_max-y_min;
		
		xscale = width/xrange;
		yscale = height/yrange;
	
		setPreferredSize(new Dimension(w,h)) ;
	}

	public void point(double x, double y) {
		// adds new point to point array
		Point point = new Point(xtrans(x),ytrans(y), PEN_COLOR, PEN_WIDTH);
		points.add(point);
		repaint();
	}

	public void paintComponent(Graphics g) {
		
		// draw background
		super.paintComponent(g) ;
		g.setColor(Color.white) ;
		g.fillRect(0,0,width,height) ;

		// draw axes
		g.setColor(Color.black) ;
		g.drawLine( xtrans(xmin), ytrans(0), xtrans(xmax), ytrans(0)) ;
		g.drawLine( xtrans(0), ytrans(ymin), xtrans(0), ytrans(ymax)) ;

		// draw all points
		for(int i = 0; i<points.size(); i++){
			g.setColor(points.get(i).getColor()) ;
			int x = points.get(i).getx();
			int y = points.get(i).gety();
			int pointSize = points.get(i).getSize();
			g.fillOval(x, y, pointSize, pointSize);
		}

	}
	
	private int xtrans(double x){
		return (int)(xscale(Math.abs(xmin)) + xscale(x));
	}
	
	private int ytrans(double y){
		return (int)(yscale(ymax) - yscale(y));
	}

	private double yscale(double y){
		return y*yscale;
	}
	
	private double xscale(double x){
		return x*xscale;
	}

	private class Point{
		private int x;
		private int y;
		private Color pointColor;
		private int pointSize;
		
		private Point(int x_in, int y_in, Color c, int s){
			x = x_in;
			y = y_in;
			pointColor = c;
			pointSize = s;
		}
		
		private int getx(){
			return x;
		}
		
		private int gety(){
			return y;
		}
		
		private Color getColor(){
			return pointColor;
		}
		
		private int getSize(){
			return pointSize;
		}
	}
}

/// End-of-File
