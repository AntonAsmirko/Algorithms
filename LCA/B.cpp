#include <stdio.h>
#include <vector>
#include <cmath>
using namespace std;

void make_tree(int num_nodes, vector<vector<int>> *tree, vector<int> *vertexes)
{
	for(int i = 1; i <= num_nodes; i++)
	{
		(*tree)[(*vertexes)[i]].push_back(i);
	}
}

void precompute_up (int node, int parent, vector<vector<int>> *up, vector<vector<int>> *tree, vector<int> *level)
{
	(*up)[node][0] = parent;
	for(int i = 1; i <= 17; i++)
	{
		(*up)[node][i] = (*up)[(*up)[node][i - 1]][i - 1];
	}
	for(auto v : (*tree)[node])
	{
		if(v != parent)
		{
			(*level)[v] = (*level)[node] + 1;
			precompute_up(v, node, up, tree, level);
		}
	}
}	

int LCA (int v1, int v2, vector<int> *level, vector<vector<int>> *up)
{
	if((*level)[v1] < (*level)[v2])
	{
		int tmp = v1;
		v1 = v2;
		v2 = tmp;
	}
	int D = abs((*level)[v1] - (*level)[v2]);
	for(int i = 17; i >= 0; i--)
	{
		if(D >= pow(2, i))
		{
			v1 = (*up)[v1][i];
			D -= pow(2, i);
		}
	}

	if (v1 == v2) return v1;

	for(int i = 17; i >= 0; i --)
	{
		if((*up)[v1][i] != (*up)[v2][i])
		{
			v1 = (*up)[v1][i];
			v2 = (*up)[v2][i];
		}
	}
	return (*up)[v1][0];
}

int main()
{
	int num_nodes, num_queries;
	vector<int> vertexes (200002, 0);
	vector<vector<int>> up (200002, vector<int> (18, 0));
	vector<vector<int>> tree (200002, vector<int>());
	vector<int> level (200002, 0);
	scanf("%d", &num_nodes);

	vertexes[1] = 1;
	for(int i = 2; i <= num_nodes; i++)
	{
		scanf("%d", &(vertexes[i]));
	}
	
	make_tree(num_nodes, &tree, &vertexes);
	precompute_up(1, 1, &up, &tree, &level);

	scanf("%d", &num_queries);

	for(int i = 1; i <= num_queries; i++)
	{
		int f, s;
		scanf("%d%d", &f, &s);
		printf("%d\n", LCA(f, s, &level, &up));
	}
	return 0;
}
