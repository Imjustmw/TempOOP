package autograder;

// Template class
public abstract class AssignmentTest {
    // Template method
    public final void runTests(String targetPath) {
        loadClasses(targetPath);
        compileClasses(targetPath);
        runUnitTests();
        runIntegrationTests();
        generateResults();
    }

    protected abstract void loadClasses(String targetPath);
    protected abstract void compileClasses(String targetPath);
    protected abstract void runUnitTests();
    protected abstract void runIntegrationTests();
    protected abstract void generateResults();
}
