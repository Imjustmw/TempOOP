package autograder.assignments.assignment1;

import autograder.models.ClassTest;
import autograder.utils.ClassLoader;

import java.lang.reflect.Method;

// Test ChatBot Class
public class ChatBotTest extends ClassTest {

    public ChatBotTest(String filePath) {
        super(filePath);
    }

    @Override
    protected void run() {
        try {
            testGetChatBotNameMethod();
            testGetNumResponsesGenerated();
            testMessageLimitStaticAttribute();
            testMessageNumberStaticAttribute();
        }
    }

    // Method to test the getChatBotName method
    public void testGetChatBotNameMethod() throws Exception {
        Method getChatBotName = super.classInstance.getMethod("getChatBotName");
        Object instance = super.classInstance.getDeclaredConstructor().newInstance();

        // Default Constructor name is "ChatGPT-3.5"
        String expected = "ChatGPT-3.5";
        String actual = (String) getChatBotName.invoke(instance);

        super.assertEquals("getChatBotName() method should return correct name", expected, actual);
    }

    // Method to test the getNumResponsesGenerated method
    public void testGetNumResponsesGenerated() throws Exception {
        Method getNumResponsesGenerated = super.classInstance.getMethod("getNumResponsesGenerated");
        Object instance = super.classInstance.getDeclaredConstructor().newInstance();

        // Default Num responses is 0
        int expected = 0;
        int actual = (int) getNumResponsesGenerated.invoke(instance);

        super.assertEquals("getNumResponsesGenerated() method should return correct number of responses generated", expected, actual);
    }

    // Method to test the MessageLimit Attribute
    public void testMessageLimitStaticAttribute() throws Exception {
        Field messageLimitField = super.classInstance.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);

        int messageLimit = messageLimitField.getInt(null); // Static field, pass null
        super.assertEquals("messageLimit should be initialized to 10", 10, messageLimit);
    }

    // Method to test the MessageNumber Attribute
    public void testMessageNumberStaticAttribute() throws Exception {
        Field messageNumberField = super.classInstance.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);

        int messageNumber = messageNumberField.getInt(null); // Static field, pass null
        super.assertEquals("messageNumber should be initialized to 0", 0, messageNumber);
    }
    
}
