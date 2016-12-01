import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ModeProbability {
	private int numberOfTries;
	private List<int[]> permutations = new ArrayList<>();
	private List<Integer> mostFrequentNumbers = new ArrayList<>();
	private List<Integer> mostFrequentNumberTimes = new ArrayList<>();
	private List<Double> probabilities = new ArrayList<>();
	private int[] probs;

	public double getProb(int[] probs, int n, int value) {
		this.numberOfTries = n;
		this.probs = probs;
		fillPermutations(new int[] {});
		fillMostFrequentNumbers();
		fillProbabilities();
		return getProbability(value);
	}

	private double getProbability(Integer value) {
		double probability = 0;
		for (int i = 0; i < permutations.size(); ++i)
			if (value.equals(mostFrequentNumbers.get(i)))
				probability += probabilities.get(i) * getMultiplier(permutations.get(i));
		return probability;
	}

	private long getMultiplier(int[] permutation) {
		Map<Integer, Integer> counts = getCounts(permutation);
		int product = 1;
		int remaining = numberOfTries;
		for (int count : counts.values()) {
			product *= c(remaining, count);
			remaining = remaining - count;
		}
		return product;
	}

	private long c(int n, int k) {
		long r = factorial(n) / factorial(k) / factorial(n - k);
		return r;
	}

	private long factorial(int number) {
		long f = 1;
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
		int maxCount = getMax(counts.values());
		List<Integer> mostFrequent = getKeysByValue(counts, maxCount);
		if (mostFrequent.size() == 1)
			return new AbstractMap.SimpleEntry<>(mostFrequent.iterator().next(), maxCount);
		else
			return null;
	}

	private int getMax(Collection<Integer> numbers) {
		int max = numbers.iterator().next();
		for (int count : numbers)
			max = Math.max(count, max);
		return max;
	}

	private List<Integer> getKeysByValue(Map<Integer, Integer> map, int value) {
		List<Integer> r = new ArrayList<>();
		for (Entry<Integer, Integer> entry : map.entrySet())
			if (entry.getValue().equals(value))
				r.add(entry.getKey());
		return r;
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
		if (array.length == numberOfTries)
			permutations.add(array);
		else
			for (int i = 0; i < probs.length; ++i)
				if (!isNewElementIsSmallerThanOthers(array, i))
					fillPermutations(appendToArray(array, i));
	}

	private boolean isNewElementIsSmallerThanOthers(int[] array, int i) {
		if (array.length == 0)
			return false;
		return array[array.length - 1] < i;
	}

	private int[] appendToArray(int[] array, int newElement) {
		int[] r = new int[array.length + 1];
		System.arraycopy(array, 0, r, 0, array.length);
		r[array.length] = newElement;
		return r;
	}
}
