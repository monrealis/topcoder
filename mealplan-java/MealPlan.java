import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MealPlan {
	private Set<String> plans;
	private int[][] meals;

	public long countDistinct(int[] meal1, int[] meal2, int[] meal3, int[] meal4) {
		plans = new TreeSet<>();
		meals = new int[][] { meal1, meal2, meal3, meal4 };
		collectPlans();
		return plans.size();
	}

	private void collectPlans() {
		for (int i = 0; i < getMaxNumberOfPlans(); ++i)
			plans.add(getPlan(i));
	}

	private String getPlan(int number) {
		List<Integer> dishes = getDishes(getIndexes(number));
		return sortedCopy(dishes).toString();
	}

	private List<Integer> getIndexes(int number) {
		List<Integer> indexes = new ArrayList<>();
		for (int j = 0; j < meals.length; ++j) {
			int dishes = meals[j].length;
			indexes.add(number % dishes);
			number /= dishes;
		}
		return indexes;
	}

	private int getMaxNumberOfPlans() {
		int n = 1;
		for (int[] meal : meals)
			n *= meal.length;
		return n;
	}

	private List<Integer> getDishes(List<Integer> indexes) {
		List<Integer> r = new ArrayList<>();
		for (int i = 0; i < indexes.size(); ++i)
			r.add(meals[i][indexes.get(i)]);
		return r;
	}

	private List<Integer> sortedCopy(List<Integer> dishes) {
		List<Integer> copy = new ArrayList<>(dishes);
		sort(copy);
		return copy;
	}
}
