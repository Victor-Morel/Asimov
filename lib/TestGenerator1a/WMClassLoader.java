package testing;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.lang.reflect.Method;

/**
 * WMCLassLoader: Dynamically loads a .class file,
 *  but requires that you have the location of classes set in your classpath.
 *
 * ie. if the classes are in directory structure beneath C:\project\
 *  then C:\project needs to be added to classpath so that ClassLoader can find
 *  any linked files.
 *
 * @author Karan Saxena (ksaxena@webmind.com)
 * @date January 25, 2001
 * @version 1.0
 *
 * @see java.lang.ClassLoader
 */
public class WMClassLoader extends ClassLoader
{
  /**
   * Default Constructor
   */
  public WMClassLoader()
  {
    // Do Nothing - will automatically call default constructor of ClassLoader
  }

  /**
   * Finds a Class when given path to the file(used by loadClass to find Class)
   *
   * (Overriding findClass from ClassLoader)
   *
   * @param name  path and name of .class file
   * @returns Class
   * @throws ClassNotFoundException
   */
  protected Class findClass(String name) throws ClassNotFoundException
  {
    byte[] b = loadClassData(name);
    return ( b != null) ? defineClass(null, b, 0, b.length) : null;
  }

  /**
   * Loads .class file into a byte[]
   *
   * @param name  path and name of .class file
   * @returns byte[] representation of .class file
   * @throws ClassNotFoundException
   */
  protected byte[] loadClassData(String name) throws ClassNotFoundException
  {
    byte[] b = null;
    FileInputStream file = null;
    DataInputStream dins = null;

    try {
      file = new FileInputStream(name);
      dins = new DataInputStream (file);
      b = new byte[dins.available()];
      dins.readFully(b);
    } // end try

    catch (FileNotFoundException e) {
      throw new ClassNotFoundException("File not found: " + name, e);
    } // end catch
    catch (IOException e) {
      throw new ClassNotFoundException("Problem reading file", e);
    } // end catch
    catch (Exception e) {
      throw new ClassNotFoundException("Class not found for: " + name, e);
    } // end catch

    finally {
      try {
        if (dins != null)
          dins.close();
      } // end try
      catch (IOException e ) {
        System.err.println("Problem closing file: " + e.getMessage());
      } // end catch
      catch (Exception e) {
        System.err.println("Problem closing file: " + e.getMessage());
      } // end catch

      return (b);
    } // end finally
  }   // end loadData

  /**
   * Tests WMClassLoader by printing out methods of class sent as argument
   */
  public static void main(String[] args)
  {
    WMClassLoader wm = new WMClassLoader();
    Class cl = null;
    try {
      cl = wm.loadClass(args[0]);
    } catch (ClassNotFoundException e ) {
      System.err.println("Class not Found" + e.getMessage());
    }
    if (cl == null)
      System.exit(1);
    Method[] m = cl.getMethods();
    if (cl.getPackage() != null) {
      String pack = cl.getPackage().getName();
      System.out.println( "Package: " + pack );
    }
    String name = cl.getName();
    System.out.println( "Name: " + name );
    System.out.println( "New Name: " + (name.substring(name.lastIndexOf('.')+1, name.length())) );
    System.out.println( "Methods:" );

    for (int i = 0; i < m.length; i++)
    {
      System.out.println(m[i].toString());
    }
  }
}