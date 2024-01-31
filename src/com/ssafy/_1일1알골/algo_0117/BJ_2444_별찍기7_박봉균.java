package com.ssafy._1일1알골.algo_0117;

import java.util.*;

/**
 * <pre>
 * BJ2444 : 별 찍기 - 7
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71918651"></a>
 *
 * 결과: "맞았습니다!!",22492KB, 580ms
 */

/*
[입력]
1) N(1 ≤ N ≤ 100)

[출력]
첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *
 */

public class BJ_2444_별찍기7_박봉균 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //첫 줄에서 n을 입력 받습니다.
		
		int space = n - 1; //별 출력 전 앞의 공백 수를 나타냅니다.
		//분기점(2n-1)까지 1부터 홀수 개의 별을 출력합니다.(증가)
		for(int i = 1; i < 2 * n; i += 2) {
			for(int j = space; j > 0; j--) { //공백 수 만큼 공백을 출력합니다.
				System.out.print(" ");
			}
			for(int j = 0; j < i; j++) { //별을 연달아 출력합니다.
				System.out.print("*"); 
			}
			System.out.println(); //개행
			space--; //분기점까지는 앞의 공백이 감소합니다.
		}
		
		space = 1; //분기점(2n-1)이후에서의 공백 수로 1로 초기화합니다.
		//분기점 이후 2n-3부터 홀수개의 별을 출력합니다.(감소)
		for(int i = 2 * n - 3; i > 0; i -= 2) {
			for(int j = 0; j < space; j++) { //공백 수 만큼 공백을 출력.
				System.out.print(" ");
			}
			for(int j = 0; j < i; j++) { //별을 연달아 출력합니다.
				System.out.print("*");
			}
			System.out.println(); //개행
			space++; //앞의 공백 증가.
		}
	}

}
