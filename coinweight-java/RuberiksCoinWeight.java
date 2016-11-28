import java.util.Arrays;
import java.util.StringTokenizer;

// Taken from Ruberik's submission and modified 
public class RuberiksCoinWeight implements CoinWeight {
	private boolean[][] cache;
	private boolean[] vals;
	private int[] val;
	private int[] min;
	private int[] max;

	public int possibleValues(int weight, String[] coins) {
		parseInput(coins);
		prepareCacheAndVals(weight);
		numvals(0, weight);
		return countVals();
	}

	private void parseInput(String[] coins) {
		int n = coins.length;
		val = new int[n];
		min = new int[n];
		max = new int[n];
		for (int i = 0; i < coins.length; i++) {
			StringTokenizer st = new StringTokenizer(coins[i]);
			val[i] = Integer.parseInt(st.nextToken());
			min[i] = Integer.parseInt(st.nextToken());
			max[i] = Integer.parseInt(st.nextToken());
		}
	}

	private void prepareCacheAndVals(int weight) {
		cache = new boolean[3000][weight + 1];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], false);
		vals = new boolean[10000];
	}

	private int countVals() {
		int cnt = 0;
		for (int i = 0; i < vals.length; i++)
			if (vals[i])
				cnt++;
		return cnt;
	}

	void numvals(int valueSoFar, int weightLeft) {
		System.out.println(valueSoFar + " " + weightLeft);
		if (weightLeft < 0)
			return;
		if (weightLeft == 0) {
			vals[valueSoFar] = true;
			return;
		}
		if (cache[valueSoFar][weightLeft])
			return;
		for (int i = 0; i < val.length; i++)
			for (int j = min[i]; j <= max[i]; j++)
				numvals(valueSoFar + val[i], weightLeft - j);
		cache[valueSoFar][weightLeft] = true;
	}
}
