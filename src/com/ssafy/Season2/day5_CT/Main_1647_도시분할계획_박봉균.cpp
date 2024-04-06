#include <bits/stdc++.h>
using namespace std;
#define INF 1e9

/*
 * 작성자 : 박봉균
 * 아이디어 : MST 크루스칼 -> 모든 정점에 대하여 최적의 간선으로 연결 보장
 *     ==> 최종으로 추가할 간선(유지비 최대)을 추가 안 한다면? -> 두 그룹으로 나누어진다!
 * 결과 : 21072KB, 376ms
 */

/* <BJ1647_G4 : 도시 분할 계획>
 * <시간제한: 2초>
 * 마을: N개 집, 그 집들을 연결하는 M개 길.
 * 길: 무방향, 각 길마다 유지비 존재.
 * 임의의 두 집 사이에 경로가 항상 존재한다.
 * 두 개의 분리된 마을로 분할하려 한다.
 * 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다
 * 마을에는 집이 하나 이상 있어야 한다.
 * 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다.
 * 길들을 최대한 없애고 유지비 합의 최소를 구하자.
[입력]
1) 집 개수 N, 길 개수 M. (2 <= N <= 1e5, 1 <= M <= 1e6)
M개 줄) 길정보 A, B, C (A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000))
[출력]
없애고 남은 길 유지비의 합의 최솟값
*/

int N, M;
int parent[100004];					// 부모 정점 번호 저장
vector<tuple<int, int, int>> roads; //{유지비, from, to} 저장 벡터

// 루트를 찾는 함수 (Path Compression)
int findRoot(int i)
{
	if (parent[i] == i)
		return i;

	return parent[i] = findRoot(parent[i]);
}

// 루트끼리 합치는 함수
bool doUnion(int a, int b)
{
	int rootA = findRoot(a);
	int rootB = findRoot(b);

	// 같은 트리에 속하면 union X , false
	if (rootA == rootB)
		return false;

	parent[rootB] = rootA;
	return true;
}

int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M; // 집(정점) 개수 N, 간선(길) 개수 M
	// 집이 2개인 경우, 그냥 분할해버리면 유지비 0
	if (N == 2)
	{
		cout << 0 << '\n';
		exit(0);
	}

	// 길 입력
	for (int i = 0; i < M; i++)
	{
		int a, b, c;
		cin >> a >> b >> c;
		roads.push_back({c, a, b});
	}

	// 정점 생성
	for (int i = 0; i < N; i++)
		parent[i] = i;

	// 가중치(유지비) 기준 정렬
	sort(roads.begin(), roads.end());

	int ret = 0; // 유지비의 합
	int cnt = 0; // 추가한 간선
	for (auto road : roads)
	{
		// 같은 트리에 속할 경우, union X
		if (!doUnion(get<1>(road), get<2>(road)))
			continue;

		ret += get<0>(road); // 유지비 갱신(누적)

		// N - 2개 간선이 연결된 경우 종료(두 그룹으로 나누어짐)
		// 전체 연결을 위한 마지막 큰 유지비의 간선을 연결 안 함으로써 최소 유지비의 두 구역으로 분할!
		if (N - 2 == ++cnt)
			break;
	}
	cout << ret << '\n';

	return 0;
}