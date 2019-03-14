import java.util.Arrays;
/*
 * PROJECT II: Polynomial.java -- Naglis
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
        /**Check the arary from the last to first positions
         check the last array numbers leading coeff, if they are = 0, keep dereasing
         when the final leading coeff = 0 is found the array is sliced and updated
        */
       int last = coeff.length - 1;
       while (coeff[last].getReal() == 0 && coeff[last].getImag() ==0) last -=1;
       Complex[] newarr = Arrays.copyOfRange(coeff, 0, last+1);
       this.coeff = newarr;
       
        }
    
 
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
        this.coeff = new Complex[1];
		this.coeff[0] = new Complex();
        
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        // You need to fill in this function.
        String poli = "";
        for (int i =0; i < this.coeff.length; i++ ) {
            if (i== 0) {
                poli = poli + "(" + this.coeff[i].toString() + ")";
            }
            else {
                poli = poli + "+(" + this.coeff[i].toString() + ")X^" + i;
            }
        }
        return poli;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
        if (coeff.length > 0) return this.coeff.length - 1;
        return 0;
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
       	if (this.coeff.length <= 0) return null;	
		// Get the last element of coeff 	
		Complex nocomplex = new Complex(this.coeff[this.coeff.length - 1].getReal(),this.coeff[this.coeff.length - 1].getImag());
		// include 0?
		for (int i = this.coeff.length - 2; i >= 0; i--) {
			nocomplex = z.multiply(nocomplex).add(this.coeff[i]);
		}
		return nocomplex;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
        // You need to fill in this function.
        // if coeff = 0, return new polynomial (because it does not exist) // FIX ME 
        if (this.coeff.length == 0) return new Polynomial();	
        // if coeff = 1, derivative 0,0 
        if (this.coeff.length == 1) return new Polynomial();
        //create a polynomial's derrivative array 
        Complex[] poliderivative = new Complex[this.coeff.length - 1];
        int j = 1;
        for (int i=0; i <= poliderivative.length - 1; i++) {
            if (j <= coeff.length -1) {
                poliderivative[i] = this.coeff[j].multiply((j));
                j++;
            }
        }
		return new Polynomial(poliderivative);
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You need to fill in this function. rewrite me
        int size = 4;
		/*Complex[] arr = new Complex[size];
		for (int i = 0; i < size - 1; i++) {
			arr[i] = new Complex(i+1.0,i+1.0);
		}
			arr[3] = new Complex();*/
		Complex [] arr = new Complex [] { new Complex(-1.0,0.0), new Complex (1,1), new Complex (2,2), new Complex(1.0,0) };
		Complex[] arr2 = {new Complex(18,3)};
		Polynomial p = new Polynomial(arr);
		
		System.out.println("p2.toString():		" + p.toString());
		System.out.println("p2.derivative:		" + p.derivative().toString());
		System.out.println("p2.degree():		" + p.degree());
		System.out.println("p.evaluate(-1-2i):	" + p.evaluate(new Complex(-1,-2)));
		
		Polynomial p2 = new Polynomial(arr2);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("p2.toString():		" + p2.toString());
		System.out.println("p2.derivative:		" + p2.derivative().toString());
		System.out.println("p2.degree():		" + p2.degree());
		System.out.println("p.evaluate(-1-2i):	" + p2.evaluate(new Complex(-1,-2)));
		System.out.println("-------------------------------------------------------------------------------");
		
		Polynomial p3 = new Polynomial();
		System.out.println("p2.toString():		" + p3.toString());
		System.out.println("p2.derivative:		" + p3.derivative().toString());
		System.out.println("p2.degree():		" + p3.degree());
		System.out.println("p.evaluate(-1-2i):	" + p3.evaluate(new Complex(-1,-2)));
		System.out.println("-------------------------------------------------------------------------------");

		Complex [] coeff = new Complex [] { new Complex(-1.0,0), new Complex (1,1), new Complex (2,2), new Complex(1.0,0) };
		Polynomial p4 = new Polynomial(coeff);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("p4.toString():		" + p4.toString());
    }
}
