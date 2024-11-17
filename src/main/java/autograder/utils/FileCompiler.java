package autograder.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class FileCompiler { // For compiling Java files
    public static boolean compileJavaFiles(String dirPath) {
        File dir = new File(dirPath);
        List<File> javaFiles = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".java")) {
                javaFiles.add(file);
            }
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaFiles);
        boolean success = compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        return success;
    }
}
