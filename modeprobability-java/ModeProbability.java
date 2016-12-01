import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ModeProbability {
	private int n;
	private List<int[]> permutations = new ArrayList<>();
	private List<Integer> mostFrequentNumbers = new ArrayList<>();
	private List<Integer> mostFrequentNumberTimes = new ArrayList<>();
	private List<Double> probabilities = new ArrayList<>();
	private int[] probs;

	public double getProb(int[] probs, int n, int value) {
		this.n = n;
		this.probs = probs;
		fillPermutations(new int[] {});
		fillMostFrequentNumbers();
		fillProbabilities();
		//System.out.println(probabilities);

		double sum = 0;
		for (int i = 0; i < permutations.size(); ++i)
			sum += getMultiplier(permutations.get(i)) * probabilities.get(i);
		System.out.println(sum);

		double r = 0;
		for (int i = 0; i < permutations.size(); ++i)
			if (mostFrequentNumbers.get(i) != null)
				if (mostFrequentNumbers.get(i).equals(value))
					r += probabilities.get(i) * getMultiplier(permutations.get(i));
		return r;
	}

	private int getMultiplier(int[] permutation) {
		Map<Integer, Integer> counts = getCounts(permutation);
		int product = 1;
		int remaining = n;
		for (int count : counts.values()) {
			product *= c(remaining, count);
			remaining = remaining - count;
		}
		//System.out.println(Arrays.toString(permutation) + " " + product);
		return product;
	}

	private int c(int n, int k) {
		int r = factorial(n) / factorial(k) / factorial(n - k);
		//System.out.println("C " + n + " " + k + " = " + r);
		return r;
	}

	private int factorial(int number) {
		int f = 1;
		for (int i = 1; i <= number; ++i)
			f *= i;
		return f;
	}

	private void fillMostFrequentNumbers() {
		for (int[] permutation : permutations) {
			Entry<Integer, Integer> entry = getMostFrequentNumber(permutation);
			if (entry != null) {
				mostFrequentNumbers.add(entry.getKey());
				mostFrequentNumberTimes.add(entry.getValue());
			} else {
				mostFrequentNumbers.add(null);
				mostFrequentNumberTimes.add(null);
			}
		}
	}

	private Entry<Integer, Integer> getMostFrequentNumber(int[] permutation) {
		Map<Integer, Integer> counts = getCounts(permutation);
		int maxCount = counts.values().iterator().next();
		for (int count : counts.values())
			maxCount = Math.max(count, maxCount);
		List<Integer> mostFrequent = new ArrayList<>();
		for (Entry<Integer, Integer> entry : counts.entrySet())
			if (entry.getValue().equals(maxCount))
				mostFrequent.add(entry.getKey());
		if (mostFrequent.size() == 1)
			return new AbstractMap.SimpleEntry<>(mostFrequent.iterator().next(), maxCount);
		else
			return null;
	}

	private Map<Integer, Integer> getCounts(int[] permutation) {
		Map<Integer, Integer> counts = new TreeMap<>();
		for (int number : permutation) {
			Integer oldCount = counts.get(number);
			int newCount = oldCount != null ? oldCount + 1 : 1;
			counts.put(number, newCount);
		}
		return counts;
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
			for (int i = 0; i < probs.length; ++i) {
				if (array.length > 0)
					if (array[array.length - 1] < i)
						continue;
				fillPermutations(append(array, i));
			}
	}

	private int[] append(int[] array, int newElement) {
		int[] r = new int[array.length + 1];
		System.arraycopy(array, 0, r, 0, array.length);
		r[array.length] = newElement;
		return r;
	}
}
