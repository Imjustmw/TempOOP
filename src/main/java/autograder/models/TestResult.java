package autograder.models;

public class TestResult {
    private String testName;
    private int score;
    private String feedback;

    public TestResult(String testName) {
        this.testName = testName;
        this.score = 0;
    }

    public void setTestName(String testName) { this.testName = testName; }

    public void addScore(int score) { this.score += score; }

    public void setFeedback(String feedback) { this.feedback = feedback; }

    public int getScore() { return this.score; }
    public String getFeedback() { return this.feedback; }
    public String getTestName() { return this.testName; }
}
