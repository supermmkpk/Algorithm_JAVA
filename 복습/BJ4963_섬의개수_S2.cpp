#include <bits/stdc++.h>
using namespace std;

const int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
const int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};

int matrix[50][50], visited[50][50];
int H, W;

void dfs(int y, int x)
{
    visited[y][x] = 1;

    for (int d = 0; d < 8; d++)
    {
        int ny = y + dy[d];
        int nx = x + dx[d];

        if (ny < 0 || ny >= H || nx < 0 || nx >= W)
            continue;

        // 방문할 수 있고, 방문한 적 없다면
        if (matrix[ny][nx] == 1 && !visited[ny][nx])
        {
            dfs(ny, nx);
        }
    }
}

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    while (1)
    {
        // 해당 테케에 대하여
        // 초기화!
        memset(visited, 0, sizeof(visited));
        cin >> W >> H;
        if (W == 0 && H == 0)
            break;

        for (int i = 0; i < H; i++)
        {
            for (int j = 0; j < W; j++)
            {
                cin >> matrix[i][j];
            }
        }

        int ret = 0;
        // connected component
        for (int i = 0; i < H; i++)
        {
            for (int j = 0; j < W; j++)
            {
                if (matrix[i][j] == 1 && !visited[i][j])
                {
                    dfs(i, j);
                    ++ret;
                }
            }
        }
        cout << ret << '\n';
    }

    return 0;
}