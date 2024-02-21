package com.ssafy.algorithm.day15_0221_서로소집합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1759 [G5] : 암호 만들기
 * </pre>
 * @author 박봉균
 * 
 * 아이디어: 암호문은 정렬되어 있다. -> 순열 같지만 순서 정해져 있음!!
 *     ==> 주어진 문자 정렬하고 그 안에서 조합 구하기 -> 유효성 검사 후 출력.
 * 결과: 11720KB, 80ms
 */

/* <요약>
 * 열쇠가 아닌 암호로 동작하게 되어 있는 보안 시스템이 있다.
 * 암호: 서로 다른 L개의 알파벳 소문자들로 구성되며, 한 개 이상의 모음(a, e, i, o, u)과 두 개 이상의 자음으로 구성되어 있다. 
 * 암호를 이루는 알파벳이 정렬되었을 것이라고 추측된다. 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다
 * C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하시오.
[입력]
1) L, C. (3 ≤ L ≤ C ≤ 15) 
2) C개의 문자들 (공백으로 구분, 알파벳 소문자, 중복X)
[출력]
각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력.
 */
public class Main_1759_암호만들기_박봉균 {
	
	/** 암호 길이 L, 문자 수 C */
	static int L, C;
	/** 주어지는 사용 가능한 문자들 */
	static char[] given;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//암호 길이 L, 문자 수 C , 동적할당
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		given = new char[C];
		
		//사용 가능한 문자 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) 
			given[i] = st.nextToken().charAt(0);
		
		//먼저 정렬 -> 암호문은 정렬돼야 가능하다
		Arrays.sort(given);
		
		//가능한 암호문 구하기
		combi(0, 0, new char[L]);
		
		//가능한 암호문 사전순 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * 암호문의 조합을 구하는 함수(암호문은 정렬된 상태여야 한다-> 순서X -> 조합)
	 * @param start : 시작 인덱스
	 * @param cnt : 현재까지 뽑은 원소 개수
	 * @param pw : 뽑은 암호문 조합
	 */
	static void combi(int start, int cnt, char[] pw) {
		//종료조건
		if(cnt == L) {
			int vowelCnt = 0; //모음 수
			int consoCnt = 0; //자음 수
			
			//구한 조합의 자음, 모음 개수를 체크합니다.
			for(char c : pw) {				
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
					++vowelCnt;
				else
					++consoCnt;
			}
			
			//암호문의 유효성을 검증하고 출력합니다.
			if(vowelCnt >= 1 && consoCnt >= 2 ) 
				sb.append(String.valueOf(pw) + '\n');
			
			return;
		}
		
		//암호문자의 조합 구하기
		for(int i = start; i < C; i++) {
			//암호문 조합에 현문자 추가하고 다음 탐색
			pw[cnt] = given[i];
			combi(i + 1, cnt + 1, pw);
		}
	}
}
