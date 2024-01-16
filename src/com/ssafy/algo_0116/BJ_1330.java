package com.ssafy.algo_0116;

import java.util.*;

/**
 * <pre>
 * BJ1330_두 수 비교하기
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 *
 * 두 정수 A와 B가 주어졌을 때, A와 B를 비교하시오.

[입력]
1) A, B (-10,000 ≤ A, B ≤ 10,000)

[출력]
첫 줄에 다음 세 가지 중 하나를 출력.
	A > B : '>'
	A < B ; '<'
	A == B : '=='
 */
public class BJ_1330 {
	public static void main(String[] args) {
		
		//a,b를 입력 받습니다.
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		//주어진 조건에 따라 출력합니다.
		if(a > b) {
			System.out.println(">");
		}
		else if(a < b) {
			System.out.println("<");
		} 
		else { //a == b
			System.out.println("==");
		}
	}

}
