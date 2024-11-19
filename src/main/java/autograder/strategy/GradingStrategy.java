package autograder.strategy;

public interface GradingStrategy {
    Object execute(Object input1, Object input2) throws Exception;
}
