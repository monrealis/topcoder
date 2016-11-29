import java.util.HashSet;
import java.util.Set;

public class BunnyPuzzle {
	public int theCount(int[] bunnies) {
		return new Counter(bunnies).count();
	}

	public class Counter {
		private final int[] bunnies;
		private final Set<Integer> locations = new HashSet<>();

		public Counter(int[] bunnies) {
			this.bunnies = bunnies;
			for (int bunny : bunnies)
				locations.add(bunny);
		}

		private int count() {
			int count = 0;
			for (int i = 0; i < bunnies.length; ++i)
				for (int j = 0; j < bunnies.length; ++j)
					if (isJumpValid(bunnies[i], bunnies[j]))
						++count;
			return count;
		}

		private boolean isJumpValid(int a, int b) {
			int c = 2 * b - a;
			if (locations.contains(c))
				return false;
			if (getNumberOfBunniesBetween(Math.min(a, c), Math.max(a, c)) != 1)
				return false;
			return true;
		}

		private int getNumberOfBunniesBetween(int from, int to) {
			int count = 0;
			for (int i = from + 1; i < to; ++i)
				if (locations.contains(i))
					++count;
			return count;
		}
	}
}
