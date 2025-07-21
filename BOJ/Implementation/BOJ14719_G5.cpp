#include <iostream>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-07-21
 */

int main()
{
    int n, m;
    cin >> n >> m;

    int total = 0;
    bool map[n][m] = {};
    for (int i = 0; i < m; i++)
    {
        int t;
        cin >> t;
        for(int j = 0 ; j < t ; j++) {
            map[n - 1 - j][i] = true;
        }
    }

    for(int i = n - 1 ; i >= 0 ; i--) {
        int left = -1;
        for(int j = 0 ; j < m ; j++) {
            if(map[i][j]) {
                if(left < 0) {
                    left = j;
                    continue;
                }
                total += j - left - 1;
                left = j;
            }
        }
    }

    cout << total;

    return 0;
}