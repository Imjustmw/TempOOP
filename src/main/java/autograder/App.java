package autograder;

import autograder.strategy.FileCompilationStrategy;
import autograder.strategy.FileExtractionStrategy;
import autograder.strategy.TestExecutionStrategy;


public class App 
{
    public static void main( String[] args ) {
        String zipFilePath = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Zips";
        String targetDirectory = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Extracted";

        GradingContext context = new GradingContext();

        try {
            // Add strategies in order
            context.addStrategy(new FileExtractionStrategy());
            context.addStrategy(new FileCompilationStrategy());
            context.addStrategy(new TestExecutionStrategy());

            // Execute all strategies
            context.executeStrategies(zipFilePath, targetDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
