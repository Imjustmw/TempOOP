package autograder.models;

import java.util.ArrayList;
import java.util.List;

import autograder.utils.ClassLoader;

// Class Test Template

public abstract class ClassTest {
    protected Class<?> classInstance;
    protected List<TestResult> tests;
    private int totalScore;
    private String className;

    public ClassTest(String filePath, String className, int totalScore) {
        this.classInstance = ClassLoader.loadClass(filePath, className);
        this.className = className;
        this.tests = new ArrayList<>();
        this.totalScore = totalScore;
    }

    public Class<?> getClassInstance() {return this.classInstance;}

    public abstract boolean run();

    protected boolean assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return true;
        } else if (expected != null && expected.equals(actual)) {
            return true;
        } else {
            return false;
        }
    }

    public void printResults() {
        int score = 0;
        for (TestResult result: tests) {
           score += result.getScore();
        }
        System.out.println("ChatBot Score: " + score + "/" + this.totalScore + "\nFeedback:");
        for (TestResult result: tests) {
            if (!result.getFeedback().isEmpty())
                System.out.print(result.getTestName() + ": " + result.getFeedback());
        }
    }

    public TestResult getResults() {
        int score = 0;
        String feedback = "";
        for (TestResult result: tests) {
            score += result.getScore();
            if (!result.getFeedback().isEmpty())
                feedback += result.getTestName() + ":\n" + result.getFeedback() + "\n";
        }

        TestResult total = new TestResult(this.className);
        total.setFeedback(feedback);
        total.addScore(score);

        return total;
    }
}
