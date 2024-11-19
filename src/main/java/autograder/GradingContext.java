package autograder;

import java.util.ArrayList;
import java.util.List;

import autograder.models.Student;
import autograder.strategy.GradingStrategy;

public class GradingContext {
    public final List<GradingStrategy> strategies = new ArrayList<>();

    public void addStrategy(GradingStrategy strategy) {
        strategies.add(strategy);
    }

    public void executeStrategies(String sourcePath, String destinationPath) throws Exception {
        List<Student> students = null;
        for (GradingStrategy strategy: strategies) {
            if (students == null) {
                // First strategy generates the student list
                students = (List<Student>) strategy.execute(sourcePath, destinationPath);
            } else {
                strategy.execute(students, null);
            }
        }
    }
}
