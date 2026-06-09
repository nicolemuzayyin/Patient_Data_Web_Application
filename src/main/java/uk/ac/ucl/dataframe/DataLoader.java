package uk.ac.ucl.dataframe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//requirement 3 --> dataloader class to load data from a CSV file (in data folder) into a dataframe

public class DataLoader {

    public DataFrame load(String filename) {

        // create dataframe object to hold the data from the CSV file
        DataFrame dataFrame = new DataFrame();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)); // read the CSV file

            String headerLine = bufferedReader.readLine(); // first line of CSV file (column names)
            String[] columns = headerLine.split(","); // split the header line by ',' to create array of column names
            for (String columnName : columns) {
                dataFrame.addColumn(new Column(columnName));
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) { // while there are still lines to read in the csv file
                String[] values = line.split(",", -1); // split the line by commas -> create an array of values in order of columns
                // -1 is used to include empty values in the array if there are empty field in the line (EX: 1997-11-10,,M,....[etc.])
                
                for (int i = 0; i < columns.length; i++) { // add the corresponding value to each column in dataframe
                    String value = "";
                    if (i < values.length) {
                        value = values[i];
                    }
                    dataFrame.addValue(columns[i], value); //add the value to the corresponding column
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.err.println("Error loading file: " + filename + " - " + e.getMessage());
        }

        return dataFrame;
    }
}
