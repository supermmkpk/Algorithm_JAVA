package com.ssafy.algorithm.day20_0228_DP2;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 아이디어: 조합, DP
 * 결과: 13248KB, 92ms
 */

/*
 * 다리를 짓기에 적합한 곳을 사이트라 한다. 
 * 강 서쪽에 N개 사이트,동쪽에 M개 사이트가 있다. (N ≤ M)
 * 서쪽 사이트와 동쪽 사이트를 다리로 연결하려 한다. 
 * 서쪽 사이트 개수만큼 (N개) 다리를 지으려 한다.
 * 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하라.
[입력]
1) 테케 수 T.
T개 줄) 서쪽과 동쪽에 있는 사이트의 수 N, M. (0 < N ≤ M < 30)
[출력]
다리를 지을 수 있는 모든 경우의 수
*/
public class Main_1010_다리놓기_박봉균 {

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
