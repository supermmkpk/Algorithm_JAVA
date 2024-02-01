package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ12891 [S2] : DNA 비밀번호
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72767615"></a>
 * 
 * 결과: 20672KB, 216ms
 */

/* <문제 요약>
 * DNA 문자열: 문자열에 등장하는 모든 문자가 {‘A’, ‘C’, ‘G’, ‘T’} 중 하나.
 * DNA 문자열의 부분문자열을 비밀번호로 사용하고자 한다.
 * 만들 수 있는 비밀번호의 종류의 수 구하라. (단, 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같아도 다른 문자열)
[입력]
1) DNA 문자열 길이 S, 부분문자열 길이 P. (1 ≤ |P| ≤ |S| ≤ 1e6)
2) DNA 문자열
3) 포함돼야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수(0<= <= |S|, 총 합 <= |S|)

[출력]
비밀번호의 종류의 수.
 */
public class Main_12891_DNA비밀번호_박봉균 {
	/** DNA 문자열 길이 */
	static int S;
	/** 부분 문자열(비밀번호) 길이 */
	static int P;
	/** 입력 받는 DNA 문자열 */
	static String given;
	/** 필요한 ACGT 최소 개수. (0:A, 1:C, 2:G, 3:T) */
	static int[] acgt = new int[4];
	/** 현재 비밀번호의 ACGT 개수 (0:A, 1:C, 2:G, 3:T) */
	static int[] acgtNow = new int[4];
	/** 답: 가능한 비밀번호 수 */
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//DNA문자열 길이 S, 비밀번호 길이 P, DNA문자열 입력 
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		given = br.readLine();
		//최소 A, C, G, T 개수 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) 
			acgt[i] = Integer.parseInt(st.nextToken());
		
		//초기윈도우
		for(int i = 0; i < P; i++) {
			plus(given.charAt(i));
		}
		check();
			
		//슬라이딩 윈도우
		for(int i = P; i < S; i++) {
				plus(given.charAt(i));
				minus(given.charAt(i - P));
				check();
		}
		//출력 및 stream을 닫습니다.
		bw.write(String.valueOf(cnt) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}

	//비밀번호로 가능한지 체크
	static void check() {
		int i = 0;
		for(i = 0; i < 4; i++) {
			if(acgtNow[i] < acgt[i])
				break;
		}
		//ACGT 최소개수보다 모두 많으면 카운트 증가
		if(i == 4) 
			cnt++;
	}

	//윈도우에 추가
	static void plus(char c) {
		//들어오는 문자에 따라 ACGT 개수 갱신
		switch(c) {
		case 'A':
			acgtNow[0]++;
			break;
		case 'C':
			acgtNow[1]++;
			break;
		case 'G':
			acgtNow[2]++;
			break;
		case 'T':
			acgtNow[3]++;
			break;
		}
	}
	//윈도우에서 제거
	static void minus(char c) {
		//나가는 문자에 따라 ACGT 개수 갱신
		switch(c) {
		case 'A':
			acgtNow[0]--;
			break;
		case 'C':
			acgtNow[1]--;
			break;
		case 'G':
			acgtNow[2]--;
			break;
		case 'T':
			acgtNow[3]--;
			break;
		}
	}
}
