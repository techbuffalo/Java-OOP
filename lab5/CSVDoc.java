//Created by Noah Duran - nduran

package lab5;

public class CSVDoc extends Document {
    public int rowCount;
    public int columnCount;

    //Gathering in the parent filename and initializing the counts to zero
    public CSVDoc(String filename) {
        super(filename);
        this.rowCount = 0;
        this.columnCount = 0;
    }

    @Override
    void collectDocInfo() {
        //Setting the input to string
        String csvContent = fileContent.toString();

        //Taking in csvContent and splitting it at each new line to get the number of rows
        String[] rows = csvContent.split("\n");
        rowCount = rows.length;

        //Taking the first row in the array and splitting it at a comman since all rows have the same number of entries
        String[] columns = rows[0].split(",");
        columnCount = columns.length;

    }
    
}
