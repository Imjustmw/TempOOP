package autograder;

import autograder.models.Student;

// Template class
public abstract class AssignmentTest {
    // Template method
    public final void runTests(Student student) throws Exception {
        runUnitTests(student.getDestinationPath());
        generateResults(student);
    }

    protected abstract void generateResults(Student student);
    protected abstract void runUnitTests(String targetPath);
    
}
