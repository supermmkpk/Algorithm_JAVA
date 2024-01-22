package com.ssafy.algo_0122;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ10870 : 피보나치 수 5
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72183067"></a>
 *
 * 결과: "맞았습니다!!", 17680KB, 212ms
 */

/*
 * 피보나치 수열: Fn = Fn-1 + Fn-2 where n ≥ 2 , F0 = 0 , F1 = 1
 * n번째 피보나치 수를 구하라.

[입력]
1) n. (0 <= n <= 20)

[출력]
Fn
 */
public class BJ_10870_피보나치수5_박봉균 {
	
	/**
	 * n 번째 피보나치 수를 구하는 함수입니다.(재귀) 
	 * @param n 
	 * @return n번째 피보나치
	 */
	static int fibo(int n) {
		switch(n) {
		case 0:
			return 0; //종료조건!(F0 = 0)
		case 1:
			return 1; //종료조건!(F1 = 1)
		default:
			//Fn = Fn-1 + Fn-2
			return fibo(n-1) + fibo(n-2);
		}
	}
	
	public static void main(String[] args) {
		//n을 입력 받습니다.
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//n번째 피보나치 수를 구하고 출력합니다.
		System.out.println(fibo(n));
	}
}
