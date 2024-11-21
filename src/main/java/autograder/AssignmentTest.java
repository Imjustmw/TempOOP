package autograder;

import java.util.ArrayList;
import java.util.List;

import autograder.models.Student;
import autograder.models.TestResult;

// Template class
public abstract class AssignmentTest {
    // Template method
    protected List<TestResult> allTestResults = new ArrayList<>();
    protected int totalScore = 0;

    public final void runTests(Student student) throws Exception {
        runUnitTests(student.getDestinationPath());
        generateResults(student);
    }

    protected String getPercent() {
        double score = 0;
        for (TestResult result : this.allTestResults) {
            score += result.getScore();
        }
        int percent = (int) ((score/this.totalScore) * 100);
        return "" + percent + "%";
    }

    protected abstract void generateResults(Student student);
    protected abstract void runUnitTests(String targetPath);
    
}
