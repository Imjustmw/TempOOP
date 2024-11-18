package autograder;

// Template class
public abstract class AssignmentTest {
    // Template method
    public final void runTests(String targetPath) throws Exception {
        loadClasses(targetPath);
        compileClasses(targetPath);
        runUnitTests(targetPath);
        runIntegrationTests();
        generateResults();
    }

    protected abstract void loadClasses(String targetPath);
    protected abstract void compileClasses(String targetPath);
    protected abstract void runUnitTests(String targetPath);
    protected abstract void runIntegrationTests();
    protected abstract void generateResults();
}
