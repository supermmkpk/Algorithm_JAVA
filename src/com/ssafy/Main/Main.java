package com.ssafy.Main;
import java.util.*;
import java.io.*;

/*
 * 그래프의 최소 스패닝 트리를 구하시오.
[입력]
1) 정점의 개수 V, 간선의 개수 E. (1 ≤ V,E ≤ 1e5)
E개 줄) 간선 정보를 A, B, C. (A번 --<가중치C>-- B번) (|C| <= 1e6)
  정점은 1~V번까지 번호가 있다. 최소 스패닝 트리의 가중치 <= int 범위로 데이터가 주어진다.
[출력]
최소 스패닝 트리의 가중치
 */
public class Main {
	static Queue<Integer> q = new ArrayDeque<>();
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//정점의 개수 V
		V = Integer.parseInt(st.nextToken());
		//간선의 개수 E
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 4];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}
		
		for(int i = 0; i < V; i++) 
			parent[i] = i;
		
		
		long ret = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.remove();
			if(union(e.from, e.to) == false)
				continue;
			ret += e.weight;
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
		else
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

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
}