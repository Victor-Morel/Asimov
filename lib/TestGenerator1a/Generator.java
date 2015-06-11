package testing;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.Enumeration;

/**
  * Copyright 2000 Webmind Inc.
  * Generator: <p>
  *
  * The TestGenerator program has being designed for use by the tester at Webmind Inc..
  * It will help the testers generate some of the test code needed in order to perform JUnit tests.
  * The program works as follows:
  *
  * This application can be run from a text based environment or a windows enviroment.
  * For Text Based run the Textui.java file and give the required parameters
  * For Window enviroment run the TestGenerator.java file and point and click your way around.
  *
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @author Karan Saxena (ksaxena@webmind.com)
  * @version 1.1
  */
public class Generator {

  protected Class testClass;        // class to generate test for
  protected String className;       // short name of class
  protected String destination;     // directory where tests are to be placed
  protected String fullClassName;   // full name (includes package)
  protected String author;
  protected String e_mail;
  protected String company;

  /**
   * Constructor
   *
   * @param source  location of .class file to be tested
   */
  public Generator(File file) throws ClassNotFoundException{
    this(file, DEFAULT_LOCATION);
  }

  /**
   * Constructor
   *
   * @param source  location of .class file to be tested
   * @param destination location to output generated tests
   */
  public Generator(File file, String destination) throws ClassNotFoundException{
    this(file, destination, "<Your Name>", "<e-mail>", "<company>");
  }

  /**
   * Constructor
   *
   * @param source  location of .class file to be tested
   * @param destination location to output generated tests
   */
  public Generator(File file, String destination, String a, String e, String c) throws ClassNotFoundException{
      if ( file == null || destination == null || !file.exists() ) {
      throw new IllegalArgumentException("Do not send null parameters to constructor");
    }

    testClass = getClass(file);
    if(testClass == null)
      throw new ClassNotFoundException("Class not found");

    fullClassName = testClass.getName();
    className = fullClassName.substring( fullClassName.lastIndexOf('.') + 1, fullClassName.length() );
    this.author = a;
    this.e_mail = e;
    this.company = c;
    this.destination = destination;
  }


  /**
    * Generates the top portion of the test harness comments
    *
    * @throws throws ClassNotFoundException
    * @return Returns the string to be added to the file generated
    */
  protected String getTopComments() {
    //Top Comments
    StringBuffer sb = new StringBuffer( className + "Test    " );
    sb.append("(Copyright 2001 " + company + ")" + ls);
    sb.append(ls);
    sb.append("<p> This class performs unit tests on " + fullClassName + " </p>" + ls);
    sb.append(ls);
    sb.append("<p> Explanation about the tested class and its responsibilities </p>" + ls);
    sb.append(ls);
    sb.append("<p> Relations:" + ls);
    Class sup = null;
    try{
      sup = testClass.getSuperclass();
    }catch(IllegalAccessError e) { e.printStackTrace();}

    if (sup != null) {
      sb.append("    " + className + " extends " + sup.getName() + " <br>" + ls);
    }
    else {
      sb.append("    " + className + " extends Object <br>" + ls);
    }

    Class[] interfaces = testClass.getInterfaces();
    String inStr = "";
    for (int i = 0; i < interfaces.length; i++) {
      inStr = inStr.concat( interfaces[i].getName().concat(", ") );
    }
    if(interfaces.length != 0)
      inStr = inStr.substring(0, inStr.length() - 2);   // get rid of ending comma

    if (inStr != "") {
      sb.append("    " + className + " implements " + inStr + " </p>" + ls);
    }

    sb.append(ls);
    sb.append("@author " + author + " " + e_mail + " - " + company + ls);
    sb.append("@date $Date: $" + ls);
    sb.append("@version $Revision: $" + ls);
    sb.append(ls);
    sb.append("@see " + fullClassName + ls);
    sb.append("@see some.other.package" + ls);

    return insertStars(sb.toString(), "");
  }

