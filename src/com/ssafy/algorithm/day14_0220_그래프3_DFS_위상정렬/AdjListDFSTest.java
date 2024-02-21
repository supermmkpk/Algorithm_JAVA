package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.util.Scanner;
 
public class AdjListDFSTest {
	static class Node {
		int to;
		Node next;
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
		public Node(int to) {
			this.to = to;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", next=" + next + "]";
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt(); 	
		
		Node[] adjList = new Node[V];
		for(int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		dfs(adjList, new boolean[V], 0);
	}
	
	static void dfs(Node[] adjList, boolean[] visited, int cur) {
		visited[cur] = true;
		System.out.println((char)(cur + 65));
		
		//5. 탐색 정점의 주변 인접정점들 탐색될 수 있도록 처리하기
		for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
			if(visited[tmp.to] == false) {
				dfs(adjList, visited, tmp.to);
			}
		}
	}

}
