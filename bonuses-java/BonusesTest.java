import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BonusesTest {

	protected Bonuses solution;

	@Before
	public void setUp() {
		solution = new Bonuses();
	}

	@Test(timeout = 2000)
	public void testCase0() {
		int[] points = new int[] { 1, 2, 3, 4, 5 };

		int[] expected = new int[] { 6, 13, 20, 27, 34 };
		int[] actual = solution.getDivision(points);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase1() {
		int[] points = new int[] { 5, 5, 5, 5, 5, 5 };

		int[] expected = new int[] { 17, 17, 17, 17, 16, 16 };
		int[] actual = solution.getDivision(points);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase2() {
		int[] points = new int[] { 485, 324, 263, 143, 470, 292, 304, 188, 100, 254, 296, 255, 360, 231, 311, 275, 93,
				463, 115, 366, 197, 470 };

		int[] expected = new int[] { 8, 6, 4, 2, 8, 5, 5, 3, 1, 4, 5, 4, 6, 3, 5, 4, 1, 8, 1, 6, 3, 8 };
		int[] actual = solution.getDivision(points);

		Assert.assertArrayEquals(expected, actual);
	}

}