  /**
   * Generates the top portion of the test harness, mostly comments and commonly used things
   *   which will not have to be typed in by the tester
   *
   * @throws ClassNotFoundException
   * @return String
   */
  protected String GenerateTopTest() {
    //makes a call to generate the top comments
    StringBuffer sb = new StringBuffer( ("// " + className + "Test.java" + ls) );

    // get package from full class name
    int i = fullClassName.lastIndexOf('.');
    if (i > 0) {
      sb.append(ls);
      sb.append("package " + fullClassName.substring(0, i) + ";" + ls);
    }
    sb.append(ls);
    sb.append("import junit.framework.TestCase;" + ls);
    sb.append("import junit.framework.TestSuite;" + ls);
    sb.append(ls);
    sb.append( getTopComments() );
    sb.append(ls);
    sb.append(ls);

    //begining of test
    sb.append("public class " + className + "Test extends " + "TestCase" + ls);
    sb.append("{" + ls);

    //comments
    String comments = "Constructor (needed for JTest)" + ls;
    comments = comments.concat("@param name    Name of Object").concat(ls);

    sb.append(ls);
    sb.append( insertStars(comments, "  ") );
    sb.append(ls);

    sb.append("  public " + className + "Test" + "(String name) {" + ls);
    sb.append("    super(name);" + ls);
    sb.append("  }" + ls);

    //comments setUp
    comments = "Used by JUnit (called before each test method)" + ls;

    sb.append(ls);
    sb.append( insertStars(comments, "  ") );
    sb.append(ls);

    //setp
    sb.append("  protected void setUp() {" + ls);
    sb.append("    //" + className.toLowerCase() + " = new " + className + "();" + ls);
    sb.append("  }");
    sb.append(ls);

    //comments tearDown
    comments = "Used by JUnit (called after each test method)" + ls;

    sb.append(ls);
    sb.append( insertStars(comments, "  ") );
    sb.append(ls);

    //tearDown
    sb.append("  protected void tearDown() {" + ls);
    sb.append("    " + className.toLowerCase() + " = null;" + ls);
    sb.append("  }" + ls);

    return sb.toString();
  }

  /**
    * Generates the bottom portion of the test harness, mostly comments and commonly used things
    *   which will not have to be typed in by the tester
    *
    * @return String
    */
  protected String GenerateBottomTest() {
    StringBuffer sb = new StringBuffer(ls);

    //comments for main method
    String comments = "Main method needed to make a self runnable class" + ls;
    comments = comments.concat(ls);
    comments = comments.concat("@param args This is required for main method" + ls);

    sb.append( insertStars(comments, "  ") );
    sb.append(ls);

    //main method
    sb.append("  public static void main(String[] args) {" + ls);
    sb.append("    junit.textui.TestRunner.run( new TestSuite(" + className + "Test.class) );" + ls);
    sb.append("  }" + ls);

    //private variable
    sb.append("  private " + className + " " + className.toLowerCase() + ";" + ls);

     //end of test
    sb.append("}");

    return sb.toString();
  }

  /**
    * This method tries to generate the constructor this or constructor tests, it\
    * will check for multiple constructors and call the analyseConstructor function
    *
    * @throws ClassNotFoundException
    * @returns Returns the string generated for the analyseConstructor call
    */
  protected String GenerateConstructorTest() {
    int occurences = 0;
    StringBuffer sb = new StringBuffer("");
    try{
      Constructor c [] = testClass.getDeclaredConstructors();

      //returns the constructor of the class
      for(int i = 0; i < c.length; i++) {
        if(isConstructorTestable(c[i]))
          sb.append(analyseConstructor(c[i], occurences));
          occurences++;
      }
      return sb.toString();
    }catch(Error e) {
      System.out.println("*****" + e.getMessage() + "Error occured in the testing of the constructor for - "
        + fullClassName + " *****" + ls);

      return sb.toString();
     }

  }

