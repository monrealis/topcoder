import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MealPlanTest {

    protected MealPlan solution;

    @Before
    public void setUp() {
        solution = new MealPlan();
    }

    @Test//(timeout = 2000)
    public void testCase0() {
        int[] morningMeal = new int[]{1, 2};
        int[] noonMeal = new int[]{1, 2};
        int[] eveningMeal = new int[]{1};
        int[] nightMeal = new int[]{1};

        long expected = 3L;
        long actual = solution.countDistinct(morningMeal, noonMeal, eveningMeal, nightMeal);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase1() {
        int[] morningMeal = new int[]{1, 2, 3};
        int[] noonMeal = new int[]{4, 5, 6};
        int[] eveningMeal = new int[]{7, 8, 9};
        int[] nightMeal = new int[]{10, 11, 12};

        long expected = 81L;
        long actual = solution.countDistinct(morningMeal, noonMeal, eveningMeal, nightMeal);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase2() {
        int[] morningMeal = new int[]{1, 2, 3};
        int[] noonMeal = new int[]{2, 3, 4};
        int[] eveningMeal = new int[]{3, 4, 5};
        int[] nightMeal = new int[]{4, 5, 6};

        long expected = 55L;
        long actual = solution.countDistinct(morningMeal, noonMeal, eveningMeal, nightMeal);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase3() {
        int[] morningMeal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] noonMeal = new int[]{1, 3, 7, 6, 8, 2, 11, 13, 17};
        int[] eveningMeal = new int[]{1, 4, 5, 9, 11, 12, 20};
        int[] nightMeal = new int[]{1, 4, 12, 18, 19};

        long expected = 1993L;
        long actual = solution.countDistinct(morningMeal, noonMeal, eveningMeal, nightMeal);

        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase4() {
        int[] morningMeal = new int[]{58};
        int[] noonMeal = new int[]{58};
        int[] eveningMeal = new int[]{58};
        int[] nightMeal = new int[]{58};

        long expected = 1L;
        long actual = solution.countDistinct(morningMeal, noonMeal, eveningMeal, nightMeal);

        Assert.assertEquals(expected, actual);
    }

}
