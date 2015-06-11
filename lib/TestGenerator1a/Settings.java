package testing;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 *
 *
 * @author Karan Saxena
 * @version
 *
 * @date
 */
public class Settings
{
  private File file;
  private Hashtable hashtable;

  /**
   * Checks whether Settings data file exists
   *
   * @throws FileNotFoundException
   */
  public Settings() {
    hashtable = new Hashtable(10);
    file = new File("TestSettings.dat");
  }

  /**
   * Read Settings from file
   */
  public void readSettings() throws IOException {
    if ( !file.exists() ) {
      System.err.println("Settings file not found, using default values");
      setSettings("Your Name", "Your Company", "Your email", "destination directory");
      writeSettings();
      return;
    }

    StringReader stringReader = new StringReader( readFile(file) );
    LineNumberReader lineNumberReader = new LineNumberReader(stringReader);
    StringTokenizer tokenizer = null;
    String key = null;
    String token = null;

    String line = lineNumberReader.readLine();
    while (line != null) {
      tokenizer = new StringTokenizer(line, "=\t\n\r\f");
      if ( tokenizer.countTokens() == 2 ) {
        key = tokenizer.nextToken();
        token = tokenizer.nextToken();
        hashtable.put(key, token);
      }
      line = lineNumberReader.readLine();
    }
  }

  /**
   * Write program settings to a file
   */
  public void writeSettings() throws IOException {
    Enumeration enum = hashtable.keys();
    String key = null;
    String value = null;
    StringBuffer data = new StringBuffer();
    while (enum.hasMoreElements()) {
      key = (String)enum.nextElement();
      value = (String)hashtable.get(key);
      data.append(key + "=" + value + System.getProperty("line.separator"));
    }

    if ( !file.exists() ) {
      file.createNewFile();
    }
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    PrintWriter out = new PrintWriter(fileOutputStream);
    out.println( data.toString().trim() );
    out.flush();
    out.close();
  }

  /**
   * Retrieves hashtable containing settings values
   *
   * @return Hashtable
   */
  public final Hashtable getSettingsAsHashtable() {
    return hashtable;
  }

  /**
   * create a new set of Settings with the specified values
   *
   * @param author
   * @param company
   * @param email
   * @param directory
   */
  public void setSettings(String author, String company,
                             String email, String directory) {
    hashtable = new Hashtable();
    hashtable.put("author", author);
    hashtable.put("company", company);
    hashtable.put("email", email);
    hashtable.put("directory", directory);
  }

  /**
   * Reads a file and converts it to String
   *
   * @param file  File to be read
   * @return String representation of file
   * @throws IOException if error occurs during reading or closing of file
   */
  private String readFile(File file) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(file);
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    byte[] byteArr = new byte[dataInputStream.available()];
    dataInputStream.readFully(byteArr);
    dataInputStream.close();
    return new String(byteArr);
  }

  /**
   * Main method needed to make a self runnable class
   *
   * @param args:   required for main method
   */
  public static void main(String[] args) throws IOException {
    Settings settings = new Settings();

    settings.readSettings();
    settings.writeSettings();
    settings.readSettings();
    settings.setSettings("Karan", "Webmind", "k@w.com", "dir");
    settings.writeSettings();
    System.out.println( settings.getSettingsAsHashtable().toString() );
  }
}