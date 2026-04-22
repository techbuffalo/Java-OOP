//Created by Noah Duran - CMU nduran


package lab9;

public class LoanAccount implements Comparable<LoanAccount> {
	String accountNumber;
	double principle;
	float interest;
	int years;
	
	double calculateEMI() {
		return principle * (interest/12) *  Math.pow((1 + interest/12) , years*12) / (Math.pow((1 + interest/12) , years*12) - 1);
	}
	
	// Creating the constructor to compar the EMI of the two loans
	@Override
	public int compareTo(LoanAccount o) {
		return Double.compare(this.calculateEMI(), o.calculateEMI());
	}
}
