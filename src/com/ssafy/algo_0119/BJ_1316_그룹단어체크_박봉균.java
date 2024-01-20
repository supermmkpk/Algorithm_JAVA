package com.ssafy.algo_0119;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1316 : 그룹 단어 체크
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72034943"></a>
 *
 * 결과: 맞았습니다!!, 14204KB, 124ms
 */

/*
 * 그룹 단어: 모든 문자에 대해, 각 문자가 연속해서 나타나는 경우만. 
 * ex) "ccazzzzbb":OK, "kin": OK, "aabbbccb": X
 * N개 단어를 입력 받아 그룹 단어의 개수를 출력하라.
[입력]
1) 단어의 개수 N이 들어온다. (N <= 100)
N개 줄) 단어 (알파벳 소문자ONLY, 중복X, 길이 <= 100)

[출력]
그룹 단어의 개수를 출력
 */
public class BJ_1316_그룹단어체크_박봉균 {


	public static void main(String[] args) throws IOException {
		//단어의 개수 N을 입력 받습니다.
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		boolean[] alphaCnt = new boolean[26]; //알파벳별로 이전에 나왔는지 여부를 체크하는 배열입니다.
		
		int result= 0; //최종 결과(그룹단어 개수)를 저장하는 변수입니다.
        
        //T개의 단어(테스트케이스)에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			Arrays.fill(alphaCnt, false);
			boolean ok = true;//그룹단어 여부를 확인하는 변수입니다.
						
			String word = br.readLine();
			//길이가 1이면 그룹단어입니다.
			if(word.length() == 1) {
				result++;
				continue;
			}
			for(int pos = 1; pos < word.length(); pos++) {
				int prevIdx = word.charAt(pos - 1) - 'a'; //이전 문자
				int idx = word.charAt(pos) - 'a'; //현재 문자
				//이전 문자와 다르면 아전 문자 true
				if(prevIdx != idx) {
					alphaCnt[prevIdx] = true;
				}
				//같으면 계속 false
				
				//중간에 다른 문자가 끼어 있고 앞에 나온 적이 있다면
				//그룹 문자가 아닙니다.
				if(alphaCnt[idx]) {
					ok = false;
					break;
				}
			}
			if(ok) {
				result++;
			}
		}
		//그룹 단어를 출력합니다.
		System.out.println(result);
		
		br.close(); //stream을 닫습니다.
	}

}


