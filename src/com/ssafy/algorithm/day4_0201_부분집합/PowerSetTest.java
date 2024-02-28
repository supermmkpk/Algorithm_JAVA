package com.ssafy.algorithm.day4_0201_부분집합;

import java.util.Scanner;

//N개의 원소를 입력 받아 가능한 모든 부분집합 생성
//1 <= N <= 10
public class PowerSetTest {
	static int N;
	static int[] input;
	static int target;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		target = sc.nextInt();
		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		generateSubset(0, 0, 0);
	}
	
	/**
	 * 부분집합의 합
	 * @param cnt
	 * @param sum : 기존 부분집합의 구성요소들의 합
	 */
	private static void generateSubset(int cnt, int sum, int visited) {
		//종료조건!!
		if(cnt == N) { //모든 원소가 고려되었다면, 부분집합을 구성하는 원소들의 합이 목표합이 되는지 체크
			if(sum == target) {
				System.out.print("{");
				for (int i = 0; i < N; i++) {
					if((visited & 1 << i) != 0)
						System.out.print(" " + input[i]);
				}
				System.out.println(" }");
			}
			
			return;
		}
		
		generateSubset(cnt + 1, sum + input[cnt], visited | 1 << cnt); //포함한 상태에서 다음 녀석, 해당 원소 합에 더하기
		generateSubset(cnt + 1, sum, visited); //포함 안한 상태에서 다음 녀석, 합 그대로
	}
}