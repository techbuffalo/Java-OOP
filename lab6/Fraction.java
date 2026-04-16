package lab6;

import java.util.Scanner;

/** This class handles fractions. 
 * It adds fractions and reduces the result to its lowest 
 * numerator and denominator by finding their greatest common divisor.
 */

public final class Fraction {
	int numerator;
	int denominator;
	
	/** Constructor to initialize numerator and denominator to 1 */
	Fraction() {	
		
		numerator = 1;
		denominator = 1;
	}
	
	/** Constructor to initialize numerator and denominator to n and d */
	Fraction(int n, int d) {		
		// we will initialize 'this' to the numerator and denominator which will be n and d.
		// the 'this' is used to refer to the current numbers that are being passed into the constructor
		this.numerator = n;
		this.denominator = d;

		//Initialing the reduction method that will return the fraction at the lowest terms
		reduce();
	}
	
	/** findGCD() recursively returns reduced fraction 
	 * as per the algorithm given in the handout
	 */
	int findGCD(int a, int b) { 

		//Method to find the greatest common demnoinator of two numbers as per lab6 instruction
		 if (a == 0) return 1;
		 if (b == 0) return a;
		 else return findGCD(b, a % b);

	}

	/** reduce() reduces the fraction to its lowest terms 
	 * by dividing numerator and denominator by their GCD
	 */
	void reduce() {

		// reduce will use findGCD to get the new denominator than divide numerator and denominator by that new number 
		int reducedFraction = findGCD(numerator, denominator);
		numerator /= reducedFraction;
		denominator /= reducedFraction;
	}

	/**  toString() returns a string as n/d 
	 * where n and d are numerator and denominator respectively
	 */
	@Override
	public String toString() {	
		// simple output of the fraction 
		return numerator + "/" + denominator;
	}

	/** toDecimal() returns the decimal value of n/d 
	 * where n and d are numerator and denominator respectively
	 */
	double toDecimal() {	
		// getting the decimal value using doubles as advised in instructions
		double decimalValue = (double) numerator / (double) denominator;
		return decimalValue;
	}

	/** add() adds two fractions using the formula 
	 * sumFraction = (n1*d2 + n2*d1)/d1*d2
	 * where two fractions being added are n1/d1 and n2/d2.
	 * It reduces sumFraction using findGCD() and  returns reduced fraction.
	 * Note that it takes only one fraction as a parameter. 
	 * It uses 'this' as the second fraction.
	 */
	Fraction add(Fraction f) {
		// now that the denominators are the common, we can get the new fraction  

		// the 'this' in the methods is the first fraction while 'f' is the second 
		 int newNumerator = this.numerator * f.denominator + f.numerator * this.denominator;
		 int newDenominator = this.denominator * f.denominator;
		 
		 // finally we have the new fraction that may or may not need to be reduced. reduce is called below so we have our final answer
		 Fraction sumFraction = new Fraction(newNumerator, newDenominator);
		 
		 sumFraction.reduce();
		 
		 return sumFraction;
	}
	
	
	//**** do not change anything in the main method ****
	public static void main(String[] args) {	
		Scanner input = new Scanner (System.in);
		System.out.println("********** Input first fraction *********");
		System.out.println("Numerator: ");
		int n1 = input.nextInt();
		System.out.println("Denominator: ");
		int d1 = input.nextInt();
		
		System.out.println("********** Input second fraction *********");
		System.out.println("Numerator: ");
		int n2 = input.nextInt();
		System.out.println("Denominator: ");
		int d2 = input.nextInt();
		
		Fraction f1 = new Fraction(n1, d1);
		Fraction f2 = new Fraction(n2, d2);

		System.out.println("f1 = " + f1);
		System.out.println("f2 = " + f2);
		
		Fraction f3 = f1.add(f2);
		System.out.println("f1 + f2 = " +     f1.add(f2) );
		System.out.println("The sum in decimal is: " + f3.toDecimal());
		input.close();
	}
}

