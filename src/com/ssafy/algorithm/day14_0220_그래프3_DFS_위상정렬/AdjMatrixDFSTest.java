package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.util.Scanner;

/*
7
8
0 1
0 2	
1 3
1 4
2 4
3 5
4 5
5 6	
 */
public class AdjMatrixDFSTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt(); 	
		
		int[][] adjMatrix = new int[V][V]; //0으로 초기화(인접되지 않은 상태로 초기화)
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1; //무향이므로 간선 양방향 처리
		}
		
		boolean[] visited = new boolean[V];
		dfs(adjMatrix, visited, 0);
	}
	
	static void dfs(int[][] adjMatrix,boolean[] visited, int cur) {
		int V = adjMatrix.length;		
		visited[cur] = true;
		System.out.println((char)(cur + 65));
		
		//5. 탐색 정점의 주변 인접정점들 탐색될 수 있도록 처리하기
		for (int i = 0; i < V; i++) {
			if(adjMatrix[cur][i] != 0 && visited[i] == false) {
				dfs(adjMatrix, visited, i);
			}
		}
	}
}
