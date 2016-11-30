import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SalaryTest {

    protected Salary solution;

    @Before
    public void setUp() {
        solution = new Salary();
    }

    @Test(timeout = 2000)
    public void testCase0() {
        String[] arrival = new String[]{"08:00:00", "13:00:00", "19:27:32"};
        String[] departure = new String[]{"12:00:00", "17:00:00", "20:48:10"};
        int wage = 1000;

        int expected = 10015;
        int actual = solution.howMuch(arrival, departure, wage);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase1() {
        String[] arrival = new String[]{"01:05:47", "16:48:12"};
        String[] departure = new String[]{"09:27:30", "21:17:59"};
        int wage = 2000;

        int expected = 33920;
        int actual = solution.howMuch(arrival, departure, wage);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase2() {
        String[] arrival = new String[]{"00:00:00"};
        String[] departure = new String[]{"23:59:59"};
        int wage = 10000;

        int expected = 299995;
        int actual = solution.howMuch(arrival, departure, wage);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase3() {
        String[] arrival = new String[]{"10:00:00"};
        String[] departure = new String[]{"18:00:00"};
        int wage = 10000;

        int expected = 80000;
        int actual = solution.howMuch(arrival, departure, wage);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase4() {
        String[] arrival = new String[]{"22:19:46"};
        String[] departure = new String[]{"23:12:46"};
        int wage = 5320;

        int expected = 7049;
        int actual = solution.howMuch(arrival, departure, wage);

        Assert.assertEquals(expected, actual);
    }

}
