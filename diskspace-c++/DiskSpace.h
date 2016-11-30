#include <algorithm>
#include <iostream>
#include <iterator>
#include <vector>

using namespace std;

#define FOR(i,s,e) for (int i = int(s); i != int(e); i++)
#define FORIT(i,c) for (typeof((c).begin()) i = (c).begin(); i != (c).end(); i++)
#define ISEQ(c) (c).begin(), (c).end()

class DiskSpace {
public:
	int minDrives(vector<int> used, vector<int> total) {
		sortDescending(total);
		int spaceUsed = getSum(used);
		return getNumberOfDisksNotEmpty(total, spaceUsed);
	}

private:
	void sortDescending(vector<int>& integers) {
		sort(integers.begin(), integers.end());
		reverse(integers.begin(), integers.end());
	}

	int getSum(vector<int> usedSpace) {
		int sum = 0;
		for (unsigned i = 0; i < usedSpace.size(); ++i)
			sum += usedSpace.at(i);
		return sum;
	}

	int getNumberOfDisksNotEmpty(vector<int> sizesOfDisks, int spaceUsed) {
		for (unsigned i = 0; i < sizesOfDisks.size(); ++i) {
			if (spaceUsed == 0)
				return i;
			spaceUsed -= min(spaceUsed, sizesOfDisks.at(0));
		}
		return sizesOfDisks.size();
	}
};
