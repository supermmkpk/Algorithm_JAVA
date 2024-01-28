package com.ssafy.algo_0125;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15650 : N과 M (2)
 * </pre>
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72374230"></a>
 * 
 * 결과: "맞았습니다!!", 22056KB, 204ms
 */

/*
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 구하라.(단, 오름차순)
[입력]
1) N, M (1 ≤ M ≤ N ≤ 8)
[출력]
한 줄에 하나씩 수열을 출력 (중복X, 각 수열은 공백으로 구분해서 출력, 사전 순)
 */
public class BJ_15650_N과M2_박봉균 {
	static int N, M;
	static boolean[] visited = new boolean[10]; //중복되는지 체크하는 배열
	
	/**
	 * 1~N 중 중복 없이 M개를 골라 출력합니다.
	 * @param l : 현재 수열을 저장하는 리스트
	 */
	static void combi(LinkedList<Integer> l) {
		//수열 리스트 크기가 M이면 출력하고 return.
		if(l.size() == M) {
			//이전 원소와 비교하여 오름차순이 아니면 출력하지 않습니다.
			int prev = l.getFirst();
			for(int i : l) {
				if(prev > i)
					return;
				prev = i;
			}
			//출력
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

