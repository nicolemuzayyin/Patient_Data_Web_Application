package uk.ac.ucl.dataframe;

import java.util.ArrayList;
import java.util.Map;

//requirement 2 --> dataframe class to hold a collection of columns

public class DataFrame {

    private ArrayList<Column> columns = new ArrayList<>(); 

    public void addColumn(Column column) { 
        columns.add(column);
    }

    public ArrayList<String> getColumnNames() { 
        ArrayList<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.getName());
        }
        return names;
    }

    public int getRowCount() { 
        if (columns.isEmpty()) {
            return 0;
        }
        return columns.get(0).getSize();
    }

    public String getValue(String columnName, int row) { 
        Column column = findColumn(columnName);
        if (column == null) {
            return null;
        }
        return column.getRowValue(row);
    }

    public void putValue(String columnName, int row, String value) { 
        Column column = findColumn(columnName);
        if (column != null) {
            column.setRowValue(row, value);
        }
    }

    public void addValue(String columnName, String value) { 
        Column column = findColumn(columnName);
        if (column != null) {
            column.addRowValue(value);
        }
    }

    public void addRow(Map<String, String> rowData) { //add new row to the dataframe 
        for (Column column : columns) {
            String value = rowData.get(column.getName());
            if (value == null) {
                value = "";
            }
            column.addRowValue(value);
        }
    }

    public void deleteRow(int row) { //deletes a row from the dataframe
        for (Column column : columns) {
            column.deleteRow(row);
        }
    }

    private Column findColumn(String columnName) {  // helper method to find a column by name
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }
}
