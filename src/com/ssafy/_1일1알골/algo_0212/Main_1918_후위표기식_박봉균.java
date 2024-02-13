package com.ssafy._1일1알골.algo_0212;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1918 [G2] : 후위표기식
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: 스택 사용, 연산자 우선순위 고려.
 * 결과: 11512KB, 76ms
 */

/* <문제 요약>
 * 중위 표기식이 주어졌을 때 후위 표기식으로 고치시오.
[입력]
1) 중위 표기식. (길이 <= 100) (-A+B와 같이 -가 가장 앞에 오거나 AB와 같이 *가 생략되는 등의 수식은 주어지지 않는다)
    표기식은 알파벳 대문자와 '+', '-', '*', '/', '(', ')' 로만 이루어져 있다. 
[출력]
후위 표기식
 */
public class Main_1918_후위표기식_박봉균 {
	/** 스택 */
	static Stack<Character> stk = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String given = br.readLine(); //주어지는 중위 표기식
		
		//주어진 수식의 각 토큰에 대하여
		for(int i = 0; i < given.length(); i++) {
			char token = given.charAt(i);
			
			switch(token) {
			case '(':
				stk.push(token);
				break;
				
			//괄호는 최우선 -> 반대 괄호 만날 때까지 pop, 출력
			case ')':
				while(stk.peek() != '(') 
					sb.append(stk.pop());
				stk.pop(); //'(' pop
				break;
				
			//연산자: 나보다 우선순위 이상인거 pop, 출력. 아니면 계속 진행(나 자신 push)
			case '+':
			case '-':
				while(!stk.empty()) {
					char top = stk.peek();
					if(top == '*' || top == '/' || top == '+' || top == '-')
						sb.append(stk.pop());
					else
						break;
				}
				stk.push(token);
				break;
			case '*':
			case '/':
				while(!stk.empty()) {
					char top = stk.peek();
					if(top == '*' || top == '/')
						sb.append(stk.pop());
					else
						break;
				}
				stk.push(token);
				break;
			
			default: //피연산자 -> 바로 출력
				sb.append(token);
			}
		}
		
		//남아있는 것들
		while(!stk.empty())
			sb.append(stk.pop());
		
		//후위 표기식 출력
		System.out.println(sb.toString());
	}
}
