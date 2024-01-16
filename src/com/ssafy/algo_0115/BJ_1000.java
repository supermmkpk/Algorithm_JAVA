package com.ssafy.algo_0115;

import java.util.*;

/**
 * <pre>
 * BJ1000_A+B
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 *
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

[입력]
1) A, B (0 < A, B < 10)

[출력]
A+B를 출력
 */
public class BJ_1000 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println(a+b);

	}

}
