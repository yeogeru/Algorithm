#include <iostream>
#include <vector>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-07-16
 */

bool visit[101];

void solve(string &s, int l, int r)
{
    if (l > r)
        return;
    int minIndex = -1;
    int minChar = 93;
    for (int i = l; i <= r; i++)
    {
        if (s[i] < minChar && !visit[i]) {
            minChar = s[i];
            minIndex = i;
        }
    }
    visit[minIndex] = true;
    for (int i = 0; i < s.length(); i++)
    {
        if (visit[i])
            cout << s[i];
    }

    cout << "\n";
    solve(s, minIndex + 1, r);
    solve(s, l, minIndex - 1);
}

int main()
{
    string input;
    cin >> input;
    solve(input, 0, input.length() - 1);
    return 0;
}
