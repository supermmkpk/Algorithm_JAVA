package day3_combination;

import java.util.*;

/* <시간제한: 1초>
 * 자연수 N과 M이 주어졌을 때, 1~N 자연수 중 중복 없이 M개를 고른 수열을 구하시오. (오름차순)
[입력]
1) N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
[출력]
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
 */
class BJ15650_N과M2_S3 {
	
	//간단히 조합 구하는 문제
	
	static int N, M, numbers[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		
		combi(0, 1);
	}
	
	static void combi(int cnt, int start) {
		if(cnt == M) {
			for(int num : numbers) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= N; i++) {
			numbers[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
}
