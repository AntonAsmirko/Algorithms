#include <iostream>
#include <string>
#include <utility>
#include <vector>
#include <queue>
#include <map>

using namespace std;

#define ll long long

struct vertex
{
    vector<ll> next = vector<ll>(26, -1);
    bool leaf = false;
    ll p = 0;
    char pch = '-';
    ll link = -1;
    ll up = -1;
    vector<ll> links_from;
    ll count_ = 0;
    vector<ll> go = vector<ll>(26, -1);
    string str;

    vertex(ll link_, ll p_, char pch_) : link(link_), p(p_), pch(pch_) {}

    vertex() {}
};

vector<vertex> vertexes;
ll size_ = 1;
vector<ll> str_to_term;

void add_str(string str, ll index)
{
    ll v = 0;
    for (int i = 0; i < str.size(); i++)
    {
        char c = str[i];
        if (vertexes[v].next[c - 'a'] == -1)
        {
            vertexes.emplace_back(-1, v, c);
            vertexes[v].next[c - 'a'] = size_;
            vertexes[v].go[c - 'a'] = size_;
            size_++;
        }
        v = vertexes[v].next[c - 'a'];
    }
    vertexes[v].leaf = true;
    str_to_term[index] = v;
    vertexes[v].str = str;
}

ll go(ll v, char c);

ll get_link(ll v)
{
    if (vertexes[v].link == -1)
    {
        if (v == 0 || vertexes[v].p == 0)
        {
            vertexes[v].link = 0;
            vertexes[0].links_from.push_back(v);
        }
        else
        {
            ll to = go(get_link(vertexes[v].p), vertexes[v].pch);
            vertexes[v].link = to;
            vertexes[to].links_from.push_back(v);
        }
    }
    return vertexes[v].link;
}

ll go(ll v, char c)
{
    if (vertexes[v].go[c - 'a'] == -1)
    {
        if (vertexes[v].next[c - 'a'] != -1)
        {
            vertexes[v].go[c - 'a'] = vertexes[v].next[c - 'a'];
        }
        else
        {
            vertexes[v].go[c - 'a'] = (v == 0 ? 0 : go(get_link(v), c));
        }
    }
    return vertexes[v].go[c - 'a'];
}

ll get_up(ll v)
{
    if (vertexes[v].up == -1)
    {
        ll suff_link = get_link(v);
        if (vertexes[suff_link].leaf)
        {
            vertexes[v].up = suff_link;
        }
        else if (suff_link == -1)
        {
            vertexes[v].up = 0;
        }
        else
        {
            vertexes[v].up = get_up(suff_link);
        }
    }
    return vertexes[v].up;
}

void build_suffix_links()
{
    vector<bool> discovered(vertexes.size(), false);
    discovered[0] = true;
    queue<ll> q;
    q.push(0);
    while (!q.empty())
    {
        ll v = q.front();
        q.pop();
        get_link(v);
        for (ll next : vertexes[v].next)
        {
            if (next != -1 && !discovered[next])
            {
                discovered[next] = true;
                q.push(next);
            }
        }
    }
}

void collect_count(map<string, ll> &collector, vector<bool> &visited, ll v, vector<ll> &q)
{
    visited[v] = true;
    if (vertexes[v].leaf)
    {
        q.push_back(vertexes[v].count_);
    }
    for (ll link_from : vertexes[v].links_from)
    {
        if (!visited[link_from])
        {
            collect_count(collector, visited, link_from, q);
        }
    }
    if (vertexes[v].leaf)
    {
        ll q_back = q.back();
        collector[vertexes[v].str] = q_back;
        q.pop_back();
        if (!q.empty())
        {
            q.back() += q_back;
        }
    }
    else if (!q.empty())
    {
        q.back() += vertexes[v].count_;
    }
}

void num_occurances(string &str, vector<string> &strings, map<string, ll> &collector)
{
    ll cur_state = 0;
    vertexes[cur_state].count_++;
    for (char ch : str)
    {
        cur_state = go(cur_state, ch);
        if (cur_state == -1)
            cur_state = 0;
        vertexes[cur_state].count_++;
    }
    vector<bool> visited(vertexes.size(), false);
    vector<ll> q;
    collect_count(collector, visited, 0, q);
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int n;
    cin >> n;
    vector<string> strings(n);
    for (int i = 0; i < n; i++)
        cin >> strings[i];
    string t;
    cin >> t;
    vertexes.emplace_back();
    str_to_term.resize(n, -1);
    for (ll i = 0; i < n; i++)
        add_str(strings[i], i);
    build_suffix_links();
    map<string, ll> collector;
    num_occurances(t, strings, collector);
    for (auto it : strings)
        cout << collector[it] << "\n";
    return 0;
}