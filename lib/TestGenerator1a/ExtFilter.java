package testing;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
  * Copyright 2000 Webmind Inc.
  * ExtFilter: <p>
  *
  * This class filters the file extensions, so that it dsplays only .class files.
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @version 1.0
  */
public class ExtFilter extends FileFilter {

  /**
   * Accept all directories and all class and java files.
   * @param file
   * @return boolean
   */
  public boolean accept(File f) {
    if (f.isDirectory())
      return true;

    String extension = getExtension(f);
	  if (extension != null) {
      if (extension.equals(ext))
        return true;
      else
        return false;
    }

    return false;
  }

  /**
   * The description of this filter
   * @return String
   */
  public String getDescription() {
    return "Classes";
  }

  /*
   * Get the extension of a file.
   * @param file
   * @return String
   */
  public String getExtension(File f) {
    String extension = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 &&  i < s.length() - 1) {
      extension = s.substring(i+1).toLowerCase();
    }
    return extension;
  }

  private String ext = "class";

}
