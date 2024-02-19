package com.ssafy.algorithm.day13_0216_그래프2;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2644 [S2] : 촌수 계산
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 인접리스트로 양방향 트리처럼 표현하고, 가중치 1로 같은 최단경로를 구하는 것과 같다! -> BFS!
 * 결과: 11780KB, 76ms
 */

/* <요약> (시간: 1초)
 * 촌수는 다음과 같은 방식으로 계산된다. 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 
 * 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
 * 주어진 두 사람의 촌수를 계산해보자.
[입력]
사람들은 1, 2, 3, …, n (1 ≤ n ≤ 100)의 연속된 번호로 각각 표시된다. 
1) 전체 사람의 수 N
2) 두 사람의 번호가 주어진다. 
3)부모 자식들 간의 관계의 개수 M이 주어진다. 
M개 줄) 두 번호 x,y. (x는 y의 부모, 부모는 최대 한명)
[출력]
두 사람의 촌수를 나타내는 정수를 출력한다. (촌수를 계산할 수 없을 경우 -1)
 */
public class Main_2644_촌수계산_박봉균 {
	/** 전체 사람의 수 N, 부모 자식 관계의 개수 M */
	static int N, M;
	/** 두 사람의 번호 */
	static int start, end;
	/** 방문 체크, 경로의 길이 저장 */
	static int[] visited;
	/** 인접리스트 */
	static LinkedList<Integer>[] adjList;
	/** BFS용 큐 */
	static Queue<Integer> q = new ArrayDeque<>();
	
	/** BFS */
	static void bfs(int start) {
		visited[start] = 1;
		q.add(start);
		while(!q.isEmpty()) {
			int now = q.remove();
			
			for(int next : adjList[now]) {
				if(visited[next] == 0) {
					visited[next] = visited[now] + 1;
					q.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//전체 사람 수 N 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		adjList = new LinkedList[N + 4];
		for(int i = 0; i < adjList.length; i++)
			adjList[i] = new LinkedList<>();
		visited = new int[N + 4];
		
		// 두 사람의 번호 입력
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		//부모 자식 관계 수 M
		M = Integer.parseInt(br.readLine());

		//부모 자식 관계 입력 -> 인접리스트
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			adjList[parent].add(child);
			adjList[child].add(parent);
		}
		
		//BFS 탐색으로 촌수 계산
		bfs(start);
		
		//촌수 출력
		bw.write(String.valueOf(visited[end] - 1) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
