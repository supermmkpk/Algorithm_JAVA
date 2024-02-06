package com.ssafy.algorithm.day5_0202_스택과큐;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1218 [D4] : 괄호 짝짓기
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 *
 * 결과: Pass, 0.11323s
 */

/* <문제 요약>
 * '()', '[]', '{}', '<>' 로 이루어진 문자열이 주어진다.
괄호들의 짝이 모두 맞는지 판별하라

[입력]
10개 테케]
	1) 테스트케이스의 길이
	다음 줄) 테스트 케이스

[출력]
#테케번호 <공백> 유효성여부(1, 0)
 */
public class D4_1218_괄호짝짓기_박봉균 {
	/** 스택 */
	static Stack<Character> stk = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//T개의 테스트 케이스에 대하여
		for(int t = 1; t <= 10; t++) {
			//초기화!
			stk.clear();
			
			int len = Integer.parseInt(br.readLine()); //문자열 길이  입력
			String given = br.readLine(); //문자열 입력
			
			//문자열의 각 문자에 대하여 유효성 여부를 확인합니다
			for(int i = 0; i < len; i++) {
				char now = given.charAt(i);
				check(now);
			}
			
			//출력 조건에 따라 유효성 여부를 출력합니다.
			System.out.print("#" + t + " ");
			if(stk.empty()) 
				System.out.println(1);
			else
				System.out.println(0);
		}
		br.close(); //stream을 닫습니다
	}
	
	/**
	 * 유효성 여부 검사하는 함수입니다.
	 * @param c : 검사 대상 문자
	 */
	static void check(char c) {
		//닫는 괄호인 경우, top이 매칭되면 pop, 아니면 push합니다.
		//닫는 괄호가 아니면(default), push합니다.
		switch(c) {
		case ')':
			if(stk.peek() == '(')
				stk.pop();
			else 
				stk.push(c);
			break;
		case ']':
			if(stk.peek() == '[')
				stk.pop();
			else 
				stk.push(c);
			break;
		case '}':
			if(stk.peek() == '{') 
				stk.pop();
			else 
				stk.push(c);
			break;
		case '>':
			if(stk.peek() == '<')
				stk.pop();
			else 
				stk.push(c);
			break;
		default:
			stk.push(c);
		}
	}
}