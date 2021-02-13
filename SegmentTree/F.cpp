#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<int> log_table;
vector<vector<long long>> sparse_table;
vector<long long> arr;

long long max(long long a, long long b) {
	return a > b ? a : b;
}

long long min(long long a, long long b) {
	return a < b ? a : b;
}

void buildSparseTable() {
	log_table.resize(arr.size() + 1);
	for (int i = 2; i < log_table.size(); i++) {
		log_table[i] = log_table[i / 2] + 1;
	}
	sparse_table.resize(log_table[log_table.size() - 1] + 1);
	sparse_table[0] = arr;
	for (int i = 1; i < sparse_table.size(); i++) {
		sparse_table[i].resize(arr.size());
	}
	for (int i = 1; i < sparse_table.size(); i++) {
		for (int j = 0; j + (1 << i) <= arr.size(); j++) {
			sparse_table[i][j] = min(sparse_table[i - 1][j],
				sparse_table[i - 1][j + (1 << (i - 1))]);
		}
	}
}

long long rmq(int l, int r) {
	l--;
	int log = log_table[r - l];
	return min(sparse_table[log][l], sparse_table[log][r - (1 << log)]);
}

int main() {
	int n, m;
	cin >> n >> m;
	arr.resize(n);
	cin >> arr[0];
	for (int i = 1; i < arr.size(); i++) {
		arr[i] = (23 * arr[i - 1] + 21563) % 16714589;
	}
	buildSparseTable();
	int q1, q2;
	cin >> q1 >> q2;
	long long prev = rmq(min(q1, q2), max(q1, q2));
	for (int i = 1; i < m; i++) {
		q1 = ((17 * q1 + 751 + prev + 2 * (i)) % n) + 1;
		q2 = ((13 * q2 + 593 + prev + 5 * (i)) % n) + 1;
		prev = rmq(min(q1, q2), max(q1, q2));
	}
	cout << q1 << " " << q2 << " " << prev << "\n";
	return 0;
}