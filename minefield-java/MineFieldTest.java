import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MineFieldTest {

	protected MineField solution;

	@Before
	public void setUp() {
		solution = new MineField();
	}

	@Test(timeout = 2000)
	public void testCase0() {
		String mineLocations = "(0,0)(1,0)(2,0)(3,0)(4,0)";

		String[] expected = new String[] { "M20000000", "M30000000", "M30000000", "M30000000", "M20000000", "110000000",
				"000000000", "000000000", "000000000" };
		String[] actual = solution.getMineField(mineLocations);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase1() {
		String mineLocations = "(0,0)(0,8)(8,0)(8,8)";

		String[] expected = new String[] { "M1000001M", "110000011", "000000000", "000000000", "000000000", "000000000",
				"000000000", "110000011", "M1000001M" };
		String[] actual = solution.getMineField(mineLocations);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase2() {
		String mineLocations = "(3,2)(3,3)(3,4)(4,2)(4,4)(5,2)(5,3)(5,4)(7,4)(6,7)";

		String[] expected = new String[] { "000000000", "000000000", "012321000", "02MMM2000", "03M8M3000", "02MMM2111",
				"0124321M1", "0001M1111", "000111000" };
		String[] actual = solution.getMineField(mineLocations);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test(timeout = 2000)
	public void testCase3() {
		String mineLocations = "";

		String[] expected = new String[] { "000000000", "000000000", "000000000", "000000000", "000000000", "000000000",
				"000000000", "000000000", "000000000" };
		String[] actual = solution.getMineField(mineLocations);

		Assert.assertArrayEquals(expected, actual);
	}

}
