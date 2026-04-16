// Created by Noah Duran - nduran

package lab7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**StudentRoster class reads data from roster.csv file and loads Student objects into its studentList.
 * It has two inner classes FirstNameComparator and ScoreComparator to sort students on their firstName and score respectively.
 */
public class StudentRoster {
	List<Student> studentList = new ArrayList<>();
	
	//Do not change this method
	public static void main(String[] args) {
		StudentRoster sr = new StudentRoster();
		sr.readData();
		sr.sortByLastName();
		System.out.println("********************** Sorted by Last name **********************");
		sr.printRoster();
		sr.sortByFirstName();
		System.out.println("********************** Sorted by First name **********************");
		sr.printRoster();
		sr.sortByScore();
		System.out.println("********************** Sorted by Score **********************");
		sr.printRoster();
	}
	
	//Do not change this method
	void printRoster() {
		System.out.println(" #. Last name\t\tFirst name\tAndrew ID\tScore");
		System.out.println("----------------------------------------------------------------------");
		int count = 1;
		for (Student s: studentList) System.out.printf("%2d. %-15s\t%-15s\t%-10s\t%.2f%n", count++,
				s.lastName, s.firstName, s.andrewID, s.score);
	}
	
	/**readData() method opens and reads roster.csv file and 
	 * loads the studentList arrayList with Student objects. 
	 */
	void readData() {
		//write your code here

		//since roster is to be saved in the same directory as the project, we can just use the file name
		String filePathString = "roster.csv";
		//being that the roster is a csv file we can use comma as the seperator 
		String delimiter = ",";

		
		try (BufferedReader br = new BufferedReader(new FileReader(filePathString))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(delimiter);
				if (data.length == 4) {
					String lastName = data[0].trim();
					String firstName = data[1].trim();
					String andrewID = data[2].trim();
					float score = Float.parseFloat(data[3].trim());
					studentList.add(new Student(lastName, firstName, andrewID, score));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void sortByLastName() {
		//since the Student class does the comparison based on last name, we can just call sort with null
		studentList.sort(null);

	}
	
	void sortByFirstName() {
		//calling the sort method using first names
		studentList.sort(new FirstNameComparator());
	}
	
	void sortByScore() {
		//calling the sort method using scores
		studentList.sort(new ScoreComparator());
	}
	
	//write your Comparators here
	
	//implementing the comparator but using first names instead
	public class FirstNameComparator implements Comparator<Student> {
	public int compare(Student s1, Student s2) {
		return s1.firstName.compareTo(s2.firstName);
	}
}
	//implementing the comparator but using scores instead
	public class ScoreComparator implements Comparator<Student> {
	public int compare(Student s1, Student s2) {
		return Float.compare(s2.score, s1.score);
	}
}

}
