package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    /** Counts words using whitespace as the delimiter. */
    int countWords(StringBuilder fileContent) {
        String wordsInFile = fileContent.toString().trim();
        if (wordsInFile.isEmpty()) {
            return 0;
        }
        String[] words = wordsInFile.split("\\s+");
        return words.length;
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
