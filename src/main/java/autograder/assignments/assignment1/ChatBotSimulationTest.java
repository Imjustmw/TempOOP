package autograder.assignments.assignment1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import autograder.models.ClassTest;
import autograder.models.TestResult;

public class ChatBotSimulationTest extends ClassTest {

    public ChatBotSimulationTest(String filePath, String className, int totalScore) {
        super(filePath, className, totalScore);
    }

    @Override
    public boolean run() {
        TestResult testResult = new TestResult("ChatBotSimulation");
        String feedback = "";
    
        // Capture console output for validation
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        String simulatedInput = "hey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey\nhey";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        System.setOut(new PrintStream(outputStream));
    
        try {
            // Run the main method of ChatBotSimulation
            Method mainMethod = super.classInstance.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});
    
            // Get console output
            String output = outputStream.toString();
    
            // Step 1: Check for "Hello World!"
            if (output.contains("Hello World!")) {
                testResult.addScore(1);
            } else {
                feedback += "The program does not print 'Hello World!' at the start.\n";
            }
    
            // Step 2: Check for ChatBotPlatform output (list of bots)
            if (output.contains("ChatGPT-3.5")) {  // Adjust the output check to match the bot list format
                testResult.addScore(2); // Score for ChatBotPlatform and bot list output
            } else {
                feedback += "The ChatBotPlatform's bot list was not printed correctly.\n";
            }
    
            // Step 3: Validate the interactions output
            String expectedResponse = "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
            int limtReached = 0;
            // Check the output for the expected interaction message
            for (int i = 0; i < 5; i++) {
                if (output.replaceAll("\\s+", "").contains(expectedResponse.replaceAll("\\s+", ""))) {
                    limtReached++;
                }
            }
    
            if (limtReached == 5) {
                testResult.addScore(4); // Score for correct number of interactions
            } else {
                feedback += "The number of interactions performed is incorrect. Expected 15.\n";
            }
    
            // Step 4: Validate final state - Ensure ChatBot names are printed
            boolean allNamesPrinted = true;
            String[] expectedBotNames = {"ChatGPT-3.5", "LLaMa", "Mistral7B", "Bard", "Claude", "Solar"};  // Adjust based on actual bot names
            for (String botName : expectedBotNames) {
                if (!output.contains(botName)) {
                    allNamesPrinted = false;
                    feedback += "The name of ChatBot " + botName + " was not printed in the final list.\n";
                }
            }
    
            if (allNamesPrinted) {
                testResult.addScore(3); // Score for correctly printing all bot names
            } else {
                feedback += "Final ChatBot list was not printed correctly.\n";
            }

            if (testResult.getScore() == 10) {
                testResult.addScore(2);
            }
    
        } catch (Exception e) {
            feedback += "An exception occurred during testing: " + e.getMessage() + "\n";
        } finally {
            testResult.setFeedback(feedback);
            super.tests.add(testResult);
            System.setIn(originalIn);
            System.setOut(originalOut);  // Restore the original output stream
        }
        return true;
    }
}
