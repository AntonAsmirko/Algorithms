#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <list>

using namespace std;

void countingSort(string &str, vector<int> &p, vector<int> &c) {
    vector<list<int>> countt(27, list<int>());
    for (int i = 0; i < str.size(); i++) countt[str[i] - '`'].push_back(i);
    c.resize(str.size(), -1);
    for (const auto &letter : countt)
        for (auto it: letter) {
            if (p.empty())
                c[it] = 0;
            else if (str[it] == str[p[p.size() - 1]])
                c[it] = c[p[p.size() - 1]];
            else
                c[it] = c[p[p.size() - 1]] + 1;
            p.push_back(it);
        }
}

void digitalSort(vector<int> &p, vector<int> &c, int k) {
    vector<list<int>> countt;
    for (auto i : {(int) pow(2, k - 1), 0}) {
        countt.clear();
        countt.resize(p.size(), list<int>());
        for (auto it : p)
            countt[c[(it + i) % c.size()]].push_back(it);
        p.clear();
        for (const auto &letter : countt)
            for (auto it: letter)
                p.push_back(it);
    }
}

void build_suffix_array(string s, vector<int> &p) {
    s += '`';
    vector<int> c;
    countingSort(s, p, c);
    for (int i = 1; i <= (int) log2(s.length()) + 1; i++) {
        digitalSort(p, c, i);
        vector<int> new_c(p.size(), -1);
        bool are_all_different = true;
        for (int index = 0; index < p.size(); index++) {
            int it = p[index];
            if (index == 0)
                new_c[it] = 0;
            else if (c[it % c.size()] == c[p[index - 1] % c.size()] &&
                     c[(int) (it + pow(2, i - 1)) % c.size()] ==
                     c[(int) (p[index - 1] + pow(2, i - 1)) % c.size()])
                new_c[it] = new_c[p[index - 1]];
            else {
                new_c[it] = new_c[p[index - 1]] + 1;
                are_all_different = false;
            }
        }
        c = new_c;
        if (are_all_different) break;
    }
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    string s;
    vector<int> suffix_array;
    cin >> s;
    build_suffix_array(s, suffix_array);
    for (int i = 1; i < suffix_array.size(); i++)
        cout << suffix_array[i] + 1 << " ";
    cout << "\n";
    for (int i = 0; i < s.size() - 1; i++) {
        int u = 0, v = 0;
        while (suffix_array[i + 1] + v < s.size() && suffix_array[i + 1 + 1] + v < s.length() &&
               s[suffix_array[i + 1] + v] == s[suffix_array[i + 1 + 1] + v]) {
            u++;
            v++;
        }
        cout << u << " ";
    }
    return 0;
}