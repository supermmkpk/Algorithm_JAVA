package com.ssafy.algorithm.day16_0222_MST1_크루스칼;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 아이디어: 크루스칼 알고리즘 사용
 * 결과: 48972KB, 596ms
 */

/* <BJ1197_최소스패닝트리>
 * 그래프의 최소 스패닝 트리를 구하시오.
[입력]
1) 정점의 개수 V, 간선의 개수 E. (1 ≤ V,E ≤ 1e5)
E개 줄) 간선 정보를 A, B, C. (A번 --<가중치C>-- B번) (|C| <= 1e6)
  정점은 1~V번까지 번호가 있다. (최소 스패닝 트리의 가중치 <= int 범위로 데이터가 주어진다.)
[출력]
최소 스패닝 트리의 가중치
 */
public class Main_1197_최소스패닝트리_박봉균 {
	
	static int V, E, parent[];
	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//정점의 개수 V
		V = Integer.parseInt(st.nextToken());
		//간선의 개수 E
		E = Integer.parseInt(st.nextToken());
		edges = new Edge[E];
		parent = new int[V];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(from, to, weight);
		}
		
		//0. 정점 생성
		for(int i = 0; i < V; i++) 
			parent[i] = i;
		
		//1. 가중치 기준 오름차순 정렬
		Arrays.sort(edges);
		
		//2. 가중치 작은 간선 뽑기
		long ret = 0; //결과: 가중치 합
		int cnt = 0; //뽑은 간선 개수
		for(Edge edge : edges) {
			//같은 트리에 속하면 안 됨
			if(!union(edge.from, edge.to))
				continue;
			
			//가중치 더하기
			ret += edge.weight;
			//다 뽑으면 종료
			if(++cnt == V - 1)
				break;
		}
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int find(int a) {
		if(parent[a] == a)
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB)
			return false;
		
		parent[rootB] = rootA;
		return true;
	}
	
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}		
	}
}
