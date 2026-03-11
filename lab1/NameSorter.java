package lab1;

import java.util.Arrays;
import java.util.Scanner;

/**NameSorter class takes n number of names in the form of string inputs 
 * from the user. It then asks user for which name to search for. 
 * It sorts the names entered by the user, and then prints the 
 * position of the search-name in the sorted list of the names, 
 * if it is found.
 */

//Edited for week 3 lab by Noah Duran - nduran CMU

public class NameSorter {
	Scanner input = new Scanner(System.in);
	
	/**getNameInputs takes an int parameter n and creates an array of size n. 
	 * It then asks user to Enter n names that get stored in the array. 
	 * It uses the helper method toTitleCase() to convert all names to Title case. 
	 * It returns the array filled with names entered by the user.
	 */
	String[] getNameInputs(int n) {
		//Taking in the number of names that the user will give
		input.nextLine();
		String[] inputNames = new String[n];
		//Until i is greater than the variable n that the user put in place it will continue to print Enter name # :
		for (int i = 0; i < n; i++) {
			System.out.print("Enter name " + (i + 1) + ":");
			inputNames[i] = toTitleCase(input.nextLine());
		}
		return inputNames;
	}
	
	/**toTitleCase() takes one string argument as name and returns the string in title case. 
	 * If the name is null or the string is empty, it returns null.
	 */
	String toTitleCase(String name) {
		//Taking in the string that the user enters which should be a name
		//If it is a null value it will just return null, but if not it will use the built in toUpperCase on the first charcter then append the rest to the end of that character
		if (name == null || name.isEmpty()) return null;
		name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}

	/**sortAndSearch() takes two arguments. The first is an array of strings and the second
	 * is a searchString. The method first sorts the array in increasing alphabetical order, 
	 * and prints it in that order.
	 * It then searches for the searchString in a case-insensitive way. If the searchString is found,
	 * it returns the position of the searchString according to the sorted list. 
	 * If it is not found, then it returns -1.
	 */
	int sortAndsearch(String[] strings, String searchString) {
		//Handling null values
		if (strings == null || searchString == null) {
			return -1;
		}
		//I set n to the length of the incoming array and then used the built in Arrays.sort from java to set the names up
		//Following this I iterated through the array and printed out each name in order.
		int n = strings.length;
		Arrays.sort(strings);
		System.out.println();
		for (int i = 0; i < n; i++) {
            System.out.println(strings[i]);
        }
		System.out.println();

		//Searching through the array to find the matching input
		for (int i = 0; i < n; i++) {
			if (strings[i].equalsIgnoreCase(searchString)) {
				return i;
			}
		}
		return -1;
	}

	/**DO NOT CHANGE THIS METHOD */
	public static void main(String[] args) {
		NameSorter ns = new NameSorter();
		System.out.println("*** How many names to store? ***");
		int n = ns.input.nextInt();
		if (n > 0) {
			String[] names = ns.getNameInputs(n);
			System.out.println("*** Enter the name to search ***");
			String name = ns.input.next();
			int position = ns.sortAndsearch(names, name);
			if (position >=0 ) System.out.println(name + " found at position " + (position+1));
			else System.out.println("Sorry! " + name + " not found!");
		} else System.out.println("Good Bye!");
	}
}
