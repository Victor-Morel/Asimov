package testing;

import java.io.File;

/**
  * Copyright 2000 Webmind Inc.
  * Textui: <p>
  * The TestGenerator program has being designed for use by the tester at Webmind Inc..
  * It will help the testers generate some of the test code needed in order to perform JUnit tests.
  * The program works as follows:
  *
  * The tester will run the file and give the location of the .class file or the directory containing the
  * .class files.
  *     ex:   java Generator path\JavaFile.class <Destination>
  *     ex:   java Generator com <Destination>
  *
  * <Destination> the location where you would like to store the outputted test files generated.
  *  If no argument is given a directory call default will be create with the tests.
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @author Karan Saxena (ksaxena@webmind.com)
  * @version 1.1
  */
public class Textui  {

  /**
    * This is the starting point of the program, it will accept an argument like
    *       c:\cvs_wcs\com\webmind\....\sample.class
    * @param String[] (com\webmind\cs\....)
    * @return (tests generated)
    */
  public static void main(String[] args) {
    File theFile = null;
    if (args.length < 1 || args.length > 2) {
      throw new IllegalArgumentException("Please give a directory or file to test!");
    }

    //checking for the second argument
    if (args.length == 2) {
      location = args[1];
    }

    try {
      theFile = new File(args[0]);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.err.println(ex);
    }

    if (!theFile.exists()) {
      throw new IllegalArgumentException(args[0] + " does not exist");
    }

    visitClassFiles(theFile);

    System.out.println(ls + "Total number of test cases generated are = " + totalGenerated);
    System.exit(0);
  }//end main

  /**
    * Recurses through the directories searching for .class files to generate tests on.
    * @param aFile
    */
  private static void visitClassFiles(File file) {
    Generator test = null;
    File[] list = null;

    if (file.isDirectory()) {
      list = file.listFiles();
      for (int i = 0; i < list.length; i++)
        visitClassFiles(list[i]);
    }

    if (file.getName().endsWith(".class")) {
      if((file.getName().indexOf('$')) == -1) {
        try{
          test = new Generator( file, location );
      }catch(ClassNotFoundException e) { e.printStackTrace();}

        if (test != null) {
          if ( test.createTest() )
            System.out.println(test.fileDone + " was created!");
            totalGenerated++;
        }
      }
    }//end if
  }

  private final static String ls = System.getProperty("line.separator");
  private final static String fs = System.getProperty("file.separator");
  private static int totalGenerated = 0;
  private static String location = "Default";

}
