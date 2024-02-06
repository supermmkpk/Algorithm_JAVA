package com.ssafy._1일1알골.algo_0204;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ10828 [S4] : 스택
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72914231"> </a>
 * 
 * 결과: 16708KB, 384ms
 */

/*
 * 정수를 저장하는 스택을 구현하고, 주어지는 명령을 처리하라.
 * push X, pop(없는 경우에는 -1을 출력), size, empty(비어있으면 1, 아니면 0), top(없는 경우 -1)
[입력]
1) 명령의 수 N. (1 ≤ N ≤ 10,000)
N개 줄) 명령어. (1 <= 주어지는 정수 <= 100,000)

[출력]
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력.
 */
public class Main_10828_스택_박봉균 {
	/** 스택 */
	static Stack<Integer> stk = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //명령어 수 N 입력
		
		//push X, pop(뺀 값 출력, 없는 경우, -1), size, empty(비어있으면 1, 아니면 0), top(없는 경우 -1)
		String command;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			command = st.nextToken();
			
			if(st.hasMoreElements()) { //2개 토큰 -> push 명령어
				stk.push(Integer.parseInt(st.nextToken()));
			}
			else {
				switch(command) { //주어지는 명령어 수행
				case "pop":
					if(!stk.empty())
						System.out.println(stk.pop());
					else 
						System.out.println(-1);
					break;
				case "size":
					System.out.println(stk.size());
					break;
				case "empty":
					if(stk.empty()) 
						System.out.println(1);
					else 
						System.out.println(0);
					break;
				case "top":
					if(!stk.empty())
						System.out.println(stk.peek());
					else 
						System.out.println(-1);
					break;
				}
			}
		}
	}
}
