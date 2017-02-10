import static java.util.Arrays.sort;

import java.util.Set;
import java.util.TreeSet;

public class MealPlan {
	public long countDistinct(int[] meal1, int[] meal2, int[] meal3, int[] meal4) {
		Set<String> distinct = new TreeSet<>();
		for (int i1 : meal1)
			for (int i2 : meal2)
				for (int i3 : meal3)
					for (int i4 : meal4)
						distinct.add(join(i1, i2, i3, i4));
		return distinct.size();
	}

	private String join(int... numbers) {
		sort(numbers);
		String r = "";
		for (int n : numbers)
			r += n + ",";
		return r;
	}
}
