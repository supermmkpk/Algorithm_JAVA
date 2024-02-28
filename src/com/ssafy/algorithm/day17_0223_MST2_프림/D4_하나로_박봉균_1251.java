package com.ssafy.algorithm.day17_0223_MST2_프림;
import java.io.*;
import java.util.*;


/*
 * 환경 부담금 = E * L^2 where E: 환경 부담 세율, L: 각 해저터널 길이
 * 총 환경 부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계하자
[입력]
1) 테케 수 T.
각 테케] 1) 섬의 개수 N (1 ≤ N ≤ 1,000)
    2) 각 섬의 X좌표. (0 ≤ X ≤ 1e6)
    3) 각 섬의 Y좌표. (0 ≤ Y ≤ 1e6)
    마지막 줄) 환경 부담 세율 실수 E. (0 ≤ E ≤ 1).
[출력]
#테케번호 <공백> 모든 섬들을 잇는 최소 환경 부담금(소수 첫째 자리에서 반올림하여 정수 형태로 출력)
 */
public class D4_하나로_박봉균_1251 {
	/** 섬의 개수 */
	static int N;
	/** 환경 부담 세율 실수 */
	static double E;
	/** 인접행렬 */
	static double[][] adjMatrix;
	
	static boolean[] visited;
	
	static int[] minEdge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			//초기화
			
			
			N = Integer.parseInt(br.readLine());
			adjMatrix = new double[N][N];
			visited = new boolean[N];
			minEdge = new int[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				double weight
				
				
			}
			
		}

	}
	
	static class Vertex implements Comparable<Vertex> {
		int no;
		int y;
		int x;
		int weight;
		
		public Vertex(int no, int y, int x, int weight) {
			this.y = y;
			this.x = x;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex v) {
			return Double.compare(this.weight, v.weight);
		}
	}

}
