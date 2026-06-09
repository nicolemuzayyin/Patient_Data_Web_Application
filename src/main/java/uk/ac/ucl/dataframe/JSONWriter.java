package uk.ac.ucl.dataframe;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JSONWriter {
    //requirement 9 --> JSONWriter class to write DataFrame data to JSON file

    public void write(DataFrame dataFrame, String filePath) throws IOException {
        List<String> columnNames = dataFrame.getColumnNames(); 
        int rowCount = dataFrame.getRowCount();

        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) { //ensure file exists, otherwise create it
            out.println("["); // start of JSON array
            int writtenRows = 0;
            int nonEmptyCount = 0;
            // count non-empty rows based on the ID column
            for (int i = 0; i < rowCount; i++) {
                String id = dataFrame.getValue("ID", i);
                if (id != null && !id.isEmpty()) {
                    nonEmptyCount++;
                }
            }
            for (int i = 0; i < rowCount; i++) {
                String id = dataFrame.getValue("ID", i);
                if (id == null || id.isEmpty()) continue;
                out.println("  {"); // start of JSON object for each row
                for (int j = 0; j < columnNames.size(); j++) {
                    String col = columnNames.get(j);
                    String value = dataFrame.getValue(col, i);
                    if (value == null) value = ""; //replace null values with empty strings
                    value = value.replace("\"", "\\\""); // escape quotes in values
                    out.print("    \"" + col + "\": \"" + value + "\"");
                    if (j < columnNames.size() - 1) out.print(",");
                    out.println();
                }
                out.print("  }");
                writtenRows++;
                if (writtenRows < nonEmptyCount) out.print(",");
                out.println();
            }
            out.println("]");
        }
    }
}
