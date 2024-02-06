package com.ssafy.algorithm.day4_0201_부분집합;

import java.util.*;
import java.io.*;

/**
 * BJ2961 [S2] : 도영이가 만든 맛있는 음식
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: - {신맛, 쓴맛} Pair를 리스트에 저장.
 * 			- 부분집합(PowerSet) 구하고 공집합이 아니라면 PQ에 저장->자동 오름차순. 
 * 결과: 11696KB, 80ms
 */

/* <문제 요약>
 *  재료가 N개 있다. 각 재료의 신맛 S와 쓴맛 B가 있다.
 *  여러 재료를 이용시, 신맛: 신맛의 곱이고, 쓴맛: 쓴맛의 합
 *  신맛과 쓴맛의 차이를 작게 만들려 한다. 재료는 하나 이상 사용해야 한다.
 *  신맛, 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이의 최소를 구하라.
[입력]
1) 재료의 개수 N. (1 ≤ N ≤ 10)
N개 줄) 신맛 <공백> 쓴맛 (모든 재료 사용 시, 1 <= < 1e9) -> int OK

[출력]
1) 신맛과 쓴맛의 차이의 최솟값. 
 */
public class Main_2961_도영의맛있는음식_박봉균 {
	/** 재료 개수 N */
	static int N;
	/** {신맛,쓴맛} Pair로 저장하는 리스트*/
	static ArrayList<Pair> list = new ArrayList<>();;
	/** 오름차순 정렬 유지 PriorityQueue */
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	/** 원소 사용 여부 체크 배열 */
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//재료 개수 N 입력
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N]; //방문배열 동적 할당
		
		//재료의 {신맛, 쓴맛} 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int shin = Integer.parseInt(st.nextToken());
			int sseun =  Integer.parseInt(st.nextToken());
			list.add(new Pair(shin, sseun));
		}

		//재료 조합 구하기
		powerSet(0, 1, 0);
		
		//출력, stream 닫기
		bw.write(String.valueOf(pq.peek()) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * 재료 조합을 구하고 pq에 추가합니다.
	 * @param cnt : 현재까지 고려한 원소 개수
	 * @param shin : 신맛 
	 * @param sseun : 쓴맛
	 */
	static void powerSet(int cnt, int shin, int sseun) {
		if(cnt == N) { //모든 재료 고려
			boolean flag = false; //공집합이면 false
			for(boolean v : visited) 
				if(v) flag = true;
			
			if(flag) //공집합이 아니면 추가
				pq.add(Math.abs(shin - sseun));
			return;
		}
		else {
			visited[cnt] = true; //포함
			powerSet(cnt + 1, shin * list.get(cnt).first, sseun + list.get(cnt).second); //신맛은 곱, 쓴맛은 합
			visited[cnt] = false; //불포함
			powerSet(cnt + 1, shin, sseun); //그대로
		}
	}
}

/**
 * Pair 클래스
 */
class Pair {
	public int first;
	public int second;
	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}
