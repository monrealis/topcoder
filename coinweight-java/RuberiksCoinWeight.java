import java.util.Arrays;
import java.util.StringTokenizer;

//Taken from Ruberik's submission
public class RuberiksCoinWeight implements CoinWeight {
	public int possibleValues(int weight, String[] coins) {
		int n = coins.length;
		int[] val = new int[n];
		int[] min = new int[n];
		int[] max = new int[n];

		for (int i = 0; i < coins.length; i++) {
			StringTokenizer st = new StringTokenizer(coins[i]);
			val[i] = Integer.parseInt(st.nextToken());
			min[i] = Integer.parseInt(st.nextToken());
			max[i] = Integer.parseInt(st.nextToken());
		}

		cache = new boolean[3000][weight + 1];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], false);
		vals = new boolean[10000];

		numvals(0, weight, val, min, max);

		int cnt = 0;
		for (int i = 0; i < vals.length; i++) {
			if (vals[i])
				cnt++;
		}
		return cnt;
	}

	boolean[][] cache;
	boolean[] vals;

	void numvals(int valsofar, int wtleft, int[] val, int[] min, int[] max) {
		if (wtleft < 0)
			return;
		if (wtleft == 0) {
			vals[valsofar] = true;
			return;
		}
		if (cache[valsofar][wtleft])
			return;
		for (int i = 0; i < val.length; i++) {
			for (int j = min[i]; j <= max[i]; j++) {
				numvals(valsofar + val[i], wtleft - j, val, min, max);
			}
		}
		cache[valsofar][wtleft] = true;
	}

	// "They have misunderestimated me." --Ruberik

	public int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	public int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	public long mod(long a, long b) {
		return a - a / b * b;
	}

	public long longgcd(long a, long b) { // it's kind of creepy that people can
											// watch me code.
		return (b == 0) ? a : longgcd(b, mod(a, b));
	}

	public long longlcm(long a, long b) { // somebody get my camera back from
											// John.
		return a / longgcd(a, b) * b;
	}
}

// Powered by PopsEdit