//HW3 - Noah Duran - CMU nduran

package hw3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/** Basic text-file utilities used by the TextEditor app. */
public class FileUtilities {

    /** Reads a text file and preserves line breaks. */
    public StringBuilder readFile(String filename) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader fileReading = new BufferedReader(new FileReader(filename))) {
            String fileLine;
            while ((fileLine = fileReading.readLine()) != null) {
                fileContent.append(fileLine).append(System.lineSeparator());
            }
        } catch (IOException fileReadingError) {
            System.err.println(
                "Couldn't read file " + filename + ". Please check file location and spelling then try again!"
            );
            System.exit(0);
        }
        return fileContent;
    }

    /** Writes the provided text to the given file path. */
    public String writeFile(String filename, String fileText) {
        
        //Implementing the try catch per instructions. If the file is saved successfully, it will return "File saved". If there is an error, it will return "Could not save file".
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename))) {
            fileWriter.write(fileText == null ? "" : fileText);
            return "File saved";
        } catch (IOException fileWritingError) {
            return "Could not save file";
        }
    }

       /** Counts words using whitespace as the delimiter. */
    public int countWords(StringBuilder fileContent) {
        String wordsInFile = fileContent.toString().trim();
        if (wordsInFile.isEmpty()) {
            return 0;
        }
        String[] words = wordsInFile.split("\\s+");
        return words.length;
    }

    public int countUniqueWords(StringBuilder fileContent) {
        if (fileContent == null || fileContent.toString().trim().isEmpty()) {
            return 0;
        }
        //Setting up an array of 'words' that has all the words from the file using the regex from the instructions.
        //All the words will be lower case so that test and Test are not counted as different words.
        String[] words = fileContent.toString().toLowerCase().split("[^a-zA-Z0-9']+");

        //Creating a hashset so there are no duplications
        HashSet<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords.size();
    }

    /** Returns all positions where searchString occurs, or null if not found. */
    int[] searchAll(StringBuilder fileContent, String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return null;
        }

        String textInFile = fileContent.toString();
        int count = 0;
        int textIndex = textInFile.indexOf(searchString);

        while (textIndex != -1) {
            count++;
            textIndex = textInFile.indexOf(searchString, textIndex + 1);
        }

        if (count == 0) {
            return null;
        }

        int[] indexPositions = new int[count];
        int i = 0;
        textIndex = textInFile.indexOf(searchString);

        while (textIndex != -1) {
            indexPositions[i++] = textIndex;
            textIndex = textInFile.indexOf(searchString, textIndex + 1);
        }

        return indexPositions;
    }

    /** Replaces all occurrences of oldString with newString and returns the count. */
    int replace(StringBuilder fileContent, String oldString, String newString) {
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
