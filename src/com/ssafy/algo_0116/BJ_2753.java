package com.ssafy.algo_0116;

import java.util.*;

/**
 * <pre>
 * BJ2753_윤년
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 *
 * 윤년: 연도가 4의 배수 && (!(100의 배수) || 400의 배수)
 * 연도가 주어졌을 때, 윤년이면 1, 아니면 0을 출력하는 프로그램을 작성하시오.

[입력]
1) 연도 (1<= 연도 <= 4000)

[출력]
첫째 줄에 윤년이면 1, 아니면 0
 */
public class BJ_2753 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt(); //연도를 입력 받습니다.
		
		//윤년 조건에 따라 윤년 여부를 확인합니다.
		if((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
			System.out.println("1"); //윤년이면 1을 출력합니다.
		}
		else {
			System.out.println("0"); //윤년이 아니면 0을 출력합니다.
		}
	}

}
