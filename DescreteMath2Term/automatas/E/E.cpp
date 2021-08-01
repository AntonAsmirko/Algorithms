#include <stdio.h>
#include <map>
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
#include <bitset>
#include <math.h>
#include <queue>

using namespace std;

typedef unsigned long long ull;

struct Comparer
{
    bool operator()(const bitset<101> &b1, const bitset<101> &b2) const
    {
        return b1.to_ullong() < b2.to_ullong();
    };
};

struct CamparerPair
{
    bool operator()(const pair<bitset<101>, bitset<101>> &b1, const pair<bitset<101>, bitset<101>> &b2) const
    {
        return b1.first.to_ullong() < b2.first.to_ullong();
    };
};

set<bitset<101>> pass_states;
int num_states;
int num_edges;
int num_pass_states;
vector<map<bitset<101>, long, Comparer>> can;
set<bitset<101>, Comparer> term_states;
vector<vector<bitset<101>>> nfa;
map<bitset<101>, vector<bitset<101>>, Comparer> dfa;

void read_input()
{
    cin >> num_states >> num_edges >> num_pass_states;
}

void read_term_states()
{
    for (int i = 1; i <= num_pass_states; i++)
    {
        int inp;
        cin >> inp;
        term_states.insert(bitset<101>(pow(2, inp - 1)));
    }
}

void make_nfa()
{
    nfa = vector<vector<bitset<101>>>(num_states + 1, vector<bitset<101>>(26, bitset<101>(0)));
    read_term_states();
    for (int i = 1; i <= num_edges; i++)
    {
        int state;
        int next_state;
        char char_path;
        cin >> state >> next_state >> char_path;
        nfa[state][char_path - 'a'] |= bitset<101>(pow(2, next_state - 1));
    }
}

void make_dfa()
{
    read_term_states();
    for (int i = 1; i <= num_edges; i++)
    {
        int state;
        int next_state;
        char char_path;
        cin >> state >> next_state >> char_path;
        bitset<101> bit_state(pow(2, state - 1));
        bitset<101> bit_next_state(pow(2, next_state - 1));
        if (dfa.find(bit_state) == dfa.end())
        {
            dfa[bit_state] = vector<bitset<101>>(26, bitset<101>(0));
        }
        //        if (dfa.find(bit_next_state) == dfa.end()) {
        //            dfa[bit_next_state] = vector<bitset<101>>(26, bitset<101>(0));
        //        }
        dfa[bitset<101>(pow(2, state - 1))][char_path - 'a'] = bit_next_state;
    }
}

bool in_cycle(bitset<101> vertex, set<bitset<101>, Comparer> visited, bitset<101> from)
{
    visited.insert(from.to_ullong());
    for (auto child : dfa[from])
    {
        if (visited.find(child) == visited.end())
        {
            if (child.to_ullong() == vertex.to_ullong())
            {
                return true;
            }
            if (in_cycle(vertex, visited, child))
            {
                return true;
            }
        }
    }
    return false;
}

void dfs(vector<pair<bitset<101>, bitset<101>>> *path, bitset<101> vertex, set<bitset<101>, Comparer> used)
{
    for (auto child : dfa[vertex])
    {
        if (used.find(child) == used.end())
        {
            path->push_back(make_pair(child, vertex));
            used.insert(child);
            dfs(path, child, used);
        }
    }
}

bool which_case()
{
    vector<pair<bitset<101>, bitset<101>>> path;
    path.push_back(make_pair(bitset<101>(1), bitset<101>(0)));
    set<bitset<101>, Comparer> used;
    used.insert(bitset<101>(1));
    dfs(&path, bitset<101>(1), used);
    bool was_triminal = false;
    bool was_cycle = false;
    bool was_another_terminal = false;
    for (auto vertex_p : path)
    {
        if (term_states.find(vertex_p.first) != term_states.end())
        {
            if (!was_triminal)
            {
                was_triminal = true;
            }
            else if (was_cycle)
            {
                was_another_terminal = true;
            }
        }
        if (in_cycle(vertex_p.first, set<bitset<101>, Comparer>(), vertex_p.second))
        {
            was_cycle = true;
        }
        if (in_cycle(vertex_p.first, set<bitset<101>, Comparer>(), vertex_p.second) && !was_triminal)
        {
            return false;
        }
        if (was_another_terminal)
        {
            return false;
        }
    }
    return true;
}

void to_dfa()
{
    queue<bitset<101>> q;
    q.push(bitset<101>(1));
    dfa[bitset<101>(1)] = vector<bitset<101>>(26, bitset<101>(0));
    while (q.size() != 0)
    {
        bitset<101> vertex = q.front();
        q.pop();
        for (int c = 0; c < 26; c++)
        {
            bitset<101> new_vertex = bitset<101>(0);
            bool mark_terminal = false;
            for (int i = 0; i < vertex.size(); i++)
                if (vertex[i])
                {
                    if (nfa[i + 1][c].any())
                        new_vertex |= nfa[i + 1][c];
                    for (int k = 0; k < nfa[i + 1][c].size(); k++)
                        if (nfa[i + 1][c][k] &&
                            term_states.find(bitset<101>(pow(2, k))) != term_states.end())
                            mark_terminal = true;
                }
            if (new_vertex.any())
            {
                if (dfa.find(new_vertex) == dfa.end())
                {
                    q.push(new_vertex);
                    dfa[new_vertex] = vector<bitset<101>>(26, bitset<101>(0));
                    if (mark_terminal)
                        term_states.insert(new_vertex);
                }
                dfa[vertex][c] = new_vertex;
            }
        }
    }
}

void fill_can()
{
    can.resize(100000 + 1, map<bitset<101>, long, Comparer>());
    for (auto i : dfa)
    {
        for (int j = 0; j <= 100000; j++)
        {
            can[j][i.first] = 0;
        }
    }
    can[0][bitset<101>(1)] = 1;
    for (int i = 0; i < 100000; i++)
    {
        for (auto u : dfa)
        {
            if (can[i][u.first])
            {
                for (auto v : dfa[u.first])
                {
                    if (v.any())
                    {
                        can[i + 1][v] = (can[i + 1][v] + can[i][u.first]) % 1000000007;
                    }
                }
            }
        }
    }
}

long num_words_size_l(int l, bool is_dfa_constructed)
{
    long res = 0;
    for (auto u : term_states)
    {
        if (!is_dfa_constructed || dfa.find(u) != dfa.end())
        {
            res = (res + can[l][u]) % 1000000007;
        }
    }
    return res;
}

long num_word_dfa_accepts(bool is_dfa_constructed)
{
    if (!which_case())
        return -1;
    long res = 0;
    for (int i = 1; i <= 100000; i++)
    {
        res = (res + num_words_size_l(i, is_dfa_constructed)) % 1000000007;
    }
    return res;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen("problem5.out", "w", stdout);
    freopen("problem5.in", "r", stdin);
    read_input();
    make_dfa();
    fill_can();
    cout << num_word_dfa_accepts(false);
    return 0;
}