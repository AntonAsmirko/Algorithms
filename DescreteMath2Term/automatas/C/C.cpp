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
    vector<map<char, int>> DFA;
    vector<bool> passStates;
};

butch makeDFA()
{
    int numStates;
    int numEdges;
    int numPassStates;
    butch DFA;
    cin >> numStates >> numEdges >> numPassStates;
    DFA.passStates = vector<bool>(numPassStates);
    for (int i = 1; i <= numPassStates; i++)
    {
        int inp;
        cin >> inp;
        DFA.passStates[inp - 1] = true;
    }
    DFA.DFA = vector<map<char, int>>(numStates, map<char, int>());
    for (int i = 1; i <= numEdges; i++)
    {
        int state;
        int nextState;
        char charPath;
        cin >> state >> nextState >> charPath;
        DFA.DFA[state - 1][charPath] = nextState;
    }
    return DFA;
}

bool areIsomorfic(int v1, int v2, butch *DFA1, butch *DFA2, bool *visited, int *association)
{
    visited[v1 - 1] = true;
    if (DFA1->passStates[v1 - 1] != DFA2->passStates[v2 - 1])
        return false;
    association[v1 - 1] = v2;
    bool res = true;
    for (auto p : DFA1->DFA[v1 - 1])
    {
        int t1 = DFA1->DFA[v1 - 1][p.first];
        int t2 = DFA2->DFA[v1 - 1][p.first];
        if (DFA1->passStates[t1 - 1] != DFA2->passStates[t2 - 1])
            return false;
        if (visited[t1 - 1])
            res = (res && t2) == association[t1 - 1];
        else
            res = res && areIsomorfic(t1, t2, DFA1, DFA2, visited, association);
    }
    return res;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen("isomorphism.out", "w", stdout);
    freopen("isomorphism.in", "r", stdin);
    butch DFA1 = makeDFA();
    butch DFA2 = makeDFA();
    bool visited[DFA1.DFA.size()];
    int association[DFA1.DFA.size()];
    if (areIsomorfic(1, 1, &DFA1, &DFA2, visited, association))
        cout << "YES";
    else
        cout << "NO";
}