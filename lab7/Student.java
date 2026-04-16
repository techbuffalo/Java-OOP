//Created by Noah Duran - nduran

package lab7;


/** Student class should implement Comparable interface to sort Student objects
 * on their lastName. 
 */

public class Student implements Comparable<Student> {

    String lastName, firstName, andrewID;
    float score;

    Student(String lastName, String firstName, String andrewID, float score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.andrewID = andrewID;
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        return this.lastName.compareTo(other.lastName);
    }
}