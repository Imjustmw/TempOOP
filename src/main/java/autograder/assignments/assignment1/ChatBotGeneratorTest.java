package autograder.assignments.assignment1;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import autograder.models.ClassTest;
import autograder.models.TestResult;

public class ChatBotGeneratorTest extends ClassTest{

    public ChatBotGeneratorTest(String filePath, String clasName, int totalScore) {
        super(filePath, clasName, totalScore);
    }

    @Override
    public boolean run() {
        try {
            testMethods();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private void testMethods() {
        TestResult methodTests = new TestResult("ChatBotGenerator Methods");
        String feedback = "";
    
        try {
            // Create an instance of ChatBotGenerator
            Object instance = super.classInstance.getConstructor().newInstance();
    
            // Test generateChatBotLLM
            Method generateChatBotLLM = super.classInstance.getMethod("generateChatBotLLM", int.class);
            int modifiers = generateChatBotLLM.getModifiers();
    
            // Test if the method is static
            if (Modifier.isStatic(modifiers)) {
                methodTests.addScore(1);
            } else {
                feedback += "generateChatBotLLM should be static\n";
            }
    
            // Individual test cases for different inputs
            try {
                String response1 = (String) generateChatBotLLM.invoke(instance, 1);
                if ("LLaMa".equals(response1)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 1\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 1: " + e.getMessage() + "\n";
            }
    
            try {
                String response2 = (String) generateChatBotLLM.invoke(instance, 2);
                if ("Mistral7B".equals(response2)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 2\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 2: " + e.getMessage() + "\n";
            }
    
            try {
                String response3 = (String) generateChatBotLLM.invoke(instance, 3);
                if ("Bard".equals(response3)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 3\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 3: " + e.getMessage() + "\n";
            }
    
            try {
                String response4 = (String) generateChatBotLLM.invoke(instance, 4);
                if ("Claude".equals(response4)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 4\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 4: " + e.getMessage() + "\n";
            }
    
            try {
                String response5 = (String) generateChatBotLLM.invoke(instance, 5);
                if ("Solar".equals(response5)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 5\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 5: " + e.getMessage() + "\n";
            }
    
            try {
                String response6 = (String) generateChatBotLLM.invoke(instance, 6);
                if ("ChatGPT-3.5".equals(response6)) {
                    methodTests.addScore(1);
                } else {
                    feedback += "Incorrect LLM for input 6\n";
                }
            } catch (Exception e) {
                feedback += "Error while testing input 6: " + e.getMessage() + "\n";
            }
    
        } catch (Exception e) {
            feedback += "An exception occurred while preparing to test generateChatBotLLM: " + e.getMessage() + "\n";
        } finally {
            methodTests.setFeedback(feedback);
            super.tests.add(methodTests);
        }
    }   
}
