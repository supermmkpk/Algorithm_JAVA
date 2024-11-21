package com.ssafy.algorithm.day13_0216_그래프2_BFS;
import java.io.*;
import java.util.*;


/**
 * BJ_2644[S2] : 촌수 계산
 */
public class reviewBFS_AdjList {
	//가중치 같은 간선의 최단 경로 -> BFS!!, int visited[]
	static LinkedList<Integer>[] adjList;
	static int N, M, from, to, visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		adjList = new LinkedList[N + 4];
		visited = new int[N + 4];
		for(int i = 0; i < adjList.length; i++) {
			adjList[i] = new LinkedList<>();
		}
		st = new StringTokenizer(br.readLine());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			adjList[parent].add(child);
			adjList[child].add(parent);			
		}
		
		bfs(from);
		
		System.out.println(visited[to] - 1);
		br.close();
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		visited[start] = 1;
		q.add(start);
		
		while(!q.isEmpty()) {
			int now = q.remove();
			for(int next : adjList[now]) {
				if(visited[next] == 0) {
					q.add(next);
					visited[next] = visited[now] + 1;
				}
			}
		}
	}
	
	

}
