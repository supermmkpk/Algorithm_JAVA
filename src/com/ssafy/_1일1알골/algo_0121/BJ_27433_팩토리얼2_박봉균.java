package com.ssafy._1일1알골.algo_0121;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ27433 : 팩토리얼2
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72125921"></a>
 *
 * 결과: "맞았습니다!!",17704KB, 208ms
 */

/*
[입력]
1)정수 N. (0 ≤ N ≤ 20)
	20! = 2.432902e+18 -> long

[출력]
N!
 */
public class BJ_27433_팩토리얼2_박봉균 {

	/**
	 * n의 팩토리얼을 반환하는 함수입니다.(재귀)
	 * @param n : 팩토리얼을 구할 n
	 * @return n!
	 */
	static long fac(int n) {
		//종료 조건 : 1 이하일 경우 1을 return. (1! = 1, 0! = 1)
		if(n <= 1) {
			return 1;
		}
		//n! = n * (n-1)!
		return n * fac(n - 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//N! 출력
		System.out.println(fac(N));
	}
}
