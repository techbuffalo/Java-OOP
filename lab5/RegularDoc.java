//Created by Noah Duran - nduran

package lab5;

public class RegularDoc extends Document {
    public int wordCount;

    //Gathering in the filename from the Document file and initally setting wordCount to zero
    public RegularDoc(String filename) {
        super(filename);
        this.wordCount = 0;
    }

    @Override
    void collectDocInfo() {
        //Ensuring all the contents of the file are strings and trimming out the excess beginnging and ending white space
        String wordsInFile = fileContent.toString().trim();

        //Adding all the words into an array, splitting them at the white space between the words
        String [] words = wordsInFile.split("\\s+");

        wordCount = words.length;

    }
}
