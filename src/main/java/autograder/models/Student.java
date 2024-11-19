package autograder.models;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String studentID;
    private final String assignmentID;
    private final String destinationPath;

    public Student(String firstName, String lastName, String studentID, String assignmentID, String destinationPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.destinationPath = destinationPath;
    }

    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getStudentID() {return this.studentID;}
    public String getAssignmentID() {return this.assignmentID;}
    public String getDestinationPath() {return this.destinationPath;}

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                "lastName='" + lastName + '\'' +
                "studentID='" + studentID + '\'' +
                ", assignmentID='" + assignmentID + '\'' +
                ", destinationPath='" + destinationPath + '\'' +
                '}';
    }
}
