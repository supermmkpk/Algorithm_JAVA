package com.ssafy.algorithm.day4_0201_부분집합;

import java.util.Scanner;

//N개의 원소를 입력 받아 가능한 모든 부분집합 생성
//1 <= N <= 10
public class PowerSetSumTest {
	static int N;
	static int[] input;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		generateSubset(0);
	}
	
	/**
	 * 모든 부분집합 구하기 (PowerSet_멱집합)
	 * @param cnt : 현재까지 고려된 원소 개수
	 */
	private static void generateSubset(int cnt) {
		//종료조건!!
		if(cnt == N) { //모든 원소가 고려되었다면
			//출력
			for (int i = 0; i < N; i++)
				System.out.print((visited[i] ? input[i] : "X") + "\t");
			System.out.println();
			return;
		}
		else {
			visited[cnt] = true; //포함!
			generateSubset(cnt + 1); //포함한 상태에서 다음 녀석
			visited[cnt] = false; //포함 안 하기!
			generateSubset(cnt + 1); //포함 안한 상태에서 다음 녀석
		}
	}
	
	
}