package autograder.assignments;

import autograder.AssignmentTest;
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
    protected void runUnitTests() {
        // Run unit tests for assignment 1
        ClassTest chatBotTest = new ChatBotTest(targetPath);
        chatBotTest.run();
    }

    @Override
    protected void runIntegrationTests() {
        // Run Integration tests for assignment 1
    }

    @Override void generateResults() {
        // Generate PDF results for assignment 1
    }
}