  /**
    * This method analyses the constructor for testing purposes, it will check the
    * params of the constructor
    * @param Constructor, String
    * @return Returns the string to be added to the test file going to be created
    */
  protected String analyseConstructor(Constructor construct, int occurences) {

    StringBuffer sb = new StringBuffer("");

    Class paramTypes [] = construct.getParameterTypes();//returns the paramTypes
    String fullConstructorStr = construct.toString();

    String arr [] = CheckTestParam(paramTypes);

    boolean marker = false;
    String paramNames = "";
    for(int i = 0; i < arr.length; i++)
      if(arr[i] != "") {
        if(marker)
          paramNames = paramNames.concat(", ");
        paramNames = paramNames.concat(arr[i]);
        marker = true;
      }

    sb.append(ls);
    String comments = "Test the constructor: " + className + "(" + paramNames + ")";
    sb.append( insertStars(comments, "  ") );
    sb.append(ls);
    if(occurences > 0)
      sb.append("  public void test" + className + "_" + occurences + "() {" + ls );
    else sb.append("  public void test" + className + "() {" + ls );

    arr = CheckDuplicateParam(arr);//checking for duplicated parameters

    for(int i = 0; i < arr.length; i++)
      if(arr[i] != "") {
        sb.append("    //Must test for the following parameters!" + ls);
        sb.append("    " + GetTestParam(arr[i]));
      }

    sb.append(ls + "  }" + ls);

    return sb.toString();//return the new string for test method

  }

  /**
    * Creates the middle portion of the test harness, with the public methods to be tested
    * @param String, String
    * @throws ClassNotFoundException
    * @return Returns the string to be added to the test file to be created
    */
  protected String GenerateMethodTests() {
    StringBuffer sb = new StringBuffer("");
    try{
      Method [] mArr = testClass.getDeclaredMethods();

      checkDuplicateMethods(mArr);

      //calls the analyseMethods one method at a time
      for(int i = 0; i < mArr.length; i++) {
        if( isMethodTestable(mArr[i]) )
          sb.append(analyseMethods(mArr[i]));
      }

      return sb.toString();
    }catch(Error e) {
      System.out.println("*****" + e.getMessage() + "Error occured in the testing of the methods for - "
        + fullClassName + " *****" + ls);

      return sb.toString();
     }

  }

  /**
   * Checks for duplicated method names, if so it will keep a counter of the
   * number of duplicate methods
   * @param mArr []
   */
  private void checkDuplicateMethods(Method mArr []) {
    String compare = null;
    int count = 1;

    for(int i = 0; i < mArr.length; i++) {
      for(int x = 0; x < mArr.length; x++) {
        if(i != x) {
          compare = mArr[i].getName();

          if(compare.equals(mArr[x].getName())) {
            duplicateMethods.put(compare, "" + count);
            count++;

          }
        }
      }
      count = 1;
    }

  }

  /**
    * Attempts to analyse the methods in order to create an appropriate test harness
    * @param Method and String
    * @return String
    */
  protected String analyseMethods(Method method) {
    int occurences = 0;

    String name = method.getName();//will return just the name of the method
    if(duplicateMethods.containsKey(name))
      occurences = Integer.parseInt((String)duplicateMethods.get(name));

    Class paramTypes [] = method.getParameterTypes();//returns the paramTypes
    String fullMethodStr = method.toString();

    String arr [] = CheckTestParam(paramTypes);
    StringBuffer sb = new StringBuffer("");

    boolean marker = false;
    String paramNames = "";

    for(int i = 0; i < arr.length; i++)
      if(arr[i] != "") {
        if(marker)
          paramNames = paramNames.concat(", ");
        paramNames = paramNames.concat(arr[i]);
        marker = true;
      }

      StringBuffer sbName = new StringBuffer(name);
      char makeUpperCase = sbName.charAt(0);
      makeUpperCase = Character.toUpperCase(makeUpperCase);//makes into upper case the first letter of the name
      sbName.setCharAt(0, makeUpperCase);//sets the uppercase letter

      String retValue = "";
      if(method.getReturnType() != null) {
        retValue = method.getReturnType().getName();
        if(retValue.indexOf("java.lang.String") != -1) {
          if(retValue.endsWith(";"))
            retValue = "String []";
          else retValue = "String";
        }
        if(retValue.endsWith(";"))
          retValue = retValue.concat(" []");
      }

      sb.append(ls);
      String comments = "Test method: " + retValue + " " + name + "(" + paramNames + ")";
      if(method.getExceptionTypes().length > 0) {
        Class [] cl = method.getExceptionTypes();
        for(int i = 0; i < cl.length; i++) {
          comments = comments.concat(ls);
          comments = comments.concat(name + " throws " + cl[i].getName());
        }
      }
      sb.append( insertStars(comments, "  ") );
      sb.append(ls);

      if(occurences > 0) {
        sb.append("  public void test" + sbName + "_" + occurences + "() {" + ls );
        occurences--;
        if(occurences > 0)
          duplicateMethods.put(name, "" + occurences);
        else duplicateMethods.remove(name);
      }
      else sb.append("  public void test" + sbName + "() {" + ls );

      arr = CheckDuplicateParam(arr);

      for(int i = 0; i < arr.length; i++) {
        if(arr[i] != "") {
          sb.append("    //Must test for the following parameters!" + ls);
          sb.append("    " + GetTestParam(arr[i]));
        }
      }

      sb.append(ls + "  }" + ls);

    return sb.toString();//return the new string for test method

  }

