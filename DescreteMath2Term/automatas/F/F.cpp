#include <stdio.h>
#include <map>
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
using namespace std;

struct butch
{
    vector<vector<vector<int>>> *DFA;
    set<int> passStates;
};

butch make_dfa()
{
    butch DFA;
    int num_pass_states, num_states, num_edges;
    cin >> num_states >> num_edges >> num_pass_states;
    for (int i = 1; i <= num_pass_states; i++)
    {
        int inp;
        cin >> inp;
        DFA.passStates.insert(inp);
    }
    DFA.DFA = new vector<vector<vector<int>>>(num_states, vector<vector<int>>(26));
    for (int i = 1; i <= num_edges; i++)
    {
        int state;
        int nextState;
        char charPath;
        cin >> state >> nextState >> charPath;
        (*(DFA.DFA))[state - 1][charPath - 'a'].push_back(nextState);
    }
    return DFA;
}

bool is_equiv()
{
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen("problem2.out", "w", stdout);
    freopen("problem2.in", "r", stdin);
    butch DFA1 = make_dfa();
    butch DFA2 = make_dfa();
    return 0;
}