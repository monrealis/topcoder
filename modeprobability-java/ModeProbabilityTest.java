import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ModeProbabilityTest {

    protected ModeProbability solution;

    @Before
    public void setUp() {
        solution = new ModeProbability();
    }

    public static void assertEquals(double expected, double actual) {
        if (Double.isNaN(expected)) {
            Assert.assertTrue("expected: <NaN> but was: <" + actual + ">", Double.isNaN(actual));
            return;
        }
        double delta = Math.max(1e-9, 1e-9 * Math.abs(expected));
        Assert.assertEquals(expected, actual, delta);
    }

    @Test(timeout = 2000)
    //@Ignore
    public void testCase0() {
        int[] probs = new int[]{50, 50};
        int n = 2;
        int value = 0;

        double expected = 0.25;
        double actual = solution.getProb(probs, n, value);

        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase1() {
        int[] probs = new int[]{50, 50};
        int n = 9;
        int value = 0;

        double expected = 0.5;
        double actual = solution.getProb(probs, n, value);

        assertEquals(expected, actual);
    }

    @Test(timeout = 200000)
    @Ignore
    public void testCase2() {
        int[] probs = new int[]{5, 50, 20, 25};
        int n = 15;
        int value = 1;

        double expected = 0.7947486656372071;
        double actual = solution.getProb(probs, n, value);

        assertEquals(expected, actual);
    }

}
