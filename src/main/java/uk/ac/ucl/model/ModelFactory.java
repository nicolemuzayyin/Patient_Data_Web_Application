package uk.ac.ucl.model;

import java.io.IOException;

public class ModelFactory { 
  //create and initializes a model object by loading data from the csv file via the dataloader class
  // this method is called by the servlets to access the model and retrieve patient data for display
  private static final Model model = new Model();
  private static boolean loaded = false;

  public static Model getModel() throws IOException {
      if (!loaded) {
          model.loadData("data/patients1000.csv");
          loaded = true;
      }
      return model;
  }
}
