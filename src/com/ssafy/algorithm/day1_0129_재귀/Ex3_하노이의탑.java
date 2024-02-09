package com.ssafy.algorithm.day1_0129_재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * <pre>
 * BJ1914 : 하노이 탑
 * </pre>
 * @author 박봉균
 * @since JDK8
 * @see <a href="https://www.acmicpc.net/source/72608653"></a>
 * 
 * 결과: 59756KB, 404ms
 */
public class Ex3_하노이의탑 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	static void move(int n, int start, int empty, int target) {
		if(N > 20)
			return;
		if(n == 0) 
			return;
		move(n - 1,start, target , empty);
		sb.append(start + " " + target + '\n');
		move(n - 1, empty, start, target);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N <= 20) 
			move(N, 1, 2, 3);
		
		//이동 횟수 구하기 : 2^N - 1
		BigInteger cnt = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		BigInteger one = new BigInteger("1");
		for(int i = 0; i < N; i++)
			cnt = cnt.multiply(two);
		
		//출력
		System.out.println(cnt.subtract(one).toString());
		if(N <= 20)
			System.out.println(sb.toString());
		br.close();
	}
}
