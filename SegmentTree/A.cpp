#include <stdio.h>
#include <iostream>
#include <vector>
#include<cmath>

using namespace std;

int max(int a, int b) {
	return a > b ? a : b;
}

int min(int a, int b) {
	return a > b ? b : a;
}

int main() {
	int input[8];
	for (int i = 0; i < 8; i++) {
		cin>>input[i];
	}
	vector<long long> a(input[0] + 1);
	a[1] = input[3];
	a[0] = a[1];
	for (int i = 2; i < input[0] + 1; i++) {
		a[0] = (input[1] * a[0] + input[2]) % 65536;
		a[i] = a[0] + a[i - 1];
	}
	long long result = 0;
	int b = input[7];
	vector<int> c(2 * input[4]);
	for (int i = 0; i < c.size(); i++) {
		c[i] = b % input[0];
		if (c[i] < 0) {
			c[i] += 1 << input[0];
		}
		b = (input[5] * b + input[6]) % 1073741824;
		if (b < 0) {
			b += 1073741824;
		}
	}
	a[0] = 0;
	for (int i = 0; i < input[4]; i++) {
		result += a[max(c[2 * i], c[2 * i + 1]) + 1] - a[min(c[2 * i], c[2 * i + 1])];
	}
	cout << result;
	return 0;
}