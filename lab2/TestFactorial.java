package lab2;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class TestFactorial {

	Factorial factorial = new Factorial();
	
	@Test
	public void test1_ZeroFactorial() {
		assertEquals("Test 0!", 1, factorial.calcFactorial(0));
	}
	
	@Test
	public void test2_NegativeFactorial() {
		assertEquals("Test -2!", 1, factorial.calcFactorial(-2));
	}
	
	@Test
	public void test3_LessThan13Factorial() {
		assertEquals("Test 7!", 5040, factorial.calcFactorial(7));
	}
	
	@Test
	public void test4_GreaterThan13Factorial() {
		assertEquals("Test 15!", "1307674368000", factorial.calcFactorial(new BigInteger("15")).toString());
	}
}
