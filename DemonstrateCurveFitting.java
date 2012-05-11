///
/// Contents: Demonstrate curve-fitting programs.
/// Author:   Jeff Parvin
/// Date:     18 March 2012
///

import java.awt.* ;
import javax.swing.* ;
import java.util.Arrays;
import java.util.Date ;

public class DemonstrateCurveFitting {

	public static int SLEEP = 1 ;

	public static void main(String[] args) {

		double[] values = { +1, -4, +1, -2, +1, -1, +4, +3, -3, +1 } ;		// y values
		
		double[] v = Arrays.copyOf(values,values.length);
		Arrays.sort(v);
		
		double deltaX = 0.01 ;												// precision	

		JFrame frame = new JFrame("Fit Curves to Points") ;
		Graph graph = new Graph(-1.0,v.length,v[0]-1,v[v.length-1]+1,1000,500) ;	// parameters are x_min, x_max, y_min, y_max, ??, ?? - for drawing axes or size of frame?
		frame.getContentPane().add(graph) ;
		frame.pack() ;
		frame.setVisible(true) ;
		sleep(500) ;

		// Plot points.
		graph.PEN_WIDTH = 10 ;				//some kind of jframe size var for draw oval...
		graph.PEN_COLOR = Color.black ;		//some kind of jframe color var
		for (int x=0 ; x<values.length ; x++) { graph.point(x,values[x]) ; }		// essentially a draw oval call to plot each point

		// Plot Lagrange interpolation polynomial.
		graph.PEN_WIDTH = 3 ;
		graph.PEN_COLOR = Color.red ;
		Polynomial lagrange = Lagrange.interpolate(values) ;
		for (double x=0 ; x<values.length-1 ; x+=deltaX) {
			graph.point(x,lagrange.evaluate(x)) ;
			sleep(SLEEP) ;
		}

		// Plot cubic spline.
		graph.PEN_WIDTH = 3 ;
		graph.PEN_COLOR = Color.blue ;
		Spline spline = new Spline(values) ;
		for (double x=0 ; x<values.length-1 ; x+=deltaX) {
			graph.point(x,spline.evaluate(x)) ;
			sleep(SLEEP) ;
		}

	}

	public static void sleep(long milliseconds) {
		Thread thread = new Thread() ;
		try { thread.sleep(milliseconds) ; }
		catch (Exception e) {}
	}

}

/// End-of-File

