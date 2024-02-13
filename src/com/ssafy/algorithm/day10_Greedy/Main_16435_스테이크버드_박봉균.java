package com.ssafy.algorithm.day10_Greedy;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ16435 [S5] : 스네이크 버드
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: PQ에 높이 넣고, 낮은 높이부터 처리(먹기)
 * 결과: 12168KB, 92ms
 */

/* <문제 요약>
 * 스네이크버드는 과일 하나를 먹으면 길이가 1만큼 늘어납니다.
 *  i (1 ≤ i ≤ N) 번째 과일은 높이 hi만큼 지상에서 떨어져 있습니다.
 *  자신의 길이 이하의 높이에 있는 과일들을 먹을 수 있습니다.
 *  초기 길이가 L일때, 과일들을 먹어 늘릴 수 있는 최대 길이를 구하세요.
[입력] 
1) 과일의 개수 N, 초기 길이 L. (1 ≤ N ≤ 1,000, 1 ≤ L ≤ 10,000)
2)  h1, h2, ..., hN (1 ≤ hi ≤ 10,000).

[출력]
 최대 길이를 출력합니다.
 */
public class Main_16435_스테이크버드_박봉균 {
	/** 과일의 개수 N, 초기 길이 L */
	static int N, L;
	/** 과일 높이 PQ(자동 오름차순) */
	static PriorityQueue<Integer> hPQ = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 과일의 개수 N, 초기 길이 L 입력
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		//과일 높이 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			hPQ.add(Integer.parseInt(st.nextToken()));
		
		//낮은 높이부터 처리
		int len = L;
		while(!hPQ.isEmpty()) {
			if(len >= hPQ.remove()) 
				++len;
			else
				break;
		}
		//최대 길이 출력
		bw.write(String.valueOf(len) + '\n');
		
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
}