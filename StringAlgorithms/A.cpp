#include <iostream>
#include <string>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    string str;
    cin >> str;
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int l1, r1, l2, r2;
        cin >> l1 >> r1 >> l2 >> r2;
        l1--;
        l2--;
        r1--;
        r2--;
        if (str.substr(l1, r1 - l1 + 1) == str.substr(l2, r2 - l2 + 1))
        {
            cout << "Yes\n";
        }
        else
        {
            cout << "No\n";
        }
    }
    return 0;
}