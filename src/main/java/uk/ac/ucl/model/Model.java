package uk.ac.ucl.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.dataframe.DataFrame;
import uk.ac.ucl.dataframe.DataLoader;

//requirement 4 --> model class

public class Model {
  private DataFrame dataFrame;
  private String dataFilename;

  public void loadData(String filename) {
    this.dataFilename = filename;
    DataLoader loader = new DataLoader();
    dataFrame = loader.load(filename);
  }

  public List<String> getColumnNames() {
    return dataFrame.getColumnNames();
  }

  public int getRowCount() {
    return dataFrame.getRowCount();
  }

  // get all patient rows as List<Map<String, String>>
  public List<Map<String, String>> getAllPatients() {
    List<Map<String, String>> patients = new ArrayList<>();
    int rowCount = dataFrame.getRowCount();
    List<String> columns = dataFrame.getColumnNames();
    for (int i = 0; i < rowCount; i++) {
      Map<String, String> patient = new LinkedHashMap<>();
      for (String col : columns) {
        patient.put(col, dataFrame.getValue(col, i));
      }
      patients.add(patient);
    }
    return patients;
  }

  // requirement 6 --> search/filter patients AND requirement 7 --> sorting search
  // results
  public List<Map<String, String>> searchPatients(String keyword, String gender, String status, String orderBy) {
    List<Map<String, String>> results = new ArrayList<>();
    String lowerKeyword;
    if (keyword == null) {
      lowerKeyword = "";
    } else {
      lowerKeyword = keyword.toLowerCase();
    }
    int rowCount = dataFrame.getRowCount();
    List<String> columns = dataFrame.getColumnNames();
    for (int i = 0; i < rowCount; i++) {
      boolean match = true;
      // keyword search
      if (!lowerKeyword.isEmpty()) {
        match = false;
        for (String col : columns) {
          String value = dataFrame.getValue(col, i);
          if (value != null && value.toLowerCase().contains(lowerKeyword)) {
            match = true;
            break;
          }
        }
      }
      // gender filter
      if (match && gender != null && !gender.isEmpty()) {
        String patientGender = dataFrame.getValue("GENDER", i);
        if (patientGender == null || !patientGender.equalsIgnoreCase(gender)) {
          match = false;
        }
      }
      // marital status filter
      if (match && status != null && !status.isEmpty()) {
        String deathDate = dataFrame.getValue("DEATHDATE", i);
        if (status.equalsIgnoreCase("living") && deathDate != null && !deathDate.isEmpty()) {
          match = false;
        } else if (status.equalsIgnoreCase("deceased") && (deathDate == null || deathDate.isEmpty())) {
          match = false;
        }
      }
      if (match) {
        Map<String, String> patient = new LinkedHashMap<>();
        for (String col : columns) {
          patient.put(col, dataFrame.getValue(col, i));
        }
        results.add(patient);
      }
    }
    // sorting by birthdate or last name
    if (orderBy != null && !orderBy.isEmpty()) {
      switch (orderBy) {
        case "birthdate_asc":
          results.sort((a, b) -> {
            String va = a.get("BIRTHDATE");
            String vb = b.get("BIRTHDATE");
            if (va == null) {
              va = "";
            }
            if (vb == null) {
              vb = "";
            }
            return va.compareTo(vb);
          });
          break;
        case "birthdate_desc":
          results.sort((a, b) -> {
            String va = a.get("BIRTHDATE");
            String vb = b.get("BIRTHDATE");
            if (va == null) {
              va = "";
            }
            if (vb == null) {
              vb = "";
            }
            return vb.compareTo(va);
          });
          break;
        case "lastName_asc":
          results.sort((a, b) -> {
            String va = a.get("LAST");
            String vb = b.get("LAST");
            if (va == null) {
              va = "";
            }
            if (vb == null) {
              vb = "";
            }
            return va.compareToIgnoreCase(vb);
          });
          break;
        case "lastName_desc":
          results.sort((a, b) -> {
            String va = a.get("LAST");
            String vb = b.get("LAST");
            if (va == null) {
              va = "";
            }
            if (vb == null) {
              vb = "";
            }
            return vb.compareToIgnoreCase(va);
          });
          break;
      }
    }
    return results;
  }

