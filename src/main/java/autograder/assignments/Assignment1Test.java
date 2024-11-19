package autograder.assignments;

import autograder.AssignmentTest;
import autograder.assignments.assignment1.ChatBotGeneratorTest;
import autograder.assignments.assignment1.ChatBotPlatformTest;
import autograder.assignments.assignment1.ChatBotSimulationTest;
import autograder.assignments.assignment1.ChatBotTest;
import autograder.models.ClassTest;
import autograder.models.Student;
import autograder.utils.PDFGenerator;

// Template for testing Assignment 1
public class Assignment1Test extends AssignmentTest {
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

        ClassTest chatBotSimulation = new ChatBotSimulationTest(targetPath, "ChatBotSimulation", 12);
        chatBotSimulation.run();
        chatBotSimulation.printResults();
    }

    @Override 
    protected void generateResults(Student student) {
        // Generate PDF results for assignment 1
        String content = "COMP2602: OBJECT-ORIENTED PROGRAMMING 2\nAssignment 2\n"; //Formatting the content to look like assignment here
        PDFGenerator.generatePDF(student.getDestinationPath(), content);
    }
}
