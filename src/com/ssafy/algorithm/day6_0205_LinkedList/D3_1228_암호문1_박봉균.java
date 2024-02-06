package com.ssafy.algorithm.day6_0205_LinkedList;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1228 [D3] : 암호문1
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: LinkedList 이용하여 특정 위치에 삽입하기
 * 결과: Pass, 0.10392s
 */

/* <문제요약>
 * 0 ~ 999999 사이의 수를 나열하여 만든 암호문이 있다. 암호문은 처리기로만 수정이 가능하다.
 * 1. I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.
 *   Ex) I 3 2 123152 487651 
 * 명령어가 주어졌을 때, 암호문을 수정하고, 결과의 처음 10개 숫자를 출력라.
[입력]
1) 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
2) 원본 암호문
3) 명령어의 개수 ( 5 ≤ N ≤ 10 의 정수)
4) 명령어
총 10개의 테스트 케이스가 주어진다.

[출력]
#테케번호 <공백> 수정된 암호문의 처음 10개 항.
 */
public class D3_1228_암호문1_박봉균 {
	/** 암호문 저장 연결리스트 */
	static LinkedList<Integer> list = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 10개의 테스트케이스에 대하여
		for (int t = 1; t <= 10; t++) {
			//초기화
			list.clear();
			
			int N = Integer.parseInt(br.readLine()); // 원본 암호문 길이 N 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 10; i++) 
				list.add(Integer.parseInt(st.nextToken())); // 원본 입력(10개)

			int nCmd = Integer.parseInt(br.readLine()); // 명령어 개수 nCmd 입력

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < nCmd; i++) {
				st.nextToken(); // I
				int start = Integer.parseInt(st.nextToken()); //시작 좌표 입력
				int n = Integer.parseInt(st.nextToken()); //수정 개수 입력
				for(int j = start; j < start + n; j++) //수정 개수(n개)에 대하여
					if(j < list.size()) //추가 가능한 인덱스 범위이면,
						list.add(j, Integer.parseInt(st.nextToken())); //해당 위치에 추가합니다.
					else
						st.nextToken(); //추가 불가하다면 넘어가기
			}
			//출력 조건에 따라, 암호문 리스트에서 초기 10개 출력
			bw.write("#" + String.valueOf(t));
			for (int i = 0; i < 10; i++)
				bw.write(" " + String.valueOf(list.get(i)));
			bw.write('\n');
		}
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
