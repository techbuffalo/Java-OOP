//Noah Duran - nduran
package lab2;

import java.math.BigInteger;
import java.util.Scanner;

/**Factorial class calculates factorial of a number of potentially unlimited size.
 * It has an overloaded calcFactorial method, one with int and the other 
 * with BigInteger as a parameter. 
 * The main method takes user input as a number and then prints its factorial value  
 */
public class Factorial {
	
	//DO NOT change the method signature
	int calcFactorial(int n) {
		//creating variable factoralSum to store the outcome of the calculated factoral
		int factorialSum = 1;

		//setting i to n and then iterate through it until hitting 0
		for (int i = n; i > 0; i--) {
			//taking the current value of factorialSum and multiplying it by whatever i currently is
			factorialSum *= i;
		}
		return factorialSum;
	}


	//DO NOT change the method signature
	BigInteger calcFactorial(BigInteger n) {
		//creating variable factoralSum to store the outcome of the calculated factoral
		//though online research I found BigInteger.ONE as a recommendation as an immutable constant when perforing factorials
		BigInteger factorialSum = BigInteger.ONE;

		//setting i to n and then iterate through it until hitting 0
		//using compareTo(BigInteger.ZERO) because you cannot compare a BigInteger to an int 0
		for (BigInteger i = n; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
			//taking the current value of factorialSum and multiplying it by whatever i currently is using the .multiply method for BigIntegers
			factorialSum = factorialSum.multiply(i);
		}
		return factorialSum;
	}
	
	public static void main(String[] args) {
		int n = 0;
		Factorial factorial = new Factorial();

		//using try for the scanner so it will auto close and the scanner will ensure users only input int
		try (Scanner scanner = new Scanner(System.in)){
		//Prompting user for input 
		System.out.print("Please enter your number: ");
		//setting n to whatever int the user inputs
		n = scanner.nextInt();

		if (n < 13) 
			System.out.printf("Factorial of %d is %,d%n", n, factorial.calcFactorial(n) );
		else 
			System.out.printf("Factorial of %d is %,d%n", n, factorial.calcFactorial(BigInteger.valueOf(n)));

		}

	}
}
