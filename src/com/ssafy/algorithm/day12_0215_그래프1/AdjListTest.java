package com.ssafy.algorithm.day12_0215_그래프1;
import java.util.Scanner;
 
/*
7
8
0 1
0 2
0 5
0 6
4 3
5 3
5 4
6 4
 */
public class AdjListTest {
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
		
		Node[] adjList = new Node[V]; //각 정점의 인접리스트의 헤드 저장
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]); //맨 앞으로 삽입하는 알고리즘
			adjList[to] = new Node(from, adjList[to]);
		}
		
		for (Node node : adjList) {
			System.out.println(node);
		}		
	}
}
