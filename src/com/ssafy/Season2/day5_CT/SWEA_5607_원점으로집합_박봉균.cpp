#include <bits/stdc++.h>
using namespace std;

/*
 * N개의 격자점이 있다.
 * 이 점들을 몇 번 움직여 모든 점을 원점((0, 0))으로 이동시키고 싶다.
 * 한 번의 움직임은 모든 점을 움직이게 하고,
 * i번째 움직임에서 각 점은 상하좌우로 i만큼의 거리를 반드시 이동해야 한다.
 * 최소 몇 번의 움직임으로 모든 점을 원점에 모을 수 있는가?
 * [입력]
 * 1)테케 수 T.
 * 각 테케] 1) N. (1 ≤ N ≤ 10)
 * N개 줄) i번째 줄에는 xi, yi(-10^9 ≤ xi,yi ≤ 10^9)
 * [출력]
 * #테케번호 <공백> 최소이동횟수(불가: -1)
 */
int T, N;

int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> T;
	for (int t = 1; t <= T; t++)
	{
		cin >> N;

		int x, y;
		cin >> x >> y;
		int dist = abs(x) + abs(y);
		int maxDist = dist;
		int prevDist = dist;
		bool flag = false;

		for (int i = 1; i < N; i++)
		{
			cin >> x >> y;
			dist = abs(x) + abs(y);

			if (prevDist % 2 != dist % 2)
			{
				flag = true;
			}

			maxDist = max(maxDist, dist);
			prevDist = dist;
		}

		cout << "#" << t << " ";
		if (flag)
			cout << "-1\n";
		else
		{
			int sum = 0;
			for (int i = 0;; i++)
			{
				sum += i;
				if (sum >= maxDist && (sum - maxDist) % 2 == 0)
				{
					cout << i << '\n';
					break;
				}
			}
		}
	}

	return 0;
}