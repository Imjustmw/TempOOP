package autograder;

import autograder.assignments.Assignment1Test;

public class AssignmentTestFactory {
    public static AssignmentTest getAssignmentTest(String assignmentId) {
        switch (assignmentId) { // Scalable to add more assignments
            case "A1":
                return new Assignment1Test();
            default:
                throw new IllegalArgumentException("Invalid assignment ID: " + assignmentId);
        }
    }
}
