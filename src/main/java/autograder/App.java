package autograder;

import autograder.utils.FileCompiler;
import autograder.utils.FileExtractor;

public class App 
{
    public static void main( String[] args ) {
        String zipFilePath = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Zips\\816024135_A1.zip";
        String targetDirectory = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Extracted\\Matthew";
        String compiledDirectory = targetDirectory;

        try {

            // Extract ZIP file
            System.out.println("Extracting ZIP...");
            FileExtractor.extractZip(zipFilePath, targetDirectory);

            // Step 2: Compile Extracted Files
            System.out.println("Compiling Java files...");
            boolean success = FileCompiler.compileJavaFiles(targetDirectory);
            if (!success) {
                System.err.println("Compilation failed!");
                return;
            }

            // Step 3: Locate Compiled Classes
            System.out.println("Compiled classes are in: " + compiledDirectory);
            

            // Step 4: Run Tests on Compiled classes
            AssignmentTest a1 = AssignmentTestFactory.getAssignmentTest("A1");
            a1.runTests(compiledDirectory);

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
