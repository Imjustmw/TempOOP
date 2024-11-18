package autograder.assignments;

import autograder.AssignmentTest;
import autograder.assignments.assignment1.ChatBotGeneratorTest;
import autograder.assignments.assignment1.ChatBotPlatformTest;
import autograder.assignments.assignment1.ChatBotTest;
import autograder.models.ClassTest;

// Template for testing Assignment 1
public class Assignment1Test extends AssignmentTest {
    @Override
    protected void loadClasses(String targetPath) {
        // Load specific classes for assignment 1
    }

    @Override
    protected void compileClasses(String targetPath) {
        // Compile classes for assignment 1
    }

    @Override
    protected void runUnitTests(String targetPath) {
        // Run unit tests for assignment 1
        ClassTest chatBotTest = new ChatBotTest(targetPath, "ChatBot", 36);
        chatBotTest.run();
        chatBotTest.printResults();

        ClassTest chatBotGenerator = new ChatBotGeneratorTest(targetPath, "ChatBotGenerator", 7);
        chatBotGenerator.run();
        chatBotGenerator.printResults();

        ClassTest chatBotPlatform = new ChatBotPlatformTest(targetPath, "ChatBotPlatform", 20);
        chatBotPlatform.run();
        chatBotPlatform.printResults();
    }

    @Override
    protected void runIntegrationTests() {
        // Run Integration tests for assignment 1
    }

    @Override 
    protected void generateResults() {
        // Generate PDF results for assignment 1
    }
}
