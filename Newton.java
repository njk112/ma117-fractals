/*
 * PROJECT II: Newton.java -- Naglis
 *
 * This file contains a template for the class Newton. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Newton-Raphson root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Newton {
    /**
     * The maximum number of iterations that should be used when applying
     * Newton-Raphson. Ensure this is *small* (e.g. at most 50) otherwise your
     * program may appear to freeze!
     */
    public static final int MAXITER =  80;
    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and _not_ explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Newton.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Newton-Raphson method to.
     */
    private Polynomial f;
    
    /**
     * The derivative of the the polynomial f.
     */
    private Polynomial fp;

    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An integer that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   =  0: Nothing went wrong.
     *   = -1: Derivative went to zero during the algorithm.
     *   = -2: Reached MAXITER iterations.
     */
    private int err;
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor. You should calculate and set fp in this method.
     *
     * @param p  The polynomial used for Newton-Raphson.
     */
    public Newton(Polynomial p) {
        // You need to fill in this method.
        this.f = p;
        this.fp = p.derivative();
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public int getError() {
        // You need to fill in this method.
        return this.err;
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
        // You need to fill in this method.
        return this.numIterations;
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        // You need to fill in this method.
        return this.root;
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
        // You need to fill in this method.
        return this.f;
    }

    /**
     * Returns the derivative of the polynomial associated with this object.
     */
    public Polynomial getFp() {
        // You need to fill in this method.
        return this.fp;
    }

    // ========================================================
    // Newton-Rapshon method
    // ========================================================
    
    /**
     * Given a complex number z0, apply Newton-Raphson to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to 0.
     *   - At some point the derivative of f becomes zero. In this case, set err 
     *     to -1 and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to -2 and return.
     *
     * @param z0  The initial starting point for the algorithm.
     */
    public void iterate(Complex z0) {
        // You need to fill in this method.
        Complex check;
        check = z0; //to compare n1+1 with n1
        for(int i =1; i <= MAXITER; i++) {
            //If f'(z0) less than tolerance, return;
            if (fp.evaluate(z0).abs() < TOL) {
                this.err = -1;
                this.numIterations = i;
                return;
            }
             /**
                * z0 = z0 + (f(z0)/f'(z0))*-1
                * compare z0 (n1+1) with z0 (n1)
                * if |(n1+1) - (n1)| less than tolerance stop and printout value
            */
            z0 = z0.add((f.evaluate(z0).divide(fp.evaluate(z0))).minus());
            if ((check.add(z0.minus()).abs()) > TOL ) check = z0;
            else {
                check = z0;
                this.root = z0;
                this.err = 0;
                this.numIterations = i;
                return;
            }
        }
        //Not enough tries to find root = return; 
        this.err = -2;
        this.numIterations = MAXITER; //or no iterations?
        return;
	    
    }
    // ========================================================
    // Tester function.
    // ========================================================
    
        public static void main(String[] args) {
        // Basic tester: find a root of f(z) = z^3-1 from the starting point
        // z_0 = 1+i.
        System.out.println("f'(z0) = 0");
        System.out.println("------------------------------------------------");
        Complex[] coeff = new Complex[] { new Complex(1,2)};
        Polynomial p    = new Polynomial(coeff);
        Newton     n    = new Newton(p);
        
        n.iterate(new Complex(0, 0));
       	System.out.println("root:		" + n.root);
		System.out.println("err:		" + n.err);
		System.out.println("f:			" + n.f.toString());
		System.out.println("fp:			" + n.fp.toString());
		System.out.println("numIter:	" + n.getNumIterations());
		System.out.println("------------------------------------------------");
		System.out.println("Find root");
		System.out.println("------------------------------------------------");
		Complex[] coeff2 = new Complex[] { new Complex(-1.0,0.0), new Complex(), 
                                          new Complex(), new Complex(1.0,0.0) };
        Polynomial p2    = new Polynomial(coeff2);
        Newton     n2    = new Newton(p2);
        
        n2.iterate(new Complex(1.0, 1.0));
       	System.out.println("root:		" + n2.root);
		System.out.println("err:		" + n2.err);
		System.out.println("f:			" + n2.f.toString());
		System.out.println("fp:			" + n2.fp.toString());
		System.out.println("numIter:	" + n2.getNumIterations());
		System.out.println("------------------------------------------------");
		System.out.println("Not enough tries");
		System.out.println("------------------------------------------------");
		Complex[] coeff3 = new Complex[] { new Complex(-1.0,5.23131231), new Complex(), 
                                          new Complex(1.3123123,7.0), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21), new Complex(1.0,4.3123123),new Complex(-1.0,3.0), new Complex(1.0,2.0), 
                                          new Complex(4.0,2.5), new Complex(1.423423,1.21) };
        Polynomial p3    = new Polynomial(coeff3);
        Newton     n3    = new Newton(p3);
        
        n3.iterate(new Complex(1.0, 1.0));
       	System.out.println("root:		" + n3.root);
		System.out.println("err:		" + n3.err);
		System.out.println("f:			" + n3.f.toString());
		System.out.println("fp:			" + n3.fp.toString());
		System.out.println("numIter:	" + n3.getNumIterations());
}
}
