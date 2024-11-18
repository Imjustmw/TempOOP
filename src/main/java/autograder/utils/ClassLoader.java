package autograder.utils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoader { // Load the classes from compiled java files
    public static Class<?> loadClass(String dirPath, String className) {
        try {
            File file = new File(dirPath);
            URL[] urls = {file.toURI().toURL()};
            URLClassLoader classLoader = new URLClassLoader(urls);
            return classLoader.loadClass(className);
        } catch (Exception e) {
            System.err.println("Error occured when loading classes: " + e.getMessage());
            return null;
        }
        
    }
}