  /**
   * This method will check for two or more of the same parameters.  If so it
   * will remove one from the array.  I am doing this so that duplicated testing
   * parameters are not writen to the test case generated
   *
   * @param String []
   * @return String []
   */
  protected String [] CheckDuplicateParam(String search []) {
    for(int i = 0; i < search.length; i++) {
      for(int x = 0; x < search.length; x++) {
        if (i != x)
          if (search[i].equals(search[x]))
            search[x] = "";
      }
    }

    return search;
  }

  /**
    * Checking for params that should be tested for.
    * @param Class array
    * @return a boolean array specifying the values that should be tested for
    */
  protected String [] CheckTestParam(Class paramTypes []) {

    String arr [] = new String [size];

    for(int i = 0; i < arr.length; i++)
      arr[i] = "";

    //check param's
    for(int i = 0; i < paramTypes.length; i++) {
      String paramStr = paramTypes[i].getName();

      int lastPeriod = paramStr.lastIndexOf(".");
      if(lastPeriod != -1) {
        String otherParam = paramStr.substring(lastPeriod + 1, paramStr.length());
        if(otherParam.endsWith(";")) {
          String otherParamArr = otherParam.substring(0, otherParam.length() - 1);
          arr[i] = otherParamArr + "[]";
        }
        else {
          arr[i] = otherParam;
        }
      }
      else {
        arr[i] = paramStr; //Primitive Java type
      }
    }

    return arr;

  }

  /**
    * After checking for the params to test for this function will be called to
    *  retrieve the appropriate string to test
    * @param int
    * @return String to test for
    */
  protected String GetTestParam(String type) {

    String result = "";
    if(type.equals("String")) {
      result = "String str [] = {null, \"\\u0000\", \" \"};" + ls;
    }
    else if(type.equals("int")) {
      result = "int intValues [] = {-1, 0, 1, Integer.MAX_VALUE, Integer.MIN_VALUE};" + ls;
    }
    else if(type.equals("double")) {
      result = "double dValues [] = {-1.0, 0.0, 1.0, Double.MAX_VALUE, Double.MIN_VALUE," +
      " Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN};" + ls;
    }
    else if(type.equals("float")) {
      result = "float fValues [] = {-1f, 0f, 1f, Float.MAX_VALUE, Float.MIN_VALUE, " +
      " Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NaN};" + ls;
    }
    else if(type.equals("Object")) {
      result = "Object oValues [] = { null };" + ls;
    }
    else if(type.equals("long")) {
      result = "long lValues [] = {-1, 0, 1, Long.MAX_VALUE, Long.MIN_VALUE};" + ls;
    }
    else if(type.equals("byte")) {
      result = "byte bValues [] = { Byte.MAX_VALUE, Byte.MIN_VALUE };" + ls;
    }
    else if(type.equals("char")) {
      result = "char cValues [] = { ' ', '\u0000', Character.MAX_VALUE, Character.MIN_VALUE };" + ls;
    }
    else if(type.equals("short")) {
      result = "short sValues [] = {-1, 0, 1, Short.MAX_VALUE, Short.MIN_VALUE };" + ls;
    }
    else if(type.equals("boolean")) {
      result = "boolean BValues [] = { true, false };" + ls;
    }
    else
      result = "//" + type + ";" + ls;

    return result;

  }

