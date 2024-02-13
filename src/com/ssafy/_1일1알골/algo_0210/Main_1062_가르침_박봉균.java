package com.ssafy._1일1알골.algo_0210;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1062 [G4] : 가르침
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 비트마스킹으로 글자 트래킹
 * 결과: 12820KB, 324ms
 */
/*
 * 김지민은 최대한 많은 단어를 가르치려 한다. 그러나, K개의 글자를 가르칠 시간 밖에 없다. 
 * 가르친 후, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
 * 어떤 K개의 글자를 가르쳐야 읽을 수 있는 단어의 개수가 최대가 될까?
 * 남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다.
 * 남극언어에 단어는 N개 밖에 없다고 가정한다. 학생들이 읽을 수 있는 단어의 최댓값을 구하시오.
[입력]
1) 단어의 개수 N, 가르치는 글자 수 K. (1 <= N <= 50, 0 <= K <= 26)
N개 줄) 남극의 모든 단어. (소문자ONLY, 8 <= 길이 <= 15, 중복X)

[출력]
1) K개 글자를 가르칠 때, 학생들이 읽을 수 있는 단어의 최대 개수.
 */
public class Main_1062_가르침_박봉균 {
	/** 남극 단어 수 N, 가르치는 글자수 K */
	static int N, K;
	/** 주어지는 단어의 글자 구성을 비트마스킹으로 저장하는 배열 */
	static int[] words;
	
	/** 
	 * 비트마스크와 비교하여 읽을 수 있는 단어 수를 카운트하는 함수
	 */
	static int count(int visited) { 		
		int cnt = 0; 
		for(int word : words) {
			if((visited & word) == word && word != 0) //마스크와 같으면 읽을 수 있습니다.
				cnt++;
		}
		return cnt;
	}
	
	/** 
	 * 글자를 재귀적으로 배우고 읽을 수 있는 단어 수를 확인하는 함수 
	 */
	static int go(int now, int k, int visited) {
		//종료조건
		if(k < 0) 
			return 0; //배울 수 없음
		if(now == 26) 
			return count(visited); //다 배움 -> 읽을 수 있는 단어 카운트
		
		int ret = go(now + 1, k - 1, visited | 1 << now); //현재 단어 배우기
		if(now != 'a' - 'a' && now != 'n' - 'a' && now != 't' - 'a' 
				&& now != 'i' - 'a' && now != 'i' - 'a') {
			ret = Math.max(ret, go(now + 1, k, visited)); //antic은 필수, 아니라면 안 가르쳐도 됨
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 남극 단어 수 N, 가르치는 글자수 K 입력
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new int[N];
		
		//단어 입력, 비트 마스크로 글자 구성 저장
		for(int i = 0; i < N; i++) {
			String given= br.readLine();
			for(int j = 0; j < given.length(); j++) 
				words[i] |= 1 << (given.charAt(j) - 'a');
		}
		
		//읽을 수 있는 단어의 최댓값 출력
		bw.write(String.valueOf(go(0,K,0)) + '\n');
		
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
}
