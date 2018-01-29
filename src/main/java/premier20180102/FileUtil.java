package premier20180102;

import java.io.File;

public class FileUtil {
  
  public static File getResourceFile(String filename) {
    ClassLoader classLoader = FileUtil.class.getClassLoader();
    File file = new File(classLoader.getResource(filename).getFile());
    return file;
  }
}
