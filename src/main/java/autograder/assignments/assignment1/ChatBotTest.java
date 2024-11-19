package autograder.assignments.assignment1;

import autograder.models.ClassTest;
import autograder.models.TestResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;;

// Test ChatBot Class
public class ChatBotTest extends ClassTest {

    public ChatBotTest(String filePath, String clasName, int totalScore) {
        super(filePath, clasName, totalScore);
    }

    @Override
    public void run() {
        try {
            testChatBotConstructor();
            testAttributes();
            testGetters();
            testMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testChatBotConstructor() {
        TestResult constructorTest = new TestResult("ChatBot Constructor");
        String feedback = "";

        try {
            Field chatBotNameField = super.classInstance.getDeclaredField("chatBotName");
            chatBotNameField.setAccessible(true);

            try {
                // Test default Constructor
                Object instance1 = super.classInstance.getDeclaredConstructor().newInstance();
                if (super.assertEquals("Constructor should set chatBotName correctly", "ChatGPT-3.5", chatBotNameField.get(instance1))){
                    constructorTest.addScore(3);
                } else {feedback += "Constructor did not initialize correctly\n";}
            } catch (Exception e) {
                feedback += "Constructor Initialization Failed: " + e.getMessage() + "\n";
            }
            
            try {
                // Test Overloaded Constructor
                Object instance2 = super.classInstance.getDeclaredConstructor(int.class).newInstance(2);
                if (super.assertEquals("Constructor should set chatBotName correctly", "Mistral7B", chatBotNameField.get(instance2))){
                    constructorTest.addScore(3);
                } else {feedback += "Constructor did not initialize correctly given LLMCode\n";}
            } catch(Exception e) {
                feedback += "Constructor Initialization Failed: " + e.getMessage() + "\n";
            }

        } catch(Exception e) {
            feedback += "Constructor Initialization Failed: " + e.getMessage() + "\n";
        }
        constructorTest.setFeedback(feedback);
        super.tests.add(constructorTest);
    }

    private void testAttributes() {
        TestResult attributeTests = new TestResult("ChatBot Attributes");
        String feedback = "";

        try {
            Object instance = super.classInstance.getDeclaredConstructor().newInstance();
            int modifiers;

            try {
                // Test chatBotName
                Field chatBotNameField = super.classInstance.getDeclaredField("chatBotName");
                modifiers = chatBotNameField.getModifiers();
                super.assertEquals("chatBotName should be private", true, Modifier.isPrivate(modifiers));
                chatBotNameField.setAccessible(true);
                if (super.assertEquals("chatBotName should be initialized to default name", "ChatGPT-3.5", chatBotNameField.get(instance))) {
                    attributeTests.addScore(1);
                } else {feedback += "chatBotName did not store correct Name\n";}
            } catch (Exception e) {
                feedback += "chatBotName Test Failed: " + e.getMessage() + "\n";
            }

            try {
                // Test numResponsesGenerated
                Field numResponsesGeneratedField = super.classInstance.getDeclaredField("numResponsesGenerated");
                modifiers = numResponsesGeneratedField.getModifiers();
                super.assertEquals("numResponsesGenerated should be private", true, Modifier.isPrivate(modifiers));
                numResponsesGeneratedField.setAccessible(true);
                if(super.assertEquals("numResponsesGenerated should be initialized to 0", 0, numResponsesGeneratedField.getInt(instance))){
                    attributeTests.addScore(1);
                } else {feedback += "numResponsesGenerated should be initialized to 0\n";}
            } catch (Exception e) {
                feedback += "numResponsesGenerated Failed: " + e.getMessage() + "\n";
            }

            try {
                // Test messageLimit
                Field messageLimitField = super.classInstance.getDeclaredField("messageLimit");
                modifiers = messageLimitField.getModifiers();
                super.assertEquals("messageLimit should be private", true, Modifier.isPrivate(modifiers));
                if (Modifier.isStatic(modifiers)) {
                    attributeTests.addScore(1);
                } else {feedback += "messageLimit should be static\n";}
                if (Modifier.isFinal(modifiers)) {
                    attributeTests.addScore(1);
                } else {feedback += "messageLimit should be final\n";}
                messageLimitField.setAccessible(true);
                if (super.assertEquals("messageLimit should be initialized to 10", 10, messageLimitField.getInt(null))) {
                    attributeTests.addScore(1);
                } else {feedback += "messageLimit should be initialized to 10\n";}
            } catch (Exception e){
                feedback += "messageLimit Test failed: " + e.getMessage() + "\n";
            }
            
            try{
                // Test messageNumber
                Field messageNumberField = super.classInstance.getDeclaredField("messageNumber");
                modifiers = messageNumberField.getModifiers();
                super.assertEquals("messageNumber should be private", true, Modifier.isPrivate(modifiers));
                if (super.assertEquals("messageNumber should be static", true, Modifier.isStatic(modifiers))) {
                    attributeTests.addScore(1);
                } else {feedback += "messageNumber should be static\n";}
                messageNumberField.setAccessible(true);
                if (super.assertEquals("messageNumber should be initialized to 0", 0, messageNumberField.getInt(null))) {
                    attributeTests.addScore(1);
                } else {feedback += "messageNumber should be initialized to 0\n";}
            } catch (Exception e){
                feedback += "messageNumber Test failed: " + e.getMessage() + "\n";
            }
            
        } catch (Exception e) {
            feedback += "An exception occured while testing attributes: " + e.getMessage() + "\n";
        } finally {
            attributeTests.setFeedback(feedback);
            super.tests.add(attributeTests);
        }
    }

    private void testGetters() {
        TestResult getterTests = new TestResult("ChatBot Accessors");
        String feedback = "";

        try {
            Object instance = super.classInstance.getDeclaredConstructor().newInstance();

            try {
                // Test getChatBotName
                Method getChatBotName = super.classInstance.getMethod("getChatBotName");
                if (super.assertEquals("getChatBotName should return default name", "ChatGPT-3.5", getChatBotName.invoke(instance))) {
                    getterTests.addScore(1);
                } else {feedback += "getChatBotName did not return expected name\n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing getChatBotName: " + e.getMessage() + "\n";
            }

            try {
                // Test getNumResponsesGenerated
                Method getNumResponsesGenerated = super.classInstance.getMethod("getNumResponsesGenerated");
                if (super.assertEquals("Default responses generated should be 0", 0, getNumResponsesGenerated.invoke(instance))) {
                    getterTests.addScore(1);
                } else {feedback += "getNumResponsesGenerated did not return expected number of responses\n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing  getNumResponsesGenerated: " + e.getMessage() + "\n";
            }

            try{
                // Test getMessageLimit
                Method getMessageLimit = super.classInstance.getMethod("getMessageLimit");
                super.assertEquals("getMessageLimit should return 10", 10, getMessageLimit.invoke(instance));
            } catch (Exception e){
                feedback += "An exception occurred while testing  getMessageLimit: " + e.getMessage() + "\n";
            }

            try{    
                // Test getTotalNumResponsesGenerated
                Method getTotalNumResponsesGenerated = super.classInstance.getMethod("getTotalNumResponsesGenerated");
                int modifiers = getTotalNumResponsesGenerated.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    getterTests.addScore(1);
                } else {feedback += "getTotalNumResponsesGenerated should be static\n";}
                if (super.assertEquals("getTotalNumResponsesGenerated should return 0", 0, getTotalNumResponsesGenerated.invoke(instance))) {
                    getterTests.addScore(1);
                } else {feedback += "getTotalNumResponsesGenerated \n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing  getTotalNumResponsesGenerated: " + e.getMessage() + "\n";
            }

        } catch (Exception e) {
            feedback += "An exception occured while testing accessors: " + e.getMessage() + "\n";

        } finally {
            getterTests.setFeedback(feedback);
            super.tests.add(getterTests);
        }
    }

    private void testMethods() {
        TestResult methodTests = new TestResult("ChatBot Methods");
        String feedback = "";
        

        try {
            Object instance = super.classInstance.getDeclaredConstructor().newInstance();
            int modifiers;

             // Needed to verify generateResponse
             Method getTotalNumResponsesGenerated = super.classInstance.getMethod("getTotalNumResponsesGenerated");
             Method getChatBotName = super.classInstance.getMethod("getChatBotName");

            try {
                // Test getTotalNumMessagesRemaining
                Method getTotalNumMessagesRemaining = super.classInstance.getMethod("getTotalNumMessagesRemaining");
                modifiers = getTotalNumMessagesRemaining.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    methodTests.addScore(1);
                } else {feedback += "getTotalNumMessagesRemaining should be static\n";}
                if (super.assertEquals("Messages Remaining should return 10", 10, getTotalNumMessagesRemaining.invoke(instance))) {
                    methodTests.addScore(2);
                } else {feedback += "getTotalNumMessagesRemaining did not return correct number of remaining responses\n";}

            } catch (Exception e){
                feedback += "An exception occurred while testing getTotalNumMessagesRemaining: " + e.getMessage() + "\n";
            }

            try{
                // Test limitReached
                Method limitReached = super.classInstance.getMethod("limitReached");
                modifiers = limitReached.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    methodTests.addScore(1);
                } else {feedback += "limitReached should be static\n";}
                if (super.assertEquals("Initial limitReached should return false", false, limitReached.invoke(instance))) {
                    methodTests.addScore(2);
                } else {feedback += "limitReached \n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing limitReached: " + e.getMessage() + "\n";
            }

            try {
                // Test generateResponse 
                Method generateResponse = super.classInstance.getDeclaredMethod("generateResponse");
                modifiers = generateResponse.getModifiers();
                if (Modifier.isPrivate(modifiers)) {
                    methodTests.addScore(1);
                } else {feedback += "generateResponse should be private\n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing generateResponse: " + e.getMessage() + "\n";
            }

            try {
                // Test generateResponse via prompt
                Method prompt = super.classInstance.getMethod("prompt", String.class);
                Field messageNumberField = super.classInstance.getDeclaredField("messageNumber");
                messageNumberField.setAccessible(true);
        
                for (int i = 0; i < 10; i++) {
                    String response = (String) prompt.invoke(instance, "Hello!");
                    if (i == 0) {
                        int numResponses = (int) getTotalNumResponsesGenerated.invoke(instance);
                        String chatBotName = (String) getChatBotName.invoke(instance);
                        String expectedResponse = ("(Message#" + numResponses + ") Response from " + chatBotName).replaceAll("\\s+", "");
                        String ignoreSpaces = response.replaceAll("\\s+", "");
                        if (ignoreSpaces.startsWith(expectedResponse)) {
                            methodTests.addScore(4);
                        } else {feedback += "generateResponse did not return correct String format\n";}

                        if (super.assertEquals("Response should be a String", true, response instanceof String)) {
                            methodTests.addScore(2);
                        } else {feedback += "prompt should return a message when limit is not reached\n";}
                    }
                    
                }

                String limitMessage = (String) prompt.invoke(instance, "Hello!");
        
                // This ignores spaces and cases for leniency
                String expected = "Daily Limit Reached. Wait 24 hours to resume chatbot usage".replaceAll("\\s+", "");
                String actual = limitMessage.replaceAll("\\s+", "");
        
                if (super.assertEquals("Should return limit reached message", expected , actual)) {
                    methodTests.addScore(2);
                } else {feedback += "prompt should return unique message when limit is reached\n";}
            } catch (Exception e){
                feedback += "An exception occurred while testing generateResponse via prompt: " + e.getMessage() + "\n";
            }
            
            try{
                // Test toString method
                Method getNumResponsesGenerated = super.classInstance.getMethod("getNumResponsesGenerated");
                Method toString = super.classInstance.getMethod("toString");
                String response = (String) toString.invoke(instance);
                String has1 = (String) getChatBotName.invoke(instance);
                String has2 = "" +  (int) getNumResponsesGenerated.invoke(instance);
                if (response.contains(has1) && response.contains(has2)) {
                    methodTests.addScore(4);

                } else {feedback += "toString should have the chatBotName and numberOfMessagesUsed\n";}
            }catch (Exception e){
                feedback += "An excepion occurred while testing toString: " + e.getMessage() + "\n";
            }
            
        } catch (Exception e) {
            feedback += "An exception occured while testing methods: " + e.getMessage();

        } finally {
            methodTests.setFeedback(feedback);
            super.tests.add(methodTests);
        }
        
        
    }

}

