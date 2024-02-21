package com.ssafy.algorithm.day15_0221_서로소집합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ13023 [G5] : ABCDE
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: DFS 탐색하면서 5연쇄 관계(간선) 존재하면 main으로 돌아오기. 인접리스트 사용
 * 결과: 18372KB, 384ms
 */

/* <요약>
 * N명의 사람들은 0~(N-1)번이고, 일부 사람들은 친구이다.
 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 * 1. A는 B와 친구다.
 * 2. B는 C와 친구다.
 * 3. C는 D와 친구다.
 * 4. D는 E와 친구다.
 * 이런 친구 관계가 존재하는지 안하는지 구하시오.
[입력]
1) 사람 수 N, 친구 관계 수 M. (5 ≤ N ≤ 2000, 1 ≤ M ≤ 2000)
M개 줄) a, b. (a와 b가 친구. (0 ≤ a,b ≤ N-1, a != b, 중복X) 
[출력]
조건에 맞는 A, B, C, D, E가 존재하면 1, 없으면 0
 */
public class Main_13023_ABCDE_박봉균 {
	/** 사람 수 N, 친구 관계 수 M */
	static int N, M;
	/** 인접리스트 */
	static LinkedList<Integer>[] adjList;
	/** 방문 체크 배열 */
	static boolean[] visited;
	/** 결과 변수 : 친구 관계 존재 여부 */
	static int ret;
	
	/**
	 * DFS탐색하며 5연쇄 관계(간선)을  확인하는 함수
	 * @param now : 현재 정점
	 * @param cnt : 연쇄관계 개수
	 */
	static void dfs(int now, int cnt) {
		//주어진 5개 친구 관계 존재하면 return -> 탐색 종료! 
		if(cnt == 5) {
			ret = 1;
			return;
		}
		//모든 인접 노드들에 대하여
		for(int next : adjList[now]) {
			//방문하지 않았다면
			if(visited[next] == false) {
				visited[next] = true; //방문체크하고
				dfs(next, cnt + 1); //방문합니다. (카운트 증가)
				//존재하면 더이상 볼 필요 없음 -> 연쇄return!! (시간초과 방지)
				if(ret == 1) 
					return;
				visited[next] = false; //원복
			}	
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//사람 수 N, 친구 관계 수 M 입력, 동적할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		adjList = new LinkedList[N];
		for(int i = 0; i < N; i++) 
			adjList[i] = new LinkedList<>();
		
		//친구 관계 입력 -> 인접리스트 저장(무방향 간선) 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		//모든 정점에 대하여 DFS탐색하고 5연쇄 관계를 찾으면 종료합니다.
		for(int i = 0; i < N; i++) {
			ret = 0;
			dfs(i, 0);
			if(ret == 1)
				break;
		}
		
		//주어진 친구관계 조건을 만족하는지 출력(만족: 1, 불만족: 0)
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
