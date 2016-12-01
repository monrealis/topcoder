import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ModeProbability {
	private int numberOfTries;
	private int[] probs;
	private int value;
	private List<Permutation> permutations = new ArrayList<>();
	private List<Integer> mostFrequentNumbers = new ArrayList<>();
	private List<Double> probabilities = new ArrayList<>();

	public double getProb(int[] probs, int n, int value) {
		prepare(probs, n, value);
		return execute();
	}

	private void prepare(int[] probs, int n, int value) {
		this.numberOfTries = n;
		this.probs = probs;
		this.value = value;
	}

	private double execute() {
		fillPermutations(new int[] {});
		fillMostFrequentNumbers();
		fillProbabilities();
		return getProbability();
	}

	private void fillPermutations(int[] array) {
		if (array.length == numberOfTries)
			permutations.add(new Permutation(array));
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

	private void fillMostFrequentNumbers() {
		for (Permutation p : permutations)
			mostFrequentNumbers.add(p.getSingleMostFrequentNumber());
	}

	private void fillProbabilities() {
		for (Permutation p : permutations)
			probabilities.add(getProbability(p.permutation));
	}

	private double getProbability(int[] permutation) {
		double probability = 1;
		for (int i : permutation)
			probability *= 1.0 * probs[i] / 100;
		return probability;
	}

	private double getProbability() {
		double probability = 0;
		Integer integerValue = new Integer(value);
		for (int i = 0; i < permutations.size(); ++i)
			if (integerValue.equals(mostFrequentNumbers.get(i)))
				probability += probabilities.get(i) * getMultiplier(permutations.get(i));
		return probability;
	}

	private long getMultiplier(Permutation permutation) {
		Map<Integer, Integer> counts = permutation.getCounts();
		int multiplier = 1;
		int remaining = numberOfTries;
		for (int count : counts.values()) {
			multiplier *= c(remaining, count);
			remaining = remaining - count;
		}
		return multiplier;
	}

	private long c(int n, int k) {
		return factorial(n) / factorial(k) / factorial(n - k);
	}

	private long factorial(int number) {
		long factorial = 1;
		for (int i = 1; i <= number; ++i)
			factorial *= i;
		return factorial;
	}
}

class Permutation {
	public final int[] permutation;

	public Permutation(int[] permutation) {
		this.permutation = permutation;
	}

	public Integer getSingleMostFrequentNumber() {
		Map<Integer, Integer> counts = getCounts();
		int maxCount = getMax(counts.values());
		List<Integer> mostFrequent = getKeysByValue(counts, maxCount);
		if (mostFrequent.size() != 1)
			return null;
		return mostFrequent.iterator().next();
	}

	public Map<Integer, Integer> getCounts() {
		Map<Integer, Integer> counts = new TreeMap<>();
		for (int number : permutation) {
			Integer oldCount = counts.get(number);
			int newCount = oldCount != null ? oldCount + 1 : 1;
			counts.put(number, newCount);
		}
		return counts;
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

	@Override
	public int hashCode() {
		return Arrays.hashCode(permutation);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Permutation)
			return Arrays.equals(permutation, ((Permutation) obj).permutation);
		return false;
	}

	@Override
	public String toString() {
		return Arrays.toString(permutation);
	}
}
