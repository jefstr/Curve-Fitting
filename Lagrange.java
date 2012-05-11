///
/// Contents: Lagrange class
/// Author:   Jeff Parvin
/// Date:     18 March 2012
///

import java.util.Arrays;

public class Lagrange {

	public static Polynomial interpolate(double[] values){

		double[] Px = new double[values.length];		// Lagrange polynomial (LP)
		double[] binomial = new double[2];				// temporarily holds binomials for multiplication

		for(int x = 0; x<values.length; x++){

			//calc Pi(x)
			double[] Pix = new double[values.length];			// Pi terms of LP, which are summed to get LP
			double[] accumulator = new double[values.length]; 	// initialize to one?

			Pix[0] = values[x];		// initialize Pix to Yi

			// multiply binomials
			for(int k = 0; k<values.length; k++){
				// skip if k==x
				if(k==x) continue;

				//create binomial (i.e. x+3)
				binomial[1] = (double)1/(x-k);		// x term
				binomial[0] = (double)(-k)/(x-k);		// constant term
				
				//multiply accumulator by binomial
				Pix = biMult(Pix,binomial);

			}

			// Add Pi(x) to terms
			for(int j=0; j<values.length; j++){
				Px[j] = Px[j] + Pix[j];
			}
		}

		Polynomial result = new Polynomial(Px);
		return result;
	}

	public static double[] biMult(double[] polynomial, double[] binomial){

		double[] accumulator = new double[polynomial.length];
			
		for(int i=0; i<polynomial.length; i++){
			double a = polynomial[i]*binomial[0];

			double b;
			if(i-1<0) b = 0;
			else b = polynomial[i-1]*binomial[1];

			accumulator[i] = a+b;
		}
		
		return accumulator;
	}

}
