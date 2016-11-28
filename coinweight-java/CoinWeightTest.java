import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinWeightTest {
	protected CoinWeight solution;

	@Before
	public void setUp() {
		solution = new RuberiksCoinWeight();
	}

	@Test(timeout = 2000)
	public void testCase0() {
		int weight = 30;
		String[] coins = new String[] { "001 10 10" };

		int expected = 1;
		int actual = solution.possibleValues(weight, coins);

		Assert.assertEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase1() {
		int weight = 11;
		String[] coins = new String[] { "25 20 22" };

		int expected = 0;
		int actual = solution.possibleValues(weight, coins);

		Assert.assertEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase2() {
		int weight = 50;
		String[] coins = new String[] { "1 15 20", "3 30 30" };

		int expected = 2;
		int actual = solution.possibleValues(weight, coins);

		Assert.assertEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase3() {
		int weight = 50;
		String[] coins = new String[] { "1 15 20", "2 30 30" };

		int expected = 1;
		int actual = solution.possibleValues(weight, coins);

		Assert.assertEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase4() {
		int weight = 100;
		String[] coins = new String[] { "1 2 31", "2 16 46", "4 31 61", "8 46 76", "16 61 91", "32 76 100", "50 2 31" };

		int expected = 1316;
		int actual = solution.possibleValues(weight, coins);

		Assert.assertEquals(expected, actual);
	}
}
