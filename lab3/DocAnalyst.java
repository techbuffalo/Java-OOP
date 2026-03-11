//Created by Noah Duran - nduran

package lab3;
import java.util.Scanner;

public class DocAnalyst {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Prompting the user for the filepath that they want checked
        System.out.print("Enter the filepath of the desired file: ");
        String userFile = input.nextLine();

        //If the user inputs anyname that ends with .csv then the CSVDoc file is used
        if (userFile.endsWith(".csv")){
            CSVDoc csvDoc = new CSVDoc(userFile);
            csvDoc.readFile();
            csvDoc.collectDocInfo();

            System.out.println("The CSV file's row count is: " + csvDoc.rowCount);
            System.out.println("The CSV file's column count is: " + csvDoc.columnCount);
        }

        //If the user inputs anythign that ends with .txt the RegularDoc file is used
        else if (userFile.endsWith(".txt")){
            RegularDoc regularDoc = new RegularDoc(userFile);
            regularDoc.readFile();
            regularDoc.collectDocInfo();

            System.out.println("The text file's word count is: " + regularDoc.wordCount);

        }

        //If the use inputs anything other than .csv or .txt then it will prompt them to try again
        else {
            System.out.println(userFile + " has an unrecognized file type. Please try again with a .txt or .csv file!");

        }

     input.close();
    }
  
}
