package autograder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import autograder.models.TestResult;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testTestResult()
    {   
        TestResult result = new TestResult("JUnit Test");
        result.addScore(5);
        result.addScore(7);

        assertEquals("The addScore method should return 12", 12, result.getScore());
    }
}
