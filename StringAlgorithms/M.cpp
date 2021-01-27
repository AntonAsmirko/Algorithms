#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <list>
#include <cstring>
#include <algorithm>

using namespace std;

void countingSort(string &str, vector<pair<int, int>> &p, vector<int> &c, int k)
{
    char delimiter = 'a' - k;
    vector<list<pair<int, int>>> countt(26 + k, list<pair<int, int>>());
    int classes_count = 0;
    for (int i = 0; i < str.size(); i++)
    {
        countt.at(str[i] - delimiter).push_back(make_pair(i, classes_count));
        if (str[i] < 'a')
            classes_count++;
    }
    for (const auto &letter : countt)
        for (auto it : letter)
        {
            if (p.empty())
                c.at(it.first) = 0;
            else if (str[it.first] == str[p.at(p.size() - 1).first])
                c.at(it.first) = c.at(p.at(p.size() - 1).first);
            else
                c.at(it.first) = c.at(p.at(p.size() - 1).first) + 1;
            p.push_back(it);
        }
}

void build_suffix_array(string s, vector<pair<int, int>> &p, int k)
{
    vector<int> c(s.size());
    countingSort(s, p, c, k);
    for (int i = 1; i <= (int)log2(s.length()) + 1; i++)
    {
        sort(p.begin(), p.end(), [&c, &i, size = s.size()](const pair<int, int> &a, const pair<int, int> &b) {
            if (c.at(a.first) > c.at(b.first))
                return false;
            else if (c.at(a.first) < c.at(b.first))
                return true;
            else if (c.at((a.first + (1 << (i - 1))) % size) > c.at((b.first + (1 << (i - 1))) % size))
                return false;
            else
                return c.at((a.first + (1 << (i - 1))) % size) < c.at((b.first + (1 << (i - 1))) % size);
        });
        vector<int> new_c(p.size());
        bool are_all_different = true;
        for (int index = 0; index < p.size(); index++)
        {
            int it = p.at(index).first;
            if (index == 0)
                new_c.at(it) = 0;
            else if (c.at(it % p.size()) == c.at(p.at(index - 1).first % p.size()) &&
                     c.at((int)(it + (1 << (i - 1))) % p.size()) ==
                         c.at((int)(p.at(index - 1).first + (1 << (i - 1))) % p.size()))
                new_c.at(it) = new_c.at(p.at(index - 1).first);
            else
            {
                new_c.at(it) = new_c.at(p.at(index - 1).first) + 1;
                are_all_different = false;
            }
        }
        c.swap(new_c);
        if (are_all_different)
            break;
    }
}

void lcp_arr(const string &str, vector<pair<int, int>> &suffix_array, vector<int> &lcp)
{
    int n = str.size();
    vector<int> pos(n);
    for (int i = 0; i < n; i++)
        pos.at(suffix_array.at(i).first) = i;
    int k = 0;
    for (int i = 0; i < n; i++)
    {
        if (k > 0)
            k--;
        if (pos.at(i) == n - 1)
        {
            lcp.at(n - 1) = -1;
            k = 0;
            continue;
        }
        else
        {
            int j = suffix_array.at(pos.at(i) + 1).first;
            while (i + k < n && j + k < n && str[i + k] == str[j + k])
                k++;
            lcp.at(pos.at(i)) = k;
        }
    }
}

void buildSparseTable(vector<int> &log_table, vector<vector<int>> &sparse_table, vector<int> &arr)
{
    log_table.resize(arr.size() + 1);
    for (int i = 2; i < log_table.size(); i++)
    {
        log_table[i] = log_table[i / 2] + 1;
    }
    sparse_table.resize(log_table[log_table.size() - 1] + 1);
    sparse_table[0] = arr;
    for (int i = 1; i < sparse_table.size(); i++)
    {
        sparse_table[i].resize(arr.size());
    }
    for (int i = 1; i < sparse_table.size(); i++)
    {
        for (int j = 0; j + (1 << i) <= arr.size(); j++)
        {
            sparse_table[i][j] = min(sparse_table[i - 1][j],
                                     sparse_table[i - 1][j + (1 << (i - 1))]);
        }
    }
}

long long rmq(int l, int r, vector<int> &log_table, vector<vector<int>> &sparse_table)
{
    int log = log_table[r - l];
    return min(sparse_table[log][l], sparse_table[log][r - (1 << log)]);
}

bool contains_zero(vector<int> &arr)
{
    bool ans = false;
    for (auto i : arr)
        if (i == 0)
        {
            ans = true;
            break;
        }
    return ans;
}

bool contains_delimiter(int p_index, int len, vector<int> &delimiters_positions)
{
    bool ans = false;
    for (auto delimiter : delimiters_positions)
        if (delimiter >= p_index && delimiter < p_index + len)
        {
            ans = true;
            break;
        }
    return ans;
}

pair<int, int> sliding_window(vector<int> lcp, int k, vector<pair<int, int>> &suffix_array,
                              vector<int> &delimiters_positions)
{
    vector<int> log_table;
    vector<vector<int>> sparse_table;
    buildSparseTable(log_table, sparse_table, lcp);
    int l = 0, r = 0;
    int substr_len = 0;
    int p_index = -1;
    vector<int> classes_count(k, 0);
    classes_count[suffix_array.at(r).second]++;
    while (r < suffix_array.size())
    {
        while (!contains_zero(classes_count))
        {
            int max_on_segment = rmq(l, r, log_table, sparse_table);
            if (max_on_segment > substr_len && !contains_delimiter(suffix_array[l].first, max_on_segment, delimiters_positions))
            {
                substr_len = max_on_segment;
                p_index = suffix_array.at(l).first;
            }
            classes_count[suffix_array.at(l).second]--;
            l++;
        }
        r++;
        if (r < suffix_array.size())
            classes_count[suffix_array.at(r).second]++;
    }
    return make_pair(p_index, substr_len);
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    string s;
    vector<pair<int, int>> suffix_array;
    int k = 2;
    char delimiter = 'a' - k;
    vector<int> delimiters_positions;
    for (int i = 0; i < k; i++)
    {
        string inp;
        cin >> inp;
        s += inp;
        s += delimiter++;
        delimiters_positions.push_back(s.size() - 1);
    }
    vector<int> lcp(s.size() + 1);
    build_suffix_array(s, suffix_array, k);
    lcp_arr(s, suffix_array, lcp);
    pair<int, int> result = sliding_window(lcp, k, suffix_array, delimiters_positions);
    for (int i = result.first, j = 0; j < result.second; i++, j++)
        cout << s[i];
    return 0;
}
