package com.ssafy._1일1알골.algo_0128;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15656 : N과 M (7)
 * </pre>
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72517512"></a>
 * 
 * 결과: "맞았습니다!!", 334564KB, 1000ms
 */

/*
 * 1부터 N까지 자연수(모두 다른 수) 중에서 M개를 고른 수열을 구하라.(같은 수 중복 OK)
[입력]
1) N, M (1 ≤ M ≤ N ≤ 8)
2) N개의 수. (<= 10,000)
[출력]
한 줄에 하나씩 수열을 출력 (중복X, 각 수열은 공백으로 구분해서 출력, 사전 순)
 */
public class BJ_15656_N과M7_박봉균 {
	static int N, M;
	/** 결과 리스트 */
	static LinkedList<Integer> list = new LinkedList<>();
	/** 입력값 리스트 */
	static LinkedList<Integer> input = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	
	/**
	 * 입력 받은 N개 수 중 M개를 골라 출력합니다.
	 */
	static void go() {
		//결과 수열 크기가 M이라면, 출력 후 종료
		if(list.size() == M) {
			for(int i : list) {
				sb.append(i + " ");
			}
			sb.append('\n');
			return;
		}
		//정렬 된 입력값 리스트에 대하여, 
		for(int i : input) {
			list.addLast(i); //결과 수열에 추가
			go(); //같은 과정 반복
			list.removeLast(); //원상복구
		}
	}
	
	public static void main(String[] args) throws IOException {
		//N, M, N개의 수 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(input); //수행 전, 입력값을 정렬합니다.(사전순 출력 조건)
		go(); //수열 구하기
		System.out.println(sb.toString()); //출력
	}
}
