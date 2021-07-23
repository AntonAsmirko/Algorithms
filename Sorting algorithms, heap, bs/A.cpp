typedef long long ll;
#define forn(i, a, b) for(int i = a; i<b;i++)
#define _CRT_SECURE_NO_DEPRECATE
#define _CRT_SECURE_NO_VARNINGS
#include <stdio.h>
#include <iostream>
#include<algorithm>
#include<vector>
#include<fstream>
#include<string>
#include<stack>
#include<set>
#include<map>

#include <algorithm>
#include <cassert>
#include <fstream>
#include <vector>
#include<iostream>
using namespace std;

long long countt = 0;

vector<int> merge(vector<int> A, vector<int> B) {
	int size = A.size() + B.size();
	vector<int> arr(size);
	int AIter = 0;
	int BIter = 0;
	int i = 0;
	int num_B = 0;
	while (AIter < A.size() && BIter < B.size()) {
		if (A[AIter] <= B[BIter])
		{
			countt += num_B;
			arr[i] = A[AIter];
			AIter++;
			i++;
		}
		else
		{
			num_B++;
			arr[i] = B[BIter];
			BIter++;
			i++;
		}
	}
	if (AIter != A.size())
	{
		for (; AIter < A.size(); AIter++, i++) {
			arr[i] = A[AIter];
			countt += num_B;
		}
	}
	if (BIter != B.size())
	{
		for (; BIter < B.size(); BIter++, i++)
			arr[i] = B[BIter];
	}
	return arr;
}


vector<int> mergeSort(vector<int> A) {
	if (A.size() == 1) return A;
	int m = A.size() / 2;
	vector<int> first(m);
	for (int i = 0; i < m; i++)
	{
		first[i] = A[i];
	}
	vector<int> second(A.size() - m);
	for (int i = 0; i < A.size() - m; i++)
		second[i] = A[m + i];
	return merge(mergeSort(first), mergeSort(second));
}

int main() {
	/*ifstream cin;
	ofstream cout;
	cin.open("input.txt");
	cout.open("output.txt");*/
	int N; cin >> N;
	vector<int> arr(N);
	for (int i = 0; i < N; i++)
		cin >> arr[i];
	vector<int> sorted = mergeSort(arr);
	for (auto now : sorted) cout << now << " ";
	//cin.close();
	//cout.close();
	return 0;
}