package autograder.utils;

import java.io.File;
import java.net.URLClassLoader;

public class ClassLoader { // Load the classes from compiled java files
    public static Class<?> loadClass(String dirPath, String className) throws Exception {
        File file = new File(dirPath);
        URL[] urls = {file.toURI().toURL()};
        URLClassLoader classLoader = new URLClassLoader(urls);
        return classLoader.loadClass(className);
    }
}
