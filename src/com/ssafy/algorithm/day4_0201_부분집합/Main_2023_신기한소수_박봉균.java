package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;

/**
 * <pre>
 * BJ2023 [G5] : 신기한 소수
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72777014"></a>
 * 
 * 아이디어: - 첫 자리 2,3,5,7에 대해서 (10 곱하고 1~9더해서 소수 확인)하면서 확장.
 * 		- "10 곱하고 1~9더해서 소수 확인": 동일한 과정 반복 -> 재귀(종료조건: 자리수 충족)
 * 결과: 12816KB, 108ms
 */

/* <문제 요약>
 * 신기한 소수: 왼쪽 모두 소수
 *  Ex) 7331: 7, 73, 733, 7331 모두 소수.
 *  주어진 N에 대하여, N자리 신기한 소수 구하라.
[입력]
1) N. (1 ≤ N ≤ 8)

[출력]
각 줄) N자리 신기한 소수.(오름차순)
 */
public class Main_2023_신기한소수_박봉균 {
	/** 자리수 */
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //자릿수 N 입력
		
		//첫 자리수 2,3,5,7부터 시작하여 확장하며 소수여부 체크 및 출력
		go(2, 1);
		go(3, 1);
		go(5, 1);
		go(7, 1);
		
		System.out.println(sb.toString()); //출력
		sc.close();
	}
	
	/**
	 * 자릿수를 확장하며 신기한 소수를 찾는 함수
	 * @param n : 현재 숫자
	 * @param depth : 현재 자릿수
	 */
	static void go(int n ,int depth) {
		//종료조건!! : 자릿수 충족
		if(depth == N) {
			sb.append(n);
			sb.append('\n');
			return;
		}
		
		int next = n * 10; //다음 자릿수: * 10
		for(int i = 1; i < 10; i++) { 
			if(isPrime(next + i))  //다음 자릿수의 1~9 소수 여부 확인
				go(next + i, depth + 1); //소수라면 계속 확장
		}		
	}
	
	/**
	 * 소수 여부 체크
	 * @param num : 체크 대상 숫자
	 */
	static boolean isPrime(int num) {
		if(num < 2) //1은 소수가 아님
			return false;
		
		for(int i = 2; i * i <= num; i++) { //제곱근에 대한 대칭구조를 이용해 소수 여부 체크
			if(num % i == 0) 
				return false;
		}
		return true;
	}
}
