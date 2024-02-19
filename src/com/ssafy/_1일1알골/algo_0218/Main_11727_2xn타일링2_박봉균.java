package com.ssafy._1일1알골.algo_0218;
import java.util.*;
import java.io.*;
/**
 * <pre>
 * BJ11727 [S3] : 2 x n 타일링 2
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 1: 시작 세로(2*1), 2: 시작 가로(1*2), 3: 시작 네모(2*2) , 재귀? DP?
 *     DP -> 기저사례, 메모이제이션, 로직, 초기화!
 * 결과: 12116KB, 80ms
 */

/* <요약> (시간: 1초)
 * 2*N 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구해보자.
[입력]
1) N. (1 ≤ N ≤ 1e3)
[출력]
2*N 크기의 직사각형을 채우는 방법의 수 % 10,007
 */
public class Main_11727_2xn타일링2_박봉균 {
	/** 직사각형 가로 길이 N */
	static int N;
	/** DP 상태값: 가로, 세로 */
	static int[][] dp;
	
	/**
	 * 동적 계획법으로 직사각형을 1*2, 2*1, 2*2로 채우는 경우의 수를 구하는 함수
	 * @param x : 남은 가로 길이
	 * @param y : 세로 길이(1: 0, 2: 1)
	 */
	static int go(int x, int y) {
		//기저사례
		if(x == 0) 
			return 1;
		else if(x < 0) 
			return 0;
		
		//메모이제이션
		if(dp[x][y] != -1)
			return dp[x][y];
		
		//로직
		//1: 2*1, 2: 1*2, 2*2
		return dp[x][y] = (go(x - 1, 0) + go(x - 2, 0) + go(x - 2, 1)) % 10007;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine()); //직사각형 가로 길이 N 입력
		
		//초기화
		dp = new int[N + 1][2];
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		
		//경우의 수 구하고 % 10007하여 결과 출력
		bw.write(String.valueOf(go(N, 0)) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}

