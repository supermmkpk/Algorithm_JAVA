package com.ssafy._1일1알골.algo_0214;
import java.util.*;
import java.io.*;

/*
 * int fibonacci(int n) {
	    if (n == 0) {
	        printf("0");
	        return 0;
	    } else if (n == 1) {
	        printf("1");
	        return 1;
	    } else {
	        return fibonacci(n‐1) + fibonacci(n‐2);
	    }
	}
	에서, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구해보자.
[입력]
1) 테스트 케이스의 개수 T
T개 줄) N (0 <= N <= 40)
[출력]
각 테케) 0이 출력되는 횟수, 1이 출력되는 횟수 <개행>
 */
/**
 * DP: 기저사례, 메모이제이션, 로직, 초기화. (상태값에 대한 생각!)
 */
public class Main_1003_피보나치수열 {
	static int[][] dp = new int[41][2];
	
	static int go(int n, int zeroOrOne) {
		//기저사례
		if(n == 1) {
			if(zeroOrOne == 0)
				return 0;
			else 
				return 1;
		}
		if(n == 0) {
			if(zeroOrOne == 0)
				return 1;
			else 
				return 0;
		}
		
		//메모이제이션
		if(dp[n][zeroOrOne] != -1)
			return dp[n][zeroOrOne];
		
		//로직
		return dp[n][zeroOrOne] = go(n - 1, zeroOrOne) + go(n - 2, zeroOrOne);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		//초기화
		for(int i = 0; i < dp.length; i++) 
			Arrays.fill(dp[i], -1);
		
		//T개의 테케에 대하여
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(go(N, 0)) + " " + String.valueOf(go(N, 1)) + '\n');
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
