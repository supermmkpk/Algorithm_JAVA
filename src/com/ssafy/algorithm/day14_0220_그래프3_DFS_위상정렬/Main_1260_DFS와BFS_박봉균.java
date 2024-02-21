package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1260 [S2] : DFS와 BFS
 * </pre>
 * @author 박봉균
 * 아이디어: DFS, BFS 하면서 출력 같이 진행하고, 간선 여러개인 인접리스트에 대해 정렬 후 진행
 * 결과 : 18388KB, 188ms
 */

/* <요약>
 * DFS 탐색 결과와 BFS 탐색 결과를 출력하시오. 
입력
1) 정점 개수 N, 간선 개수 M, 탐색 시작 정점 번호 V. (1 ≤ N ≤ 1,000, 1 ≤ M ≤ 10,000)
M개 줄) 간선이 연결하는 두 정점 번호. (간선 여러 개: 작은번호 정점부터)
[출력]
1) DFS 수행 결과
2) BFS 수행 결과. 
V부터 방문된 점을 순서대로 출력.
 */
public class Main_1260_DFS와BFS_박봉균 {
	/** 정점 개수 N, 간선 개수 M, 탐색 시작 정점 번호 V */
	static int N, M, V;
	/** 인접리스트 */
	static LinkedList<Integer>[] adjList;
	/** 방문 체크 배열 */
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	/** DFS */
	static void dfs(int now) {
		visited[now] = true;
		sb.append(now + " "); //현 정점 출력
		
		for(int next : adjList[now]) {
			if(visited[next] == false) {
				dfs(next);
			}
		}
	} 
	
	/** BFS */
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		visited[start] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			int now = q.remove();
			sb.append(now + " "); //현 정점 출력
			
			for(int next : adjList[now]) {
				if(visited[next] == false) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//정점 개수 N, 간선 개수 M, 탐색 시작 정점 번호 V, 동적할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 4];
		adjList = new LinkedList[N + 4];
		for(int i = 0; i < N + 4; i++) 
			adjList[i] = new LinkedList<>();
		
		//간선 정보 입력 -> 인접리스트(adjList)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		//간선 여러 개: 작은번호 정점부터 이므로 1개보다 많은 간선 가진 인접리스트 정렬 후 진행
		for(int i = 0; i < N + 4; i++) {
			if(adjList[i].size() > 1)
				Collections.sort(adjList[i]);
		}
		
		//DFS 탐색
		dfs(V);
		sb.append('\n');
		
		//방문 배열 초기화
		Arrays.fill(visited, false);
		
		//BFS 탐색
		bfs(V);
		sb.append('\n');

		//결과 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
