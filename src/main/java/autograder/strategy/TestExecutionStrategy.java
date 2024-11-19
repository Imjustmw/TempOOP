package autograder.strategy;

import java.io.File;
import java.util.List;

import autograder.AssignmentTest;
import autograder.AssignmentTestFactory;
import autograder.models.Student;

public class TestExecutionStrategy implements GradingStrategy {
    @Override
    public Object execute(Object input1, Object input2) throws Exception {
        if (!(input1 instanceof List)) {
            throw new IllegalArgumentException("Input must be a List of StudentModel.");
        }

        List<Student> students = (List<Student>) input1;
        for (Student student: students) {
            AssignmentTest assignmentTest = AssignmentTestFactory.getAssignmentTest(student.getAssignmentID());
            if (assignmentTest == null) {
                System.err.println("Invalid assignment ID: " + student.getAssignmentID());
                continue;
            }

            System.out.println("Running tests for: " + student.getStudentID());
            assignmentTest.runTests(student);
        }
        return null;
    }
    
}
