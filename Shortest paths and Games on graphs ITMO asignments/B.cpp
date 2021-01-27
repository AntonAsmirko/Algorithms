#include <iostream>
#include <vector>
#include <limits>

using namespace std;

#define ll long

int main()
{
    int n, m;
    cin >> n >> m;
    vector<vector<pair<int, ll>>> graph(n, vector<pair<int, ll>>());
    for (int i = 0; i < m; i++)
    {
        int start, end, weight;
        cin >> start >> end >> weight;
        graph[start - 1].push_back({end - 1, weight});
        graph[end - 1].push_back({start - 1, weight});
    }
    vector<int> unchecked_vertexes;
    for (int i = 1; i < n; i++)
        unchecked_vertexes.push_back(i);
    vector<ll> dist(n, numeric_limits<ll>::max());
    dist[0] = 0;
    for (auto p : graph[0])
        dist[p.first] = p.second;
    while (!unchecked_vertexes.empty())
    {
        ll min_dist = numeric_limits<ll>::max();
        int min_dist_index = -1;
        int pos_in_vector = -1;
        for (int i = 0; i < unchecked_vertexes.size(); i++)
        {
            if (dist[unchecked_vertexes[i]] < min_dist)
            {
                min_dist = dist[unchecked_vertexes[i]];
                min_dist_index = unchecked_vertexes[i];
                pos_in_vector = i;
            }
        }
        for (auto p : graph[min_dist_index])
        {
            dist[p.first] = min(dist[p.first], (dist[min_dist_index] + p.second));
        }
        unchecked_vertexes.erase(unchecked_vertexes.begin() + pos_in_vector);
    }
    for (long long i : dist)
        cout << i << " ";
    return 0;
}