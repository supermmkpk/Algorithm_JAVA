package com.ssafy._1일1알골.algo_0204;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15900 [S1] : 나무 탈출 
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72931331"></a>
 * 
 * 아이디어: 총 이동 횟수 홀짝 여부(홀수: Yes, 짝수: No), 인접리스트, dfs
 * 결과: 228472KB, 1576ms
 */

/* <문제 요약>
 * '나무 탈출' 은 N개의 정점이 있는 트리 모양으로 생긴 게임판과 몇 개의 게임말로 이루어진다.
 *  트리의 각 정점에는 1 ~ N번의 번호가 있다. 1번 정점: '루트 노드', 자식이 없는 노드는 '리프 노드'.
 *  두 사람이 번갈아 가면서 게임판에 놓여있는 게임말을 움직인다. 
 *  1. 모든 리프 노드에 게임말이 하나씩 놓여있는 채로 시작한다. 
 *  2. 차례가 오면, 그 사람은 현재 존재하는 게임말 중 아무거나 하나를 골라 그 말이 놓여있던 노드의 부모 노드로 옮긴다. (한 노드에 여러 개의 게임말이 놓이게 될 수도 있다)
 *  3. 게임말이 루트 노드에 도착했다면 그 게임말을 즉시 제거한다. 모든 과정을 마치면 다음 사람에게 차례를 넘긴다. 
 *  이런 식으로 계속 진행하다가 게임말이 게임판에 존재하지 않아 고를 수 없는 사람이 지게 된다.
 *  성원이가 이 게임을 이길 수 있을지 없을지를 알려주는 프로그램을 만들자.
[입력]
1) 트리의 정점 개수 N. (2 ≤ N ≤ 5e5)
N-1개 줄) 트리의 간선 a-b. (1 ≤ a, b ≤ N, a ≠ b)

[출력]
이길 수 있으면 Yes, 아니면 No
 */
public class Main_15900_나무탈출_박봉균 {
	/** 인접리스트 */
	static ArrayList<Integer>[] adj;
	/** 정점의 개수 */
	static int N;
	/** 방문 체크 배열 */
	static boolean[] visited;
	/** 결과 : 이동 횟수 */
	static int ret = 0;
	
	/**
	 * DFS 루트에서 리프까지 탐색하고 가능한 총 이동횟수를 구하는 함수
	 * @param now : 현재 노드 번호
	 * @param cnt : 누적 이동 횟수
	 */
	static void dfs(int now, int cnt) {
		visited[now] = true;
		boolean flag = true; //리프노드 여부
		
		for(int next : adj[now]) { //인접 정점에 대하여
			if(visited[next] == false) { //방문하지 않았으면 방문, 리프 아님.
				flag = false;
				dfs(next, cnt + 1);
			}
		}
		
		if(flag) //갈 곳이 없으면 리프 -> 총 이동횟수에 더하기 
			ret += cnt;
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //정점 개수 N 입력
		adj = new ArrayList[N + 1]; //인접리스트 동적 할당
		for(int i = 0; i < N+1; i++)
			adj[i] = new ArrayList<>();
		visited = new boolean[N + 1]; //visited[] 동적 할당
		
		//간선 정보 인접리스트에 저장
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		//게임 진행
		dfs(1, 0);
		
		//총 이동횟수가 홀수면 승, 아니면 패
		if(ret % 2 == 1)
			bw.write("Yes\n");
		else 
			bw.write("No\n");
		
		//데이터 출력 및 stream 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
