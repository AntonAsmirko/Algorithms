#include <stdio.h>
#include <map>
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
using namespace std;

set<int> passStates;
int numStates;
int numEdges;
int numPassStates;
int l;
vector<vector<int>> *DFA;
vector<vector<int>> *can;

void readInput()
{
    cin >> numStates >> numEdges >> numPassStates >> l;
}

void makeDFA()
{
    for (int i = 1; i <= numPassStates; i++)
    {
        int inp;
        cin >> inp;
        passStates.insert(inp);
    }
    can = new vector<vector<int>>(l + 1, vector<int>(numStates, 0));
    DFA = new vector<vector<int>>(numStates + 1, vector<int>(26, -1));
    for (int i = 1; i <= numEdges; i++)
    {
        int state;
        int nextState;
        char charPath;
        cin >> state >> nextState >> charPath;
        (*DFA)[state][charPath - 'a'] = nextState;
    }
}

int accepts()
{
    (*can)[0][1] = 1;
    for (int i = 0; i < l; i++)
    {
        for (int u = 1; u <= numStates; u++)
        {
            if ((*can)[i][u])
            {
                for (auto v : (*DFA)[u])
                {
                    if (v != -1)
                    {
                        (*can)[i + 1][v] += (*can)[i][u] % 1000000007;
                        (*can)[i + 1][v] %= 1000000007;
                    }
                }
            }
        }
    }
    int res = 0;
    for (int u = 1; u <= numStates; u++)
    {
        if ((*can)[l][u - 1] && passStates.find(u) != passStates.end())
        {
            res += (*can)[l][u - 1];
        }
    }
    return res;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen("problem4.out", "w", stdout);
    freopen("problem4.in", "r", stdin);
    readInput();
    makeDFA();
    cout << accepts();
    return 0;
}