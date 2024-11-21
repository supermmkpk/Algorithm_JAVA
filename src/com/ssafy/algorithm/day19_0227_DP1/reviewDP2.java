package com.ssafy.algorithm.day19_0227_DP1;
import java.io.*;
import java.util.*;

/**
 * BJ_1010[S5] : 다리 놓기
 */
public class reviewDP2 {

	static int N, M;
	static int[][] dp; 

	static int go(int n, int r) {
		// 기저사례
		if (n == r || r == 0)
			return dp[n][r] = 1;

		// 메모이제이션
		if (dp[n][r] != 0)
			return dp[n][r];

		// 로직
		return dp[n][r] = go(n - 1, r) + go(n - 1, r - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //서쪽
			M = Integer.parseInt(st.nextToken()); //동쪽
			dp = new int[M + 4][N + 4];

			// dp 초기화(default 0)
			
			//동적 계획법으로 경우의 수 구하고 출력
			bw.write(String.valueOf(go(M, N)) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
