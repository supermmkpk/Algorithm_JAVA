#include <bits/stdc++.h>
using namespace std;

/**
 * KMP 풀이
 * @author 박봉균
 * 결과: 11140KB, 24ms
 */

/*
문자열 S와 P가 주어졌을 때, P가 S의 부분 문자열인지 아닌지 알아보자.
[입력]
1) 문자열 S
2)문자열 P (S,P: 길이<= 1e6, 알파벳 소문자)
[출력]
P가 S의 부분 문자열이면 1, 아니면 0
*/

vector<char> S, P;
int pi[1000004], ret;

// 패턴 일치 저장 배열 만드는 함수
void getPi()
{
    int j = 0;
    for (int i = 1; i < P.size(); i++)
    {
        while (j > 0 && P[i] != P[j])
        {
            j = pi[j - 1];
        }
        if (P[i] == P[j])
        {
            pi[i] = ++j;
        }
    }
}

void kmp()
{
    int j = 0; // 패턴 내 일치체크 idx

    // 전체 문자열 돌기
    for (int i = 0; i < S.size(); i++)
    {
        // 맞는 위치가 나올 때까지 j - 1칸의 공통 부분 위치로 이동
        while (j > 0 && S[i] != P[j])
        {
            j = pi[j - 1];
        }
        // 맞는 경우 패턴의 끝까지 확인했으면 정답
        if (S[i] == P[j])
        {
            if (j == P.size() - 1)
            {
                ret = 1;
                break;
                // 매칭하는 부분문자열의 개수 구하는 경우
                // ret++;
                // j = pi[j]; //초기화
            }
            // 아니면 패턴의 다음 문자를 확인
            else
            {
                j++;
            }
        }
    }
}

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string inS, inP;
    cin >> inS >> inP;
    for (char c : inS)
        S.push_back(c);
    for (char c : inP)
        P.push_back(c);

    getPi();
    kmp();

    cout << ret << '\n';

    return 0;
}
