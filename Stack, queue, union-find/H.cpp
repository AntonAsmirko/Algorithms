#include <iostream>
#include<string>
#include<vector>
#include<map>
using namespace std;

struct Node {
	int exp = 0;
	int val;
	int size = 1;
	int ancestor = val;
	Node(int val) {
		this->val = val;
		this->ancestor = -1;
	}
};

vector<Node> sets;
map<int, vector<int>> my_map;

int find(int n) {
	if (sets[n - 1].ancestor == -1) {
		return n-1;
	}
	else
	{
		int new_ancestor = find(sets[n - 1].ancestor+1);
		sets[n - 1].ancestor = new_ancestor;
		return new_ancestor;
	}
}

void unionn(int x, int y) {
	int repX = find(x);
	int repY = find(y);
	if (repX == repY) return;
	if (sets[repX].size < sets[repY].size) {
		int tmp = repX;
		repX = repY;
		repY = tmp;
	}
	sets[repY].ancestor = repX;
	sets[repX].size += sets[repY].size;
	my_map[repX].insert(my_map[repX].end(), my_map[repY].begin(),
		my_map[repY].end());
}

void add(int u, int v) {
	int uRep = find(u);
	for (auto now : my_map[uRep]) {
		sets[now].exp += v;
	}
}

int	main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		sets.push_back(Node(i + 1));
		my_map[i] = vector<int>{ i };
	}
	int m;
	cin >> m;
	string command;
	for(int i = 0; i < m; i++) {
		cin >> command;
		if (command == "join") {
			int u;
			int v;
			cin >> u >> v;
			u;
			v;
			unionn(u, v);
		}
		if (command == "get") {
			int u;
			cin >> u;
			u--;
			cout << sets[u].exp<<'\n';
		}
		if (command == "add") {
			int u;
			int v;
			cin >> u >> v;	
			add(u, v);
		}
	}
	return 0;
}
