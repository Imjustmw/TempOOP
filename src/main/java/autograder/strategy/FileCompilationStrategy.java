package autograder.strategy;

import java.util.List;

import autograder.models.Student;
import autograder.utils.FileCompiler;

public class FileCompilationStrategy implements GradingStrategy {
    
    @Override
    public Object execute(Object input1, Object input2) throws Exception {
        if (!(input1 instanceof List)) {
            throw new IllegalArgumentException("Input must be a List of Students");
        }

        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) input1;

        for (Student student: students) {
            System.out.println("Compiling files for: " + student.getStudentID());
            boolean success = FileCompiler.compileJavaFiles(student.getDestinationPath());
            if (!success) {
                System.err.println("Compilation failed for: " + student.getStudentID());
            }
        }
        return null;
    }
}