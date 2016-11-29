import static java.util.Collections.reverseOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bonuses {
	public int[] getDivision(int[] points) {
		return new Distributor(points).execute();
	}

	private class Distributor {
		private final int[] points;
		private final int[] bonuses;
		private final Map<Integer, List<Integer>> pointsToIndex;
		private int remainder = 100;

		public Distributor(int[] points) {
			this.points = points;
			bonuses = new int[points.length];
			pointsToIndex = createPointsToIndex();
		}

		private Map<Integer, List<Integer>> createPointsToIndex() {
			Map<Integer, List<Integer>> m = new TreeMap<>(reverseOrder());
			for (int i = 0; i < points.length; ++i) {
				int key = points[i];
				if (!m.containsKey(key))
					m.put(key, new ArrayList<>());
				m.get(key).add(i);
			}
			return m;
		}

		public int[] execute() {
			distributeProportionally();
			distributeRemainder();
			return bonuses;
		}

		private void distributeProportionally() {
			int sum = getSum();
			for (int i = 0; i < points.length; ++i) {
				int bonus = points[i] * 100 / sum;
				bonuses[i] = bonus;
				remainder -= bonus;
			}
		}

		private int getSum() {
			int r = 0;
			for (int p : points)
				r += p;
			return r;
		}

		private void distributeRemainder() {
			for (List<Integer> indexes : pointsToIndex.values())
				for (int index : indexes) {
					if (remainder == 0)
						return;
					++bonuses[index];
					--remainder;
				}
		}

	}
}
