package uk.ac.ucl.dataframe;

import java.util.ArrayList;

//requirement 1 --> Column class 

public class Column {

    private String columnName; //name of column from csv file
    private ArrayList<String> rows; //arrayList of rows in each column

    public Column(String columnName) {
        this.columnName = columnName;
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return columnName;
    }

    public int getSize() { 
        return rows.size();
    }

    public String getRowValue(int row) { 
        if (row < rows.size()) {
            return rows.get(row);
        }
        return null;
    }

    public void setRowValue(int row, String value) { 
        if (row < rows.size()) {
            rows.set(row, value);
        }
    }

    public void addRowValue(String value){ 
        rows.add(value);
    }

    public void deleteRow(int row) { //deletes specific row from this.column
        if (row >= 0 && row < rows.size()) {
            rows.remove(row);
        }
    }

}
