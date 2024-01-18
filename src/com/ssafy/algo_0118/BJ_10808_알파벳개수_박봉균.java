package com.ssafy.algo_0118;

import java.io.*;

/**
 * <pre>
 * BJ10808 : 알파벳 개수
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71959519"></a>
 *
 * 결과: "맞았습니다!!", 14520KB, 140ms
 */

/*
[입력]
1) 단어 S (알파벳 소문자로만 구성, 길이 <= 100)

[출력]
포함된 a ~ z의 개수를 각각 공백으로 구분해서 출력
 */

public class BJ_10808_알파벳개수_박봉균 {

	public static void main(String[] args) throws IOException {
		//카운팅은 맵 또는 배열
		//a~z = 97 ~122
		int[] alphaCnt = new int[26];
		
		//단어 str를 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//입력 받은 str에 대하여 각 알파벳의 개수를 확인합니다.
		for(int i = 0; i < str.length(); i++) {
			//아스키코드를 이용하여 카운트 배열의 인덱스 0(a)~25(z)를 계산하고 값을 증가시킵니다.
			alphaCnt[str.charAt(i) - 'a']++; 
		}
		
		//출력 조건에 따라 카운트 배열을 출력합니다.
		for(int i : alphaCnt) {
			System.out.printf("%d ", i);
		}
	}

}
