#include <iostream>
#include <stack>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	stack<int> myStack;
	stack<int> minStack;
	int n; cin >> n;
	int m, k;
	for (int i = 0; i < n; i++) {
		cin >> m;
		if (m == 1) {
			cin >> k;
			myStack.push(k);
			if (minStack.size() == 0 || k < minStack.top()) {
				minStack.push(k);
			}
			else {
				minStack.push(minStack.top());
			}
		}
		else if (m == 2) {
			minStack.pop();
			myStack.pop();
		}
		else {
			cout << minStack.top()<<'\n';
		}

	}
	return 0;
}