  public Map<String, String> getPatientById(String id) {
    int rowCount = dataFrame.getRowCount();
    List<String> columns = dataFrame.getColumnNames();
    for (int i = 0; i < rowCount; i++) {
      if (id.equals(dataFrame.getValue("ID", i))) {
        Map<String, String> patient = new LinkedHashMap<>();
        for (String col : columns) {
          patient.put(col, dataFrame.getValue(col, i));
        }
        return patient;
      }
    }
    return null;
  }

  // requirement 8 --> add, edit, delete a row from the model and save changes to
  // CSV file
  // add a new patient
  public void addPatient(Map<String, String> data) throws IOException {
    dataFrame.addRow(data);
    saveData();
  }

  // update patient by id (edit)
  public void updatePatient(String id, Map<String, String> data) throws IOException {
    int rowCount = dataFrame.getRowCount();
    List<String> columns = dataFrame.getColumnNames();
    for (int i = 0; i < rowCount; i++) {
      if (id.equals(dataFrame.getValue("ID", i))) {
        for (String key : data.keySet()) {
          if (columns.contains(key)) {
            dataFrame.putValue(key, i, data.get(key));
          }
        }
        saveData();
        return;
      }
    }
  }

  // delete patient by id
  public void deletePatient(String id) throws IOException {
    int rowCount = dataFrame.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      if (id.equals(dataFrame.getValue("ID", i))) {
        dataFrame.deleteRow(i);
        saveData();
        return;
      }
    }
  }

  // for statistics --> get patient count by city (applies to requirement 7)
  public Map<String, Integer> getPatientCountByCity() {
    Map<String, Integer> cityMap = new LinkedHashMap<>();
    int rowCount = dataFrame.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      String city = dataFrame.getValue("CITY", i);
      if (city != null && !city.isEmpty()) {
        cityMap.put(city, cityMap.getOrDefault(city, 0) + 1);
      }
    }
    return cityMap;
  }

  // for chart statistics --> get patient count by gender (applies to requirement 7 and 10)
  public Map<String, Integer> getPatientCountByGender() {
    int male = 0, female = 0;
    int rowCount = dataFrame.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      String gender = dataFrame.getValue("GENDER", i);
      if ("M".equals(gender))
        male++;
      else if ("F".equals(gender))
        female++;
    }
    Map<String, Integer> result = new LinkedHashMap<>();
    result.put("Male", male);
    result.put("Female", female);
    return result;
  }

  // for chart statistics --> get patient count by age group (applies to requirement 7 and 10)
  public Map<String, Integer> getPatientCountByAgeGroup() {
    String[] groups = { "0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90+" };
    int[] counts = new int[10];
    int rowCount = dataFrame.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      String deathDate = dataFrame.getValue("DEATHDATE", i);
      if (deathDate != null && !deathDate.isEmpty()) {
        continue;
      }
      String birthdate = dataFrame.getValue("BIRTHDATE", i);
      if (birthdate != null && birthdate.length() >= 10) {
        try {
          int age = Period.between(LocalDate.parse(birthdate), LocalDate.now()).getYears();
          int ageGroupIdx;
          if (age / 10 > 9) {
            ageGroupIdx = 9;
          } else {
            ageGroupIdx = age / 10;
          }
          counts[ageGroupIdx]++;
        } catch (Exception ignored) {
        }
      }
    }
    Map<String, Integer> result = new LinkedHashMap<>();
    for (int i = 0; i < groups.length; i++)
      result.put(groups[i], counts[i]);
    return result;
  }

  // save data to CSV
  private void saveData() throws IOException {
    if (dataFilename == null || dataFrame == null) {
      return;
    }
    List<String> columns = dataFrame.getColumnNames();
    int rowCount = dataFrame.getRowCount();
    try (java.io.FileWriter writer = new java.io.FileWriter(dataFilename)) {
      // write header (column names)
      writer.write(String.join(",", columns));
      writer.write("\n");
      // write rows --> patient data
      for (int i = 0; i < rowCount; i++) {
        List<String> values = new ArrayList<>();
        for (String col : columns) {
          String val = dataFrame.getValue(col, i);
          if (val == null) {
            values.add("");
          } else {
            values.add(val);
          }
        }
        writer.write(String.join(",", values)); //separate values by commas
        writer.write("\n"); //new line after each patient row
      }
    }
  }

  public DataFrame getDataFrame() {
    return dataFrame;
  }
}
