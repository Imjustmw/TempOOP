package autograder.assignments;

import autograder.AssignmentTest;
import autograder.assignments.assignment1.ChatBotGeneratorTest;
import autograder.assignments.assignment1.ChatBotPlatformTest;
import autograder.assignments.assignment1.ChatBotSimulationTest;
import autograder.assignments.assignment1.ChatBotTest;
import autograder.models.ClassTest;
import autograder.models.Student;
import autograder.models.TestResult;
import autograder.utils.PDFGenerator;

// Template for testing Assignment 1
public class Assignment1Test extends AssignmentTest {
    @Override
    protected void runUnitTests(String targetPath) {
        TestResult compilingResult = new TestResult("Compiling");
        TestResult runResult = new TestResult("Running");
        String feedback = "";
        String runFeedback = "";
        boolean compilation = true;
        boolean run = true;
        super.totalScore += 5; // 5 for Compiling
        super.totalScore += 10; // 10 for running

        // Run unit tests for assignment 1
        ClassTest chatBotTest = new ChatBotTest(targetPath, "ChatBot", 36);
        super.totalScore += 36;
        if (!chatBotTest.run()) {run = false; runFeedback += "ChatBot failed to run\n";} else {runResult.addScore(2);};
        super.allTestResults.add(chatBotTest.getResults());
        if (chatBotTest.getClassInstance() == null) {compilation = false; feedback += "Failed to compile ChatBot Class\n";}

        ClassTest chatBotGenerator = new ChatBotGeneratorTest(targetPath, "ChatBotGenerator", 7);
        super.totalScore += 7;
        if (!chatBotGenerator.run()) {run = false; runFeedback += "ChatBotGenerator failed to run\n";} else {runResult.addScore(2);};;
        super.allTestResults.add(chatBotGenerator.getResults());
        if (chatBotGenerator.getClassInstance() == null) {compilation = false; feedback += "Failed to compile ChatBotGenerator Class\n";}

        ClassTest chatBotPlatform = new ChatBotPlatformTest(targetPath, "ChatBotPlatform", 20);
        super.totalScore += 20;
        if (!chatBotPlatform.run()) {run = false; runFeedback += "ChatBotPlatform failed to run\n";} else {runResult.addScore(2);};;
        super.allTestResults.add(chatBotPlatform.getResults());
        if (chatBotPlatform.getClassInstance() == null) {compilation = false; feedback += "Failed to compile ChatBotPlatform Class\n";}

        ClassTest chatBotSimulation = new ChatBotSimulationTest(targetPath, "ChatBotSimulation", 12);
        super.totalScore += 12;
        if (!chatBotSimulation.run()) {run = false; runFeedback += "ChatBotSimulation failed to run\n";} else {runResult.addScore(2);};;
        super.allTestResults.add(chatBotSimulation.getResults());
        if (chatBotSimulation.getClassInstance() == null) {compilation = false; feedback += "Failed to compile ChatBotSimulation Class\n";}

        // Compiling points
        if (compilation) {
            compilingResult.addScore(5);
        } else {
            compilingResult.setFeedback(feedback);
        }
        super.allTestResults.add(compilingResult);

        // Running points
        if (run) {
            runResult.addScore(2);
        } else {
            runResult.setFeedback(runFeedback);
        }
        super.allTestResults.add(runResult);
    }

    @Override 
    protected void generateResults(Student student) {
        // Generate PDF results for assignment 1
        String header = "THE UNIVERSITY OF THE WEST INDIES, ST AUGUSTINE\n" +
                            "COMP2602: OBJECT-ORIENTED PROGRAMMING I\n" + 
                            "2023/2024 SEMESTER 1\n\n" +
                            "Assignment 1 GRADE SHEET";
        String title = student.getFirstName() + " " + student.getLastName() +
                            "\n" + student.getStudentID() + "\n" + super.getPercent() +"\n";
        PDFGenerator.generatePDF(student.getDestinationPath(), header, title, super.allTestResults);
    }
}
