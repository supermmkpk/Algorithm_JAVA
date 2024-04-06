#include <bits/stdc++.h>
using namespace std;
#define INF 1e9

/*
 * 작성자 : 박봉균
 * 아이디어:
     //부분집합 구하고 합? 완탐? 근데 N 크고, 시간이 너무 빠듯하다!
     //DP? - 메모이제이션 ㄴㄴ
     //누적합? 투포인터? ㄱㄱ!
 * 결과: 2412KB, 8ms
 */

/* <BJ1806_G4 : 부분합>
 * <시간제한: 0.5초>
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 있다.
 * 이 수열의 연속된 수들의 부분합 중 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하시오.
[입력]
1) N, S (10 ≤ N < 1e5, 0 < S ≤ 1e8)
2) 수열. (10,000이하 자연수)
[출력]
최소의 길이. (불가능: 0)
*/

int N, S, s[100004];

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> S;

    int given;
    for (int i = 1; i <= N; i++)
    {
        cin >> given;
        s[i] = given + s[i - 1]; // 누적합
    }

    int left = 1, right = 1; // 범위의 왼쪽, 오른쪽
    int ret = INF;           // 길이 최소
    // 주어진 수열내의 범위에 대하여
    while (right <= N && left <= N)
    {
        while (right <= N && s[right] - s[left - 1] < S) // 부분합이 S이상 될 때까지 범위 확장
            right++;

        if (s[right] - s[left - 1] >= S)
        { // 부분합 >= S되면 길이 최소 갱신, 다음 left로 이동
            ret = min(ret, right - left + 1);
            left++;
        }
    }

    cout << (ret == INF ? 0 : ret) << '\n';

    return 0;
}