package uk.ac.ucl.main;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main
{
  public static void main(String[] args) throws Exception
  {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080); // set server port
    tomcat.getConnector(); // initialize tomcat connector

    String webappPath = new java.io.File("src/main/webapp/").getAbsolutePath();
    Context context = tomcat.addWebapp("/", webappPath); // add webapp by its absolute path

    //tells tomcat where to find the compiled servlet classes to load them
    WebResourceRoot resources = new StandardRoot(context);
    String classesPath = new java.io.File("target/classes").getAbsolutePath();
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
        classesPath, "/")); 
    context.setResources(resources);

    tomcat.start(); // start the server
    System.out.println("server started on port 8080");
    tomcat.getServer().await(); // keep server running
  }
}