  /**
   * Checks to see if the file exist, if it does it will return true, and if not false
   * @return result
   */
  public boolean doesItExist() {
    boolean result = true;
    try {

      File newDir = new File(destination);

      int lastSeparator = fullClassName.lastIndexOf('.');
      if (lastSeparator > 0) {
        String path = fullClassName.substring(0, lastSeparator);
        path = path.replace('.', File.separatorChar);
        newDir = new File(newDir + fs + path);
      }

      //creating the file under a mirror image structure
      File newFile = new File(newDir + fs + className + "Test.java");
      if(newFile.exists()) {
        result = true;
      }
      else {
        result = false;
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return result;

  }

  /**
    * Creates the test file and writes to the file with the generated test harness
    * @return boolean
    */
  public boolean createTest() {

    //making sure the class is public and can be tested, if not return and continue to other classes
    if(!isClassTestable(testClass))
      return false;

    //creating the actual dir and file structure
    try {

      File newDir = new File(destination);
      if(!newDir.exists())
        newDir.mkdirs();

      int lastSeparator = fullClassName.lastIndexOf('.');
      if (lastSeparator > 0) {
        String path = fullClassName.substring(0, lastSeparator);
        path = path.replace('.', File.separatorChar);
        newDir = new File(newDir + fs + path);
      }

      if(!newDir.exists())
        newDir.mkdirs();

      //creating the file under a mirror image structure
      File newFile = new File(newDir + fs + className + "Test.java");
      newFile.createNewFile();

      // output
      FileOutputStream fos = new FileOutputStream(newFile);
      PrintWriter out = new PrintWriter(fos);

      String constructInfo = GenerateConstructorTest();//gets constructor info

      StringBuffer sb = new StringBuffer(GenerateTopTest());//gets the top portion of the test harness

      sb.append(constructInfo);//adds the constructor test if one applies
      sb.append(GenerateMethodTests());//adds a test for each method if they applie
      sb.append(GenerateBottomTest());//gets the bottome portion of the test harness

      out.println(sb.toString());//printing to the file
      out.flush();
      out.close();//closes the file

      fileDone = newFile.getName();
      //System.out.println(newFile.getName() + " was generated..");

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }

    return true;
  }

  /**
    * Checking if a class is test-able
    * @param class to be tested
    * @return true if class can be tested
    */
  protected boolean isClassTestable(Class cl) {
    return !( Modifier.isPrivate(cl.getModifiers()) || cl.isInterface() );   // private class isn't test-able
  }

  /**
    * Checking if a method is test-able
    * @param method to be tested
    * @return true if method can be tested
    */
  protected boolean isMethodTestable(Method method) {
    boolean isPrivate = Modifier.isPrivate(method.getModifiers());
    String name = method.getName();
    boolean hasMain = name.endsWith("main");
    return (!isPrivate && !hasMain);
  }

  /**
    * Checking if a constructor is test-able
    * @params constructor to be tested
    * @return true if constructor can be tested
    */
  protected boolean isConstructorTestable(Constructor c) {
    return !Modifier.isPrivate(c.getModifiers());
  }

  /**
   * Creates Class object from .class file specified
   *
   * @param file    path to .class file
   * @returns Class object of file
   */
  private Class getClass(File file) {
    Class cl = null;
    WMClassLoader wm = new WMClassLoader();

    try {
      cl = wm.loadClass(file.getPath());
    } catch (ClassNotFoundException e ) {
      System.err.println("Class not Found" + e.getMessage());
    } catch(Error ex) {
      System.out.println("*****" + ex.getMessage() + " occured in the testing of the class named - "
        + file.getName() + " *****" + ls );}


    return cl;
  }

  /**
    * Inserts the stars for the comments
    * @param str  String to be enclosed in comments
    * @param tab  how far to indent comments
    * @return comments
    */
  private String insertStars(String str, String tab) {
    StringReader stringReader = new StringReader(str);
    LineNumberReader lnr = new LineNumberReader(stringReader);
    String result = tab + "/**" + ls;
    try {
      String line = lnr.readLine();

      while (line != null) {
        result = result.concat(tab).concat(" * ").concat(line).concat(ls);
        line = lnr.readLine();
      }
    } catch (IOException e) { e.printStackTrace(); }

    result = result.concat(tab).concat(" */");
    return result;
  }

  private final static String ls = System.getProperty("line.separator");
  private final static String fs = System.getProperty("file.separator");
  private final int size = 10;
  private final static String DEFAULT_LOCATION = "default";
  private static String location = DEFAULT_LOCATION;
  private static String errorMsg = "Specify a .class file or a directory to scan, and the output location!";
  private Hashtable duplicateMethods = new Hashtable();
  public String fileDone = "";

}

