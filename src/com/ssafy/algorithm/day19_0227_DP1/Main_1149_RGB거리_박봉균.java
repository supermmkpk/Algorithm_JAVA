package com.ssafy.algorithm.day19_0227_DP1;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 아이디어: 시간 0.5초. 완탐:O(3*2^1000) -> 절대 불가 -> 메모이제이션 가능! -> DP 
 * 결과: 12352KB, 96ms
 */

/* <BJ1149 : RGB 거리>
 * 1~N번 집이 있다. 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어질 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *  1. 1번 색 != 2번 색
 *  2. N번 색 != N-1번 색
 *  3. i(2 ≤ i ≤ N-1) 색 != i-1번, i+1번 색
[입력]
1) 집의 수 N. (2 ≤ N ≤ 1000)
N개 줄) 빨강, 초록, 파랑 비용. (1 <= 비용 <= 1000)
[출력]
모든 집을 칠하는 비용의 최솟값
 */
public class Main_1149_RGB거리_박봉균 {
	static final int INF = (int)1e9;
	
	/** 집 개수 */
	static int N;
	/** 집 별 색 비용 */
	static int[][] cost; 
	static int[][] dp; //상태값: 집 번호, 현재 색
	
	/** 
	 * 동적 계획법으로 가능한 최소 비용을 구하는 함수
	 * @param n : 집 번호
	 * @param color : 현재 색
	 */
	static int go(int n, int color) {
		//기저사례
		if(n == 1)
			return 0;
		
		//메모이제이션
		if(dp[n][color] != INF)
			return dp[n][color];
		
		//로직: 전부 인접 집과 색깔 다름
		for(int i = 0; i < 3; i++) {
			if(i == color && n != N + 1) //초기에는(N+1) 아무 색상
				continue;
			dp[n][color] = Math.min(dp[n][color], go(n - 1, i) + cost[n - 1][i]);
		}

		return dp[n][color];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//집 개수 N 입력, 동적 할당
		N = Integer.parseInt(br.readLine());
		cost = new int[N+4][3];
		dp = new int[N+4][3];
		
		//집 별 색 비용 입력
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		//초기화
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], INF);
		
		//최소 비용 구하고 출력
		bw.write(String.valueOf(go(N + 1, 0)) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
