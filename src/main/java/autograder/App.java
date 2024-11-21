package autograder;

import autograder.strategy.FileCompilationStrategy;
import autograder.strategy.FileExtractionStrategy;
import autograder.strategy.TestExecutionStrategy;
import java.util.Scanner;;

public class App 
{
    public static void main( String[] args ) {
        String zipFilePath = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Zips";
        String targetDirectory = "C:\\Users\\jonny\\Downloads\\OOPTEST\\Extracted";

        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter the source path: ");
        String src = keyboard.nextLine();

        System.out.println("Enter the destination path: ");
        String dest = keyboard.nextLine();

        keyboard.close();

        if (src.isEmpty()) {src = zipFilePath;}
        if (dest.isEmpty()) {dest = targetDirectory;}

        GradingContext context = new GradingContext();

        try {
            // Add strategies in order
            context.addStrategy(new FileExtractionStrategy());
            context.addStrategy(new FileCompilationStrategy());
            context.addStrategy(new TestExecutionStrategy());

            // Execute all strategies
            context.executeStrategies(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
