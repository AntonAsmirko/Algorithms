#include <iostream>
#include <string>
#include <utility>
#include <vector>
#include <queue>
#include <unordered_map>

using namespace std;

#define ll int

struct vertex
{
    vector<ll> next = vector<ll>(26, -1);
    bool leaf = false;
    ll p = 0;
    char pch = '-';
    ll link = -1;
    vector<ll> links_from;
    vector<ll> go = vector<ll>(26, -1);
    vector<ll> accessible_terminals;
    bool checked = false;
    string str = "s";
    pair<ll, ll> if_term = make_pair(-1, -1);

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

void compute_accessible_terminals(ll v)
{
    if (vertexes[v].checked)
        return;
    ll suffix_link = vertexes[v].link;
    while (suffix_link > 0)
    {
        if (vertexes[suffix_link].leaf)
        {
            vertexes[v].accessible_terminals.push_back(suffix_link);
            compute_accessible_terminals(suffix_link);
            for (ll terminal : vertexes[suffix_link].accessible_terminals)
            {
                vertexes[v].accessible_terminals.push_back(terminal);
            }
            break;
        }
        suffix_link = vertexes[suffix_link].link;
    }
    vertexes[v].checked = true;
}

void num_occurances(string &str, vector<string> &strings)
{
    ll cur_state = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        cur_state = go(cur_state, ch);
        if (cur_state == -1)
            cur_state = 0;
        compute_accessible_terminals(cur_state);
        if (vertexes[cur_state].leaf)
        {
            if (vertexes[cur_state].if_term.first == -1)
            {
                vertexes[cur_state].if_term.first = i - vertexes[cur_state].str.size() + 1;
            }
            vertexes[cur_state].if_term.second = max(vertexes[cur_state].if_term.second,
                                                     (ll)(i - vertexes[cur_state].str.size() + 1));
        }
        for (ll terminal : vertexes[cur_state].accessible_terminals)
        {
            if (vertexes[terminal].if_term.first == -1)
            {
                vertexes[terminal].if_term.first = i - vertexes[terminal].str.size() + 1;
            }
            vertexes[terminal].if_term.second = max(vertexes[terminal].if_term.second,
                                                    (ll)(i - vertexes[terminal].str.size() + 1));
        }
    }
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
    num_occurances(t, strings);
    for (int i = 0; i < strings.size(); i++)
        cout << vertexes[str_to_term[i]].if_term.first << " " << vertexes[str_to_term[i]].if_term.second << "\n";
    return 0;
}