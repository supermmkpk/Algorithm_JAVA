package com.ssafy.algorithm.day12_0215_그래프1;
import java.util.Arrays;
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
public class AdjMatrixTest {

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
		
		for (int[] row : adjMatrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}