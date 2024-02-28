package com.ssafy.algorithm.day17_0223_MST2_프림;
import java.io.*;
import java.util.*;

/*
7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0

output==>175
 */
public class PrimPQTest {
	
	static class Vertex implements Comparable<Vertex> {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(this.weight, v.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[V][V]; //인접행렬 준비
		boolean[] visited = new boolean[V]; //트리 정점 여부
		int[] minEdge = new int[V]; //비트리 정점 기준으로 트리 정점들과 연결했을 경우 최소 간선 비용
		
		StringTokenizer st;
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); //최솟값 갱신 위해 max 초기화
		minEdge[0] = 0; //임의의 시작점 0을 위해 처리
		pq.add(new Vertex(0, minEdge[0]));
		
		int result = 0; //MST 비용
		int c = 0;
		while(!pq.isEmpty()) {
			//step1 : 비트리 정점 중 최소 간선 비용의 정점 찾기!!
			Vertex minVertex = pq.remove();
			if(visited[minVertex.no])
				continue;
			
			result += minVertex.weight; //간선 비용 누적
			visited[minVertex.no] = true; //트리 정점에 포함
			if(++c == V)
				break;
			
			//step2 : 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과 간선 비용 고려 최적 업데이트
			for (int i = 0; i < V; i++) {
				if(visited[i] == false && adjMatrix[minVertex.no][i] != 0 
						&& adjMatrix[minVertex.no][i] < minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex.no][i];
					pq.add(new Vertex(i, minEdge[i]));
				}
			}
		}
		
		System.out.println(c == V ? result : -1);
	}

}
