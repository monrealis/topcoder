import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ModeProbability {
	private int n;
	private List<int[]> permutations = new ArrayList<>();
	private List<Integer> mostFrequentNumbers = new ArrayList<>();
	private List<Double> probabilities = new ArrayList<>();
	private int[] probs;

	public double getProb(int[] probs, int n, int value) {
		this.n = n;
		this.probs = probs;
		fillPermutations(new int[] {});
		fillMostFrequentNumbers();
		fillProbabilities();
		double r = 0;
		for (int i = 0; i < permutations.size(); ++i)
			if (mostFrequentNumbers.get(i) != null)
				if (mostFrequentNumbers.get(i).equals(value))
					r += probabilities.get(i);
		return r;
	}

	private void fillMostFrequentNumbers() {
		for (int[] permutation : permutations)
			mostFrequentNumbers.add(getMostFrequentNumber(permutation));
	}

	private Integer getMostFrequentNumber(int[] permutation) {
		Map<Integer, Integer> counts = new TreeMap<>();
		for (int number : permutation) {
			Integer oldCount = counts.get(number);
			int newCount = oldCount != null ? oldCount + 1 : 1;
			counts.put(number, newCount);
		}
		int maxCount = counts.values().iterator().next();
		for (int count : counts.values())
			maxCount = Math.max(count, maxCount);
		List<Integer> mostFrequent = new ArrayList<>();
		for (Entry<Integer, Integer> entry : counts.entrySet())
			if (entry.getValue().equals(maxCount))
				mostFrequent.add(entry.getKey());
		if (mostFrequent.size() == 1)
			return mostFrequent.iterator().next();
		else
			return null;
	}

	private void fillProbabilities() {
		for (int[] permutation : permutations)
			probabilities.add(getProbability(permutation));
	}

	private double getProbability(int[] permutation) {
		double probability = 1;
		for (int i : permutation)
			probability *= 1.0 * probs[i] / 100;
		return probability;
	}

	private void fillPermutations(int[] array) {
		if (array.length == n)
			permutations.add(array);
		else
			for (int i = 0; i < probs.length; ++i)
				fillPermutations(append(array, i));
	}

	private int[] append(int[] array, int newElement) {
		int[] r = new int[array.length + 1];
		System.arraycopy(array, 0, r, 0, array.length);
		r[array.length] = newElement;
		return r;
	}
}
