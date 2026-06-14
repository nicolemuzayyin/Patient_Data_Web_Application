Patient Data Web Application

## Overview

This Java web application manages patient records loaded from CSV files using servlets, JSPs, and the MVC pattern. Built with Apache Tomcat, it features a custom DataFrame structure for data storage, comprehensive search functionality, statistical analysis with CSS-based charts, and full CRUD operations with data persistence.

## Data Structure and Loading

Patient data is loaded from CSV files (patients100.csv to patients100000.csv) into a custom DataFrame composed of Column objects. The DataLoader class handles CSV parsing with robust error handling for missing files or malformed data. All data is stored as strings with the DataFrame providing efficient row and column access methods. The Model class manages the DataFrame through a singleton pattern, ensuring consistent data access across all servlets.

## Web Interface

The application provides five main pages: a home page, a patient list with ability to add a new patient, a search page, a patient page for individual patient details + editing/deleting their data, and a statistics dashboard. Navigation is handled through a header on all pages. Servlets process all HTTP requests and pass data to JSPs as attributes. JSPs contain only presentation logic with no direct data manipulation, maintaining clean MVC separation.

## Search and Filtering

Multi-field search allows keyword matching across all patient attributes where results can be filtered by gender (Male/Female) and vital status (Living/Deceased) and sorted by name or birth date. Search results display patient names as clickable links leading to detailed patient pages for editing or deletion.

## Statistics and Visualization

The statistics page displays the gender distribution, age distribution of living patients, and geographic distribution of patients by city. Visual charts are implemented using CSS techniques: a conic-gradient pie chart for gender distribution and a bar chart for age group distributions. All statistics update as patient data changes (altered patient data, deleted patients, added patients).

## Data Management

CRUD operations are implemented: add new patients through web forms, edit existing records, and delete patients with confirmation. All changes immediately update the DataFrame and trigger CSV file rewriting to maintain data persistence. JSON export functionality saves the complete dataset using a custom JSONWriter class.

## How to Run

Note: To run, Java and Maven are required
1. Place CSV files in the data/ folder
2. Run the following commands:

```bash
mvn clean
mvn clean compile exec:exec
```

3. Open http://localhost:8080 in your browser

The application automatically loads patients1000.csv on startup. For larger datasets, update the filename reference in the ModelFactory class and ensure the CSV file is read properly.
