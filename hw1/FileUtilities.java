//Created by Noah Duran - nduran 

package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** FileUtilities class provides some basic tools to read a file, count words, search and replace strings. 
 */
public class FileUtilities {

	/**readFile() method takes filename as a string parameter. 
	 * It opens the file which is assumed to be residing in the project folder.
	 * It reads the file's each line into a StringBuilder. While reading, 
	 * it preserves the line breaks. It then returns the StringBuilder.
	 */
	public StringBuilder readFile(String filename)  {

		/**Using BufferedReader to read in the file contents and StringBuilder to build the new string
		 * fileContent is the variable that will hold the new string consisting of whatever is in filename
		 * I found this article helpful online that explained what BufferedReader is https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
		 */
		StringBuilder fileContent = new StringBuilder();
		try (BufferedReader fileReading = new BufferedReader(new FileReader(filename))) {
			String fileLine;
			/**This while loop will append fileContent with each line based on whats inside of filename
			 * The System.lineSeparator() funciton uses \n by default, meeting the req to preserve line breaks
			 */
			while ((fileLine = fileReading.readLine()) != null) {
				fileContent.append(fileLine).append(System.lineSeparator());
			}
		}
		catch (IOException fileReadingError) {

			/**In the case the file couldn't be read the program will output a failure message then quit
			 * This stops the program from running the rest of the word count and replace methods
			 */

			System.err.println("Couldn't read file " + filename + ". Please check file location and spelling then try again! \nGoodbye!");
			System.exit(0);
			}
		//When fileContent is returned to the program it will be displayed in the same format as the original file.
		return fileContent;				
	}
	/** wordCount method receives text content in a StringBuilder object and 
	 * returns its word count. It considers white-space as a word-delimiter.  
	 */
	int countWords(StringBuilder fileContent) {
		//Setting the fileContents to all be a string and trimming leading and ending whitespace
		String wordsInFile = fileContent.toString().trim();

		/**Now that all the file contents are one long string I used \\s+ as the delimter
		 * This ensures that if there is a double space or a tab, the white space is still considered
		 */
		String[] words = wordsInFile.split("\\s+");

		//Pulling the count based on the length of words and returning it for TextEditor.java
		int wordCount = words.length;
		return wordCount;
	}
	
	/**searchAll() method receives text content in a StringBuilder and 
	 * returns all the char-positions at which searchString is found.
	 * These positions are counted from the beginning of the file starting with 0.
	 * For example, if the fileContent has: "What a wonderful world", 
	 * the searchString 'wonder' is at char-position 7. 
	 * The search is case-sensitive. 
	 * If the searchString is not found, it returns null. 
	 * Hints: 
	 * 1. You may find String methods indexOf() and substring() useful. 
	 * 2. You may have to traverse the fileContent twice. Once to count the number of times 
	 * the searchString is found, and second time to store its positions. 
	 */
	int[] searchAll(StringBuilder fileContent, String searchString) {

		//If statement to catch if the user hits enter with no input
		if (searchString.isEmpty()) {
			System.out.println("Nothing was entered, try again!");
			return null;
		}
		//Setting the text in the file to all be a string
		String textInFile = fileContent.toString();

		/**Creating int count to track how many times the input of searchString is found
		 * indexOf will return -1 if there isn't a match so a while loop was used to check the int textIndex
		 * count is raised by one each time the index of searchString is found 
		 * the search begins again one space after the previously found searchString until the end textInFile
		 */
		int count = 0;
		int textIndex = textInFile.indexOf(searchString);
		while (textIndex != -1) {
			count++;
			textIndex = textInFile.indexOf(searchString, textIndex + 1);
		}

		//This if statement will catch if the searchString wasn't found in the file and then prompt the else statement in TextEditor
		if (count == 0) {
			return null;
		}

		/**With the number of times found looping back over the text to correlate the position to the array
		 * the array of indexPositions will equate to whatever count is, so if the code found 10 of the searchString the array will be 10 int large
		 * the program then records the position of the first character of the string for the position and replaces count in the array with i
		 */
		int[] indexPositions = new int[count];
		int i = 0;
		textIndex = textInFile.indexOf(searchString);
		while (textIndex != -1) { 
			indexPositions[i] = textIndex;
			i++;
			textIndex = textInFile.indexOf(searchString, textIndex + 1);	
		}
		return indexPositions;
	}


	/** replace method receives text content in a StringBuilder object, and 
	 * replaces all occurrences of oldString with new String.
	 * It returns the number of replacements done.
	 * If oldString is not found, it means that no replacement happens. In such a case, it returns 0.
	 */
	int replace(StringBuilder fileContent, String oldString, String newString) {
		/**Similar to searchAll finding the index of the old word and using while to replace it
		 * using .replace to take the beginning index of the old word through its length and replace it with new string
		 * each time this occurs countOfReplacement is increased by 1 
		 * indexOfOld is changed to start again one character after the replaced string
		 */
		int countOfReplacements = 0;
		int indexOfOld = fileContent.indexOf(oldString);
		while (indexOfOld != -1) { 
			fileContent.replace(indexOfOld, indexOfOld + oldString.length(), newString);
			countOfReplacements++;
			indexOfOld = fileContent.indexOf(oldString, indexOfOld + newString.length());
		}
		return countOfReplacements;
	}
}
