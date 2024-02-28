package com.ssafy.algorithm.day19_0227_DP1;
import java.util.*;

/**
 * @author 박봉균
 * 파란 막대 1cm, 노란 막대 1cm, 빨간 막대 2cm이 있을 때,
 * 막대들을 연결해 Ncm 만드는 방법의 수 구하자.
 */
public class Main_연습문제2_막대색칠하기_박봉균 {

	static int[] dp; //상태값: 길이
	
	static int go(int len) {
		//기저사례
		if(len == 0) //성공: 1
			return 1;
		else if(len < 0) //실패: 0
			return 0;
		
		//메모이제이션
		if(dp[len] != -1)
			return dp[len];
		
		//로직: 파랑, 노랑, 빨강 중 하나 잇는다
		return dp[len] = go(len - 1) + go(len - 1) + go(len - 2);		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[N + 4];
		
		//초기화
		Arrays.fill(dp, -1);
		
		System.out.println(go(N));
	}
}
