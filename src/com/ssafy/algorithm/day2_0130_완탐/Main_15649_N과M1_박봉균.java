package com.ssafy.algorithm.day2_0130_완탐;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15649[S3] : N과 M (1) 
 * </pre>
 * @author 박봉균
 * @since JDK8
 * @see <a href="https://www.acmicpc.net/source/72615892"></a>
 * 
 * 결과: 41412KB, 392ms
 */
public class Main_15649_N과M1_박봉균 {
	/** 전체 숫자 개수 N */
	static int N;
	/** 수열의 크기 M */
	static int M;
	/** 수열을 저장하는 리스트 */
	static LinkedList<Integer> list = new LinkedList<>();
	/** 방문(중복) 여부를 체크하는 배열. 편의상 인덱스 1부터 사용*/ 
	static boolean[] visited = new boolean[10];
	static StringBuilder sb = new StringBuilder();
	
	/**
	 * 1~N 자연수 중 중복 없이 M개를 고른 수열을 구하는 함수
	 */
	static void choose() {
		//종료조건! : 수열의 크기가 M이라면 출력후 종료.
		if(list.size() == M) {
			for(int i : list) 
				sb.append(i + " ");
			sb.append('\n');
			return;
		}
		//1~N 자연수에 대하여, 중복 없이 수열에 더한다.
		for(int i = 1; i <= N; i++) {
			if(visited[i]) //방문했다면, 즉, 중복된다면 건너뛰기
				continue;
			list.addLast(i); //수열에 추가
			visited[i] = true; //방문 체크
			choose(); //뒤의 숫자에 대해서도 동일 과정 수행
			//원상복구
			visited[i] = false;
			list.removeLast();
		}
	}

	
	public static void main(String[] args) throws IOException {
		//N, M 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		choose(); //수열 구하기
		
		System.out.println(sb.toString()); //출력
		br.close(); //stream을 닫습니다.
	}
}
