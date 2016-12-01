import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BombSweeperTest {

    protected BombSweeper solution;

    @Before
    public void setUp() {
        solution = new BombSweeper();
    }

    public static void assertEquals(double expected, double actual) {
        if (Double.isNaN(expected)) {
            Assert.assertTrue("expected: <NaN> but was: <" + actual + ">", Double.isNaN(actual));
            return;
        }
        double delta = Math.max(1e-9, 1e-9 * Math.abs(expected));
        Assert.assertEquals(expected, actual, delta);
    }

    @Test//(timeout = 2000)
    public void testCase0() {
        String[] board = new String[]{".....", ".....", "..B..", ".....", "....."};

        double expected = 94.11764705882354;
        double actual = solution.winPercentage(board);

        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase1() {
        String[] board = new String[]{"BBBBB", "B...B", "B...B", "B...B", "BBBBB"};

        double expected = 5.882352941176471;
        double actual = solution.winPercentage(board);

        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase2() {
        String[] board = new String[]{".........", ".B..B..B.", ".........", ".........", ".B..B..B.", ".........", ".........", ".B..B..B.", "........."};

        double expected = 0.0;
        double actual = solution.winPercentage(board);

        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase3() {
        String[] board = new String[]{".........................", ".........................", ".........................", "........................."};

        double expected = 100.0;
        double actual = solution.winPercentage(board);

        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase4() {
        String[] board = new String[]{"......B.......B..B...........................B....", "..............B..................BB..B............", "B.B.B.............B.....B..............B..........", "...................B...B....................BB....", "...B.....B.........................B.......B.....B", "B.B.........B.....B.......B..B......B.B...B.BB....", "..B...................BB...............B..........", ".........B...B................B..B................", ".......BB.......B....B................B.....BBB...", ".......BB..........B..............B......BB.......", "...................BB.....................B.......", "...B.B.B......B..............B...B......B.........", "B................B................................", "....B..........B.....B..BB....B...............BB..", "..B....B.....B.............B.....B............B...", "...................B.B........B..B.........B.B....", ".....B.....B......................................", "...........BB......BB...B.B........B...B..........", ".....BBB..........................................", ".B...........B....B...BB......B......B...B.B......", "..................B........BB................B....", "...............................B..B....BBB.B....B.", "..........B.......................................", ".....B..........B....BB......B.B......B......B....", ".....B..................B........B................", "............B.....B..B....BB...B....BB........B...", "..B.................B.........B...................", ".BB..............B................................", "...B....B..................B.................B....", "......B...B......B......................B.B.......", "..............B..................B.......B........", ".....B........BB...B.....B........................", ".......B......B.B..B..........B...........B....B..", "B...B...........B...B...B......B.B...B..B......B..", "....B..B.....B.B.......BB..B............B.B....B..", "B.......B..........B.........B...B.BB......B......", "....B...............................B.............", ".....B.B..........................................", "..........B............B......B.B..B....B.........", "....B...B.......................B.................", "B.................B...........B..B....B...........", "...B.....B........................................", "...B..B......................................BBB..", ".B...B....................................B....B..", "...B...B..........B...B.B.........................", ".....B.............B...BB..........B..BBB.BB......", "....................B.....B.......................", "........B..BB..........B.B....B...........B......B", ".........B.....BB..B.............B....BB..........", "....B..B..............B...B..B..........B........."};

        double expected = 77.67558528428094;
        double actual = solution.winPercentage(board);

        assertEquals(expected, actual);
    }

}
