///
/// Contents: Polynomial class
/// Author:   Jeff Parvin
/// Date:     18 March 2012
///

public class Polynomial {
	
	private double[] terms;
	
	public Polynomial(double[] t){
		terms = t;
	}
	
	public double[] toArray(){
		return terms;
	}
		
	public double evaluate(double x){
		
		double result = terms[terms.length-1];
		
		for(int i=terms.length-2; i>=0; i--){
			result = (result*x)+terms[i];
		}
		
		return result;
	}

}
