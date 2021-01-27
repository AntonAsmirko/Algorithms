#include <iostream>
#include <cmath>
#include <vector>

using namespace std;

double euclid(pair<int, int> p1, pair<int, int> p2)
{
    return sqrt(pow(p2.first - p1.first, 2) + pow(p2.second - p1.second, 2));
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int n;
    cin >> n;
    vector<pair<int, int>> points(n);
    for (int i = 0; i < n; i++)
    {
        int x, y;
        cin >> x >> y;
        points[i] = make_pair(x, y);
    }
    vector<bool> used(n, false);
    vector<double> min_edge(n, 2828427.0);
    vector<int> selected_edge(n, -1);
    min_edge[0] = 0.0;
    double weight = 0.0;
    for (int i = 0; i < n; i++)
    {
        int v = -1;
        for (int j = 0; j < n; j++)
        {
            if (!used[j] && (v == -1 || min_edge[j] < min_edge[v]))
                v = j;
        }
        used[v] = true;
        if (selected_edge[v] != -1)
            weight += euclid(points[v], points[selected_edge[v]]);
        for (int to = 0; to < n; to++)
        {
            double dist = euclid(points[v], points[to]);
            if (dist < min_edge[to])
            {
                min_edge[to] = dist;
                selected_edge[to] = v;
            }
        }
    }
    cout << weight;
    return 0;
}