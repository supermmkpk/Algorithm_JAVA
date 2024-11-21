package com.ssafy.algorithm.day19_0227_DP1;
import java.util.*;
import java.io.*;

/**
 * BJ_1149[S1] : RGB 거리
*/
public class reviewDP {
	// 완탐(3^1000) 불가 -> 그리디 ㄴㄴ -> 메모이제이션 가능? DP!
	// DP는 기저사례, 메모이제이션, 로직, 초기화!
	
	static final int INF = (int) 1e9;
	
	static int N;
	static int[][] cost;
	static int[][] dp; //상태값: 현재 집, 현재 색 
	
	static int go(int nowHouse, int nowColor) {
		//기저사례
		if(nowHouse == 1) {
			return 0;
		}
		
		//메모이제이션
		if(dp[nowHouse][nowColor] != INF) {
			return dp[nowHouse][nowColor];
		}
		
		//로직
		for(int i = 0; i < 3; i++) {
			if(i == nowColor && nowHouse != N + 1) {
				continue;
			}
			dp[nowHouse][nowColor] = Math.min(dp[nowHouse][nowColor], go(nowHouse - 1, i) + cost[nowHouse - 1][i]);
		}
		return dp[nowHouse][nowColor];
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N + 4][3];
		dp = new int[N + 4][3];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//초기화
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], INF);
		}

		System.out.println(go(N + 1, 0));
		br.close();
	}

}
