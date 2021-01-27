#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <list>
#include <cstring>
#include <algorithm>

using namespace std;

void countingSort(string &str, vector<int> &p, int *c)
{
    vector<list<int>> countt(97, list<int>());
    for (int i = 0; i < str.size(); i++)
        countt[str[i] - 32].push_back(i);
    for (const auto &letter : countt)
        for (auto it : letter)
        {
            if (p.empty())
                c[it] = 0;
            else if (str[it] == str[p[p.size() - 1]])
                c[it] = c[p[p.size() - 1]];
            else
                c[it] = c[p[p.size() - 1]] + 1;
            p.push_back(it);
        }
}

void digitalSort(vector<int> &p, int *c, int k)
{
    vector<list<int>> countt;
    for (auto i : {1 << (k - 1), 0})
    {
        countt.clear();
        countt.resize(p.size(), list<int>());
        for (auto it : p)
            countt[c[(it + i) % p.size()]].push_back(it);
        p.clear();
        for (const auto &letter : countt)
            for (auto it : letter)
                p.push_back(it);
    }
}

void build_suffix_array(string s, vector<int> &p)
{
    //s += char(31);
    int c[s.size()];
    countingSort(s, p, c);
    for (int i = 1; i <= (int)log2(s.length()) + 1; i++)
    {
        sort(p.begin(), p.end(), [&c, &i, size = s.size()](const int &a, const int &b) {
            if (c[a] > c[b])
                return false;
            else if (c[a] < c[b])
                return true;
            else if (c[(a + (1 << (i - 1))) % size] > c[(b + (1 << (i - 1))) % size])
                return false;
            else
                return c[(a + (1 << (i - 1))) % size] < c[(b + (1 << (i - 1))) % size];
        });
        //digitalSort(p, c, i);
        int new_c[p.size()];
        bool are_all_different = true;
        for (int index = 0; index < p.size(); index++)
        {
            int it = p[index];
            if (index == 0)
                new_c[it] = 0;
            else if (c[it % p.size()] == c[p[index - 1] % p.size()] &&
                     c[(int)(it + (1 << (i - 1))) % p.size()] ==
                         c[(int)(p[index - 1] + (1 << (i - 1))) % p.size()])
                new_c[it] = new_c[p[index - 1]];
            else
            {
                new_c[it] = new_c[p[index - 1]] + 1;
                are_all_different = false;
            }
        }
        memcpy(c, new_c, p.size() * sizeof(int));
        if (are_all_different)
            break;
    }
    for (int i = 1; i < p.size(); i++)
    {
        if (c[p[i]] == c[p[i - 1]] && (c[(p[i] + (1 << (int)log2(s.length()))) % p.size()] ==
                                       c[(p[i - 1] + (1 << (int)log2(s.length()))) % p.size()]))
            p[i] = p[i - 1];
    }
}

void lcp_arr(const string &str, vector<int> &suffix_array, int *lcp)
{
    int n = str.size() + 1;
    int pos[n];
    for (int i = 0; i < n; i++)
        pos[suffix_array[i]] = i;
    int k = 0;
    for (int i = 0; i < n; i++)
    {
        if (k > 0)
            k--;
        if (pos[i] == n - 1)
        {
            lcp[n - 1] = -1;
            k = 0;
            continue;
        }
        else
        {
            int j = suffix_array[pos[i] + 1];
            while (i + k < n && j + k < n && str[i + k] == str[j + k])
                k++;
            lcp[pos[i]] = k;
        }
    }
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    string s;
    vector<int> suffix_array;
    cin >> s;
    int num;
    cin >> num;
    build_suffix_array(s, suffix_array);
    auto end = unique(suffix_array.begin(), suffix_array.end());
    suffix_array.resize(end - suffix_array.begin());
    if (suffix_array.size() < num)
    {
        cout << "IMPOSSIBLE";
        return 0;
    }
    for (int i = suffix_array[num - 1], j = 0; j < s.length(); j++, i++)
    {
        char ch = s[i % s.length()];
        cout << ch;
    }
    return 0;
}