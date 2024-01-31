package com.ssafy._1일1알골.algo_0125;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15649 : N과 M (1)
 * </pre>
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72372688"></a>
 * 
 * 결과: "맞았습니다!!", 68940KB, 2284ms
 */

/*
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 구하라.
[입력]
1) N, M (1 ≤ M ≤ N ≤ 8)
[출력]
한 줄에 하나씩 수열을 출력 (중복X, 각 수열은 공백으로 구분해서 출력, 사전 순)
 */
public class BJ_15649_N과M1_박봉균 {
	static int N, M;
	static boolean[] visited = new boolean[10]; //중복되는지 체크하는 배열
	
	/**
	 * 1~N 중 중복 없이 M개를 골라 출력합니다.
	 * @param l : 현재 수열을 저장하는 리스트
	 */
	static void combi(LinkedList<Integer> l) {
		//수열 리스트 크기가 M이면 출력하고 return.
		if(l.size() == M) {
			for(int i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		//1~N 중 숫자를 고릅니다.
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) { //이미 고르지 않았다면
				visited[i] = true;
				l.addLast(i); //수열에 더하고 
				combi(l); //다음 자리에서 같은 과정을 반복합니다.	
				
				//원상복귀.
				l.removeLast(); 
				visited[i] = false;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		//입력 조건에 따라 N, M을 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 구합니다.
		LinkedList<Integer> list = new LinkedList<>();
		combi(list);
	}
}
