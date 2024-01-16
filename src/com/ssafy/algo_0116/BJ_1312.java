package com.ssafy.algo_0116;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1312_소수
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71855571"></a>
 * 
 * 결과: "맞았습니다!!", 14136KB, 140ms
 */

/*
 * 피제수(분자) A와 제수(분모) B가 있다. 두 수를 나누었을 때, 소숫점 아래 N번째 자리수를 구하려고 한다. 
 * Ex) A=3,B=4, N=1이라면, A÷B=0.75 이므로 출력 값은 7이다.

[입력]
1) A, B(1 ≤ A, B ≤ 100,000), N(1 ≤ N ≤ 1,000,000)

[출력] 
A÷B를 했을 때, 소숫점 아래 N번째 수를 출력한다.
 */
public class BJ_1312 {

	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				  
		//a, b, n을 입력 받습니다.
		int a = Integer.parseInt(st.nextToken()); 
		int b = Integer.parseInt(st.nextToken()); 
		int n = Integer.parseInt(st.nextToken());
				 
		//수학에서의 나눗셈 계산과 같이 수행합니다.
		int rem = a % b; //나눈 나머지를 나타냅니다.
		int res = 0; //각 단계에서의 나눗셈 결과를 나타냅니다.
		for(int i = 0; i < n; i++) { //n번의 단계에 대하여
			rem *= 10; //나머지에서 10을 곱합니다.
			res = rem / b; //나눗셈 수행.
			rem = rem % b; //나눈 나머지를 확인합니다.
			
			//나머지가 0이라면 나눗셈 연산을 종료합니다.
			if(rem == 0) {
				break;
			}
		}
				
		System.out.println(res);
			
	}

}
