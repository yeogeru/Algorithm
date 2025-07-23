#include <iostream>
#include <queue>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Implementation
 * @since : 2025-07-23
 */

struct Wind
{
    int r, c, d;
    Wind(int r, int c, int d) : r(r), c(c), d(d) {}
};

int n, m;

int main()
{
    cin >> n >> m;
    bool result[n][m][4] = {};
    int dtr[] = {-1, 1, 0, 0};
    int dtc[] = {0, 0, -1, 1};
    int map[n][m];
    queue<Wind> que;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 9)
            {
                for (int d = 0; d < 4; d++)
                {
                    que.push(Wind(i, j, d));
                    result[i][j][d] = true;
                }
            }
        }
    }

    while (!que.empty())
    {
        Wind t = que.front();
        que.pop();
        int nr = t.r + dtr[t.d];
        int nc = t.c + dtc[t.d];
        int nd = t.d;
        if (nr < 0 || nr >= n || nc < 0 || nc >= m)
            continue;
        switch (map[nr][nc])
        {
        case 1:
            if (t.d == 2)
                nd = 3;
            if (t.d == 3)
                nd = 2;
            break;
        case 2:
            if (t.d == 0)
                nd = 1;
            if (t.d == 1)
                nd = 0;
            break;
        case 3:
            if (t.d == 0)
                nd = 3;
            if (t.d == 1)
                nd = 2;
            if (t.d == 2)
                nd = 1;
            if (t.d == 3)
                nd = 0;
            break;
        case 4:
            nd = (nd + 2) % 4;
            break;
        }
        if (result[nr][nc][nd])
            continue;
        result[nr][nc][nd] = true;
        que.push(Wind(nr, nc, nd));
    }

    int total = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            for (int d = 0; d < 4; d++)
            {
                if (result[i][j][d])
                {
                    ++total;
                    break;
                }
            }
        }
    }

    cout << total;
}