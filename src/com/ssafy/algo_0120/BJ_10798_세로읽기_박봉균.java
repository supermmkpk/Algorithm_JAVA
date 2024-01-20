package com.ssafy.algo_0120;

import java.io.*;

/**
 * <pre>
 * BJ10798 : 세로 읽기
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72082869"></a>
 *
 * 결과: "맞았습니다!!", 14400KB, 124ms
 */

/*
 * 단어들이 주어질 때, 세로로 읽은 순서대로 글자들을 출력하라.
[입력]
5개 줄) 문자열(1 <= 길이 <= 15) ‘A’~‘Z’, ‘a’~‘z’, ‘0’~‘9’

[출력]
세로로 읽은 순서대로 글자들을 출력.
 */
public class BJ_10798_세로읽기_박봉균 {
	
	public static void main(String[] args) throws IOException {
		//입출력을 위해 BufferedReader 및 BufferedWriter를 선언합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력으로 들어오는 문자열을 2차원 문자 배열로 저장합니다. 
		char[][] inChar = new char[5][15];
		for(int i = 0; i < 5; i++) {
			String in = br.readLine();
			inChar[i] = in.toCharArray();
		}
		
		int idx = 0; //확인할 열을 나타냅니다. 		
		while(true) {
			int flag = 0; //열에 문자가 존재하는지 확인합니다.
			for(int i = 0; i < 5; i++) { //5개의 행에 대하여
				if(inChar[i].length <= idx) { //해당 행의 길이를 벗어나면
					flag++; //해당 행의 열에는 문자가 없습니다.
					continue; 
				}
				bw.write(inChar[i][idx]); //해당 행의 해당 열 문자를 버퍼에 기록합니다.
			}
			if(flag >= 5) { //열의 5개 행에 문자가 없으면 확인을 종료합니다.
				break;
			}
			idx++; //다음 열 
		}
		bw.flush(); //데이터를 출력합니다.
		
		//stream을 닫습니다.
		bw.close();
		br.close();
	}

}
