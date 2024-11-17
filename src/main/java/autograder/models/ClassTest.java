package autograder.models;

import autograder.utils.ClassLoader;

// Class Test Template

public abstract class ClassTest {
    protected Class<?> classInstance;

    public ClassTest(String filePath) {
        this.classInstance = ClassLoader.loadClass(filePath, "ChatBot");
    }

    protected abstract void run();
    protected boolean assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return true;
        } else if (expected != null && expected.equals(actual)) {
            return true;
        } else {
            return false;
        }
    }
}
