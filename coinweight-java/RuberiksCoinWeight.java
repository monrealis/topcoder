import java.util.Arrays;
import java.util.StringTokenizer;

// Taken from Ruberik's submission and modified 
public class RuberiksCoinWeight implements CoinWeight {
	boolean[][] cache;
	boolean[] vals;
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

	void numvals(int valsofar, int wtleft) {
		if (wtleft < 0)
			return;
		if (wtleft == 0) {
			vals[valsofar] = true;
			return;
		}
		if (cache[valsofar][wtleft])
			return;
		for (int i = 0; i < val.length; i++)
			for (int j = min[i]; j <= max[i]; j++)
				numvals(valsofar + val[i], wtleft - j);
		cache[valsofar][wtleft] = true;
	}
}
