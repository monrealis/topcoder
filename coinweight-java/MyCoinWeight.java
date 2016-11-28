import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyCoinWeight implements CoinWeight {
	public int possibleValues(int weight, String[] coins) {
		return new Counter(weight, toCoins(coins)).count();
	}

	private List<Coin> toCoins(String[] coinStrings) {
		List<Coin> coins = new ArrayList<>();
		for (String coin : coinStrings)
			coins.add(toCoin(coin));
		sort(coins);
		return coins;
	}

	private Coin toCoin(String coin) {
		String[] parts = coin.split(" ");
		// Octal value if starts with zero?
		return new Coin(parseInt(parts[0]), parseInt(parts[1]), parseInt(parts[2]));
	}

	private class Counter {
		private final int weight;
		private final List<Coin> coins;
		private final Set<Integer> values = new TreeSet<>();

		public Counter(int weight, List<Coin> coins) {
			this.weight = weight;
			this.coins = coins;
		}

		public int count() {
			checkPermutations(new Coin[] {});
			return values.size();
		}

		private void checkPermutations(Coin[] prefix) {
			if (getSumOfMin(prefix) > weight)
				return;
			int value = getSumOfValues(prefix);
			if (matches(weight, prefix))
				if (!values.contains(value))
					values.add(value);
			for (Coin coin : coins)
				if (isNewCombination(prefix, coin))
					checkPermutations(appendedCopy(prefix, coin));
		}

		private boolean isNewCombination(Coin[] prefix, Coin coin) {
			if (prefix.length == 0)
				return true;
			return prefix[prefix.length - 1].compareTo(coin) <= 0;
		}

		private Coin[] appendedCopy(Coin[] prefix, Coin coin) {
			Coin[] joined = new Coin[prefix.length + 1];
			System.arraycopy(prefix, 0, joined, 0, prefix.length);
			joined[prefix.length] = coin;
			return joined;
		}

		private boolean matches(int weight, Coin[] input) {
			int sumOfMin = getSumOfMin(input);
			int sumOfMax = getSumOfMax(input);
			return weight >= sumOfMin && weight <= sumOfMax;
		}
	}

	private static int getSumOfValues(Coin[] coins) {
		int r = 0;
		for (Coin c : coins)
			r += c.value;
		return r;
	}

	private static int getSumOfMin(Coin[] coins) {
		int r = 0;
		for (Coin c : coins)
			r += c.minWeight;
		return r;
	}

	private static int getSumOfMax(Coin[] coins) {
		int r = 0;
		for (Coin c : coins)
			r += c.maxWeight;
		return r;
	}

	private static class Coin implements Comparable<Coin> {
		public final int value;
		public final int minWeight;
		public final int maxWeight;

		public Coin(int value, int minWeight, int maxWeight) {
			this.value = value;
			this.minWeight = minWeight;
			this.maxWeight = maxWeight;
		}

		@Override
		public int compareTo(Coin other) {
			return new Integer(value).compareTo(other.value);
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
}
