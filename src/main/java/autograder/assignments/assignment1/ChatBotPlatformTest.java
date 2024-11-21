package autograder.assignments.assignment1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import autograder.models.ClassTest;
import autograder.models.TestResult;

public class ChatBotPlatformTest extends ClassTest {

    public ChatBotPlatformTest(String filePath, String clasName, int totalScore) {
        super(filePath, clasName, totalScore);
    }

    @Override
    public boolean run() {
        try {
            testChatBotPlatformConstructor();
            testGetters();
            testMethods();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public void testChatBotPlatformConstructor() {
        TestResult constructorTest = new TestResult("ChatBotPlatform Constructor");
        String feedback = "";

        try {
            // Test default Constructor
            Object instance1 = super.classInstance.getDeclaredConstructor().newInstance();

            Field botsField = super.classInstance.getDeclaredField("bots");
            botsField.setAccessible(true);

            int expected = 0;
            int actual = (int) ((List) botsField.get(instance1)).size() ;
            
            if (super.assertEquals("Constructor should instantiate bots list correctly", expected, actual)){
                constructorTest.addScore(4);
            } else {feedback += "bot Attribute not Initialized properly\n";}

        } catch (Exception e) {
            feedback += "Constructor Initialization Failed: " + e.getMessage() + "\n";
        } finally {
            constructorTest.setFeedback(feedback);
            super.tests.add(constructorTest);
        }
    }

    public void testGetters() {
        // Need to check for getBots
    }

    @SuppressWarnings("rawtypes")
    public void testMethods() {
        TestResult methodTests = new TestResult("ChatBotPlatform Methods");
        String feedback = "";
        
        try {
            Object instance = super.classInstance.getDeclaredConstructor().newInstance();

            try {
                // getChatBotList Method
                Method addChatBot = super.classInstance.getMethod("addChatBot", int.class);
                addChatBot.invoke(instance, 0); // Adds ChatGPT-3.5
                Field botsField = super.classInstance.getDeclaredField("bots");
                botsField.setAccessible(true);
                
                int currentNumBots = ((List) botsField.get(instance)).size();
                Object lastBot = ((List) botsField.get(instance)).get(currentNumBots - 1);
                
                Method prompt = lastBot.getClass().getMethod("prompt", String.class);
                prompt.invoke(lastBot, "Hello"); // Adds a message

                Method getChatBotList = super.classInstance.getMethod("getChatBotList");


                String response = ((String) getChatBotList.invoke(instance)).toLowerCase();
                String has1 = "bot number: " + (currentNumBots - 1);
                String has2 = "chatbot name: chatgpt-3.5";
                String has3 = "number messages used: 1";

                if (response.contains(has1)) {methodTests.addScore(2);}
                else {feedback += "getChatBotList does not have bot number\n";}

                if (response.contains(has2)) {methodTests.addScore(2);}
                else {feedback += "getChatBotList does not have bot name\n";}

                if (response.contains(has3)) {methodTests.addScore(2);}
                else {feedback += "getChatBotList does not have bot usage\n";}

            } catch (Exception e) {
                feedback += "An exception occured while testing getChatBotList: " + e.getMessage() + "\n";
            }

            try {
                // interactWithBot Method
                Method interactWithBot = super.classInstance.getMethod("interactWithBot", int.class, String.class);
                Method addChatBot = super.classInstance.getMethod("addChatBot", int.class);
                addChatBot.invoke(instance, 0); // Adds ChatGPT-3.5

                // Test with bot
                String response = (String) interactWithBot.invoke(instance, 0, "Hello");
                String has1 = "Message#";
                String has2 = "ChatGPT-3.5";

                if (response.contains(has1) && response.contains(has2)) {
                    methodTests.addScore(3);
                } else {feedback += "interactWithBot did not generate correct response";}

                // Test with no bot
                response = (String) interactWithBot.invoke(instance, 10, "Hello");
                if (response.contains("Incorrect") && response.contains("10")) {
                    methodTests.addScore(2);
                } else {feedback += "interactWithBot did not generate correct error response";}

            } catch (Exception e) {
                feedback += "An exception occured while testing interactWithBot: " + e.getMessage() + "\n";
            }

            try {
                // addChatBot Method
                Method addChatBot = super.classInstance.getMethod("addChatBot", int.class);
                Field botsField = super.classInstance.getDeclaredField("bots");
                botsField.setAccessible(true);

                // Get the original size of the bots list
                int originalNumBots = ((List) botsField.get(instance)).size();

                // Invoke addChatBot with LLMcode = 1
                boolean successful = (boolean) addChatBot.invoke(instance, 3);
                if (successful) {
                    // Check if a bot was added
                    int currentNumBots = ((List) botsField.get(instance)).size();
                    Object lastBot = ((List) botsField.get(instance)).get(currentNumBots - 1);
                    if (currentNumBots == originalNumBots + 1) {
                        methodTests.addScore(2);

                        // Check if the name of the last bot matches the argument
                        
                        Method getChatBotName = lastBot.getClass().getMethod("getChatBotName");
                        String botName = (String) getChatBotName.invoke(lastBot);

                        if (botName.equals("Bard")) { // Assuming the name should match the LLM code as a string
                            methodTests.addScore(1);
                        } else {
                            feedback += "The name of the last bot added does not match the argument provided.\n";
                        }
                    } else {
                        feedback += "addChatBot did not add bot to bots list.\n";
                    }

                    // Add messages to reach limit then try adding a bot
                    Method prompt = lastBot.getClass().getMethod("prompt", String.class);
                    for (int i = 0; i < 10; i ++){
                        prompt.invoke(lastBot, "Hello");
                    }
                    // Call method when limit is reached
                    successful = (boolean) addChatBot.invoke(instance, 1);
                    if (!successful) {
                        methodTests.addScore(2);
                    } else {
                        feedback += "addChatBot return true for unexpected reason\n";
                    }

                } else {
                    feedback += "addChatBot returned false for unexpected reason\n";
                }
                
            } catch (Exception e) {
                feedback += "An exception occured while testing addChatBot: " + e.getMessage() + "\n";
            }

        } catch (Exception e) {
            feedback += "An exception occured while testing methods: " + e.getMessage();

        } finally {
            methodTests.setFeedback(feedback);
            super.tests.add(methodTests);
        }
    }
}
