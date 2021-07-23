#include <stdio.h>
#include <vector>
#include <cmath>
using namespace std;

struct LCA_butch
{
	int lca;
	int min_weight;
};

LCA_butch lca_butch_constructor(int lca, int min_weight)
{
	LCA_butch butch;
	butch.lca = lca;
	butch.min_weight = min_weight;
	return butch;
}

void make_tree(int num_nodes, vector<vector<int>> *tree, vector<int> *vertexes)
{
	for(int i = 1; i <= num_nodes; i++)
	{
		(*tree)[(*vertexes)[i]].push_back(i);
	}
}

void precompute_up_and_min_edge (const int node, const int parent, vector<vector<int>> *up,
 	vector<vector<int>> *min_edge, vector<vector<int>> *tree, vector<int> *level, vector<int> *edges)
{
	(*min_edge)[node][0] = (*edges)[node];
	(*up)[node][0] = parent;
	for(int i = 1; i <= 17; i++)
	{
		(*up)[node][i] = (*up)[(*up)[node][i - 1]][i - 1];
		(*min_edge)[node][i] = min((*min_edge)[node][i - 1], (*min_edge)[(*up)[node][i - 1]][i - 1]);
	}
	for(auto v : (*tree)[node])
	{
		if(v != parent)
		{
			(*level)[v] = (*level)[node] + 1;
			precompute_up_and_min_edge(v, node, up, min_edge, tree, level, edges);
		}
	}
}	

LCA_butch LCA (int v1, int v2, vector<int> *level, vector<vector<int>> *up, vector<vector<int>> *min_edge)
{
	int min_weight = pow(10, 7);
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
			min_weight = min(min_weight, (*min_edge)[v1][i]);
			v1 = (*up)[v1][i];
			D -= pow(2, i);
		}
	}

	if (v1 == v2)
	{
	 	return lca_butch_constructor(v1, min_weight);
	}

	for(int i = 17; i >= 0; i --)
	{
		if((*up)[v1][i] != (*up)[v2][i])
		{
			min_weight = min(min_weight, min((*min_edge)[v1][i], (*min_edge)[v2][i]));
			v1 = (*up)[v1][i];
			v2 = (*up)[v2][i];
		}
	}

	min_weight = min(min_weight, min((*min_edge)[v1][0], (*min_edge)[v2][0]));
	return lca_butch_constructor((*up)[v1][0], min_weight);
}

int main()
{
	freopen("minonpath.in", "r", stdin);
	freopen("minonpath.out", "w", stdout);
	int num_nodes, num_queries;
	vector<int> vertexes (200002, 0);
	vector<vector<int>> up (200002, vector<int> (18, 0));
	vector<vector<int>> tree (200002, vector<int>());
	vector<int> level (200002, 0);
	vector<vector<int>> min_edge (200002, vector<int> (18, pow(10, 7)));
	vector<int> edges (200002, pow(10, 7));
	scanf("%d", &num_nodes);

	vertexes[1] = 1;
	for(int i = 2; i <= num_nodes; i++)
	{
		scanf("%d%d", &(vertexes[i]), &(edges[i]));
	}
	
	make_tree(num_nodes, &tree, &vertexes);
	precompute_up_and_min_edge(1, 1, &up, &min_edge, &tree, &level, &edges);

	scanf("%d", &num_queries);

	for(int i = 1; i <= num_queries; i++)
	{
		int f, s;
		scanf("%d%d", &f, &s);
		printf("%d\n", LCA(f, s, &level, &up, &min_edge).min_weight);
	}
	fclose(stdin);
	fclose(stdout);
	return 0;
}
