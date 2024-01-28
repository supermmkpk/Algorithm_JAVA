package com.ssafy.algo_0128;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15663 : N과 M (9)
 * </pre>
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72524700"></a>
 * 
 * 결과: "맞았습니다!!", 29076KB, 284ms
 */

/*
 * N개 자연수 중 M개를 고른 수열을 구하라.
[입력]
1) N, M (1 ≤ M ≤ N ≤ 8)
2) N개의 수. (<= 10,000)
[출력]
한 줄에 하나씩 수열을 출력 (중복X, 각 수열은 공백으로 구분해서 출력, 사전 순)
 */
public class BJ_15663_N과M9_박봉균 {
	static int N, M;
	/** 결과 배열 */
	static int[] result;
	/** 입력값 배열 */
	static int[] input;
	/** 방문 여부 배열 */
	static boolean[] visited =  new boolean[8];
	static StringBuilder sb = new StringBuilder();
	
	/**
	 * 입력 받은 N개 수 중 M개를 골라 출력합니다.
	 */
	static void go(int start) {
		//결과 수열 크기가 M이라면, 출력 후 종료
		if(start == M) {
			for(int i : result) {
				sb.append(i + " ");
			}
			sb.append('\n');
			return;
		}
		
		int prev = 0;
		for(int i = 0; i < N; i++) { //정렬 된 입력값 배열에 대하여, 
			//이전값과 다르고 해당 요소를 방문 안 했다면, 결과에 추가합니다.
			if (!visited[i] && input[i] != prev) { 
				result[start] = input[i];
				prev = result[start]; //겹치지 않게 이전 값 기록.
				visited[i] = true;
				go(start + 1);
				visited[i] = false;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		//N, M, N개의 수 입력 및 input[], result[] 배열 동적 할당
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input); //수행 전, 입력값을 정렬합니다.(사전순 출력 조건)
		go(0); //수열 구하기
		System.out.println(sb.toString()); //출력
	}
}
