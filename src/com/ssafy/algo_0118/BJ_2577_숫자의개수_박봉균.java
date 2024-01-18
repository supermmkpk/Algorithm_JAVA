package com.ssafy.algo_0118;

import java.io.*;

/**
 * <pre>
 * BJ2577 : 숫자의 개수
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71960946"></a>
 *
 * 결과: "맞았습니다!!", 14052KB, 120ms
 */

/*
[입력]
1) A
2) B 
3) C
(100 <= A, B, C <= 1000)
A*B*C <= 1e9 - int OK

[출력]
10개줄) A × B × C의 결과에서 0~9의 개수를 각각 출력.
 */
public class BJ_2577_숫자의개수_박봉균 {

	public static void main(String[] args) throws IOException {
		//카운팅은 맵 또는 배열
		int[] numCnt = new int[10];
		
		//세 자연수 A, B, C를 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		//A * B * C 의 결과를 구합니다.
		int mul = A * B * C;
		//결과를 문자열로 변환합니다.
		String mulStr = String.valueOf(mul); 
		
		//아스키코드로 카운팅 배열의 인덱스를 계산하고 값을 증가시킵니다.
		for(int i = 0; i < mulStr.length(); i++) {
			numCnt[mulStr.charAt(i) - '0']++;
		}
		
		//출력 조건에 따라 각 숫자의 개수를 출력합니다.
		for(int i : numCnt) {
			System.out.println(i);
		}
	}

}
