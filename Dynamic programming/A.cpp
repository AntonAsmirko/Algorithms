#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include<string>
#include<vector>
#include<map>
using namespace std;

int	main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n, k;
	cin >> n >> k;
	vector<int> coasts(n);
	for (int i = 1; i < n-1; i++) {
		cin >> coasts[i];
	}
	vector<pair<long, vector<bool>>> dp(n);
	dp[0] = pair<long, vector<bool>>(0, vector<bool>(n));
	for (int i = 1; i < n; i++) {
		long max = LONG_MIN;
		int numb = -1;
		for (int j = i - 1; j >= 0 && j >= i - k; j--){
			if (dp[j].first + coasts[i] > max) {
				max = dp[j].first + coasts[i];
				numb = j;
			}
		}
		vector<bool> tmp = dp[numb].second;
		tmp[i] = true;
		dp[i] = pair<long, vector<bool>>(max, tmp);
	}
	cout << dp[n - 1].first << "\n";
	int countJumps = 0;
	for (auto now : dp[n - 1].second) {
		if (now != 0) {
			countJumps++;
		}
	}
	cout<<countJumps<< "\n";
	cout << 1;
	for (int i = 0; i < n; i++) {
		if (dp[n - 1].second[i] != false) {
			cout <<" "<< i + 1;
		}
	}
	return 0;
}
