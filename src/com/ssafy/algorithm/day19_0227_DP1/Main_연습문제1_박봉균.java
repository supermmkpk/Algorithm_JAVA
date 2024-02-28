package com.ssafy.algorithm.day19_0227_DP1;
import java.io.*;
import java.util.*;

/*
 * 파란색 or 노란색으로 칠하려고 한다.
 * 노란색: 인접한 두 층에 연속OK
 * 파란색: 인접한 두 층에 연속X
 * N 층의 아파트를 칠할 수 있는 방법의 수는?
 */
public class Main_연습문제1_박봉균 {
	
	static int[][] dp; //상태값: 현재 층, 이전 색
	
	//노랑:0, 파랑:1
	static int go(int level, int color) {
		//기저사례
		if(level == 1)
			return 1;
		
		//메모이제이션
		if(dp[level][color] != -1)
			return dp[level][color];
		
		//로직
		//노란색(0): 인접한 두 층에 연속OK
		if(color == 0)
			return dp[level][color] = go(level - 1, 0) + go(level - 1, 1);
		//파란색(1): 인접한 두 층에 연속X
		else
			return dp[level][color] = go(level - 1, 0);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[N + 4][2];
		
		//초기화
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);

		System.out.println(go(N, 0) + go(N, 1));
	}
}
