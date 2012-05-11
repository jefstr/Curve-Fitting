///
/// Contents: Spline class
/// Author:   Jeff Parvin
/// Date:     18 March 2012
///

public class Spline {

	private double[] values;

	public Spline(double[] v){
		values = v;
	}

	public double evaluate(double x){

		int x0 = (int)x;
		int x1 = x0+1;
		double y0 = values[x0];
		double y1 = values[x1];
		double s0;
		double s1;

		if(x0==0){
			s0 = midSlope(-y1,y0,y1);
			if(x1==values.length-1) s1 = midSlope(y0,y1,-y0);
			else s1 = midSlope(y0,y1,values[x1+1]);
		}

		else{
			s0 = midSlope(values[x0-1],y0,y1);
			if(x1==values.length-1) s1 = midSlope(y0,y1,-y0);
			else s1 = midSlope(y0,y1,values[x1+1]);
		}
		
		return cubic(y0, y1, s0, s1, x-x0);
		 
	}

	private double cubic(double y0, double y1, double s0, double s1, double x){

		double a = y0;
		double b = s0;
		double c = ( (3*(y1-y0)) - 2*s0 ) - s1;
		double d = (2*(y0-y1)) + s0 + s1;
		
		double[] terms = { a, b, c, d };
		
		Polynomial spline = new Polynomial(terms);
		
		double result =  spline.evaluate(x);
		
		return result;
	}

	private double midSlope(double y1, double y2, double y3){

		double A = (y3 + y1 - 2*y2) / 2;
		double B = y2 - y1 - A;
		double slope = 2*A + B;
	
		return slope;
	}
}
