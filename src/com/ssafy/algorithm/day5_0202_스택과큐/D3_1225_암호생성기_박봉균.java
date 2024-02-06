package com.ssafy.algorithm.day5_0202_스택과큐;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1225 [D3] : 암호 생성기
 * </pre>
 * @author 박봉균
 * 
 * 아이디어: 큐 사용해서 front remove, 연산 후 rear에 add -> 0이하면 마지막으로 0 add 후 종료
 * 결과: Pass, 0.12024s
 */

/*
 * 조건에 따라 n개의 수를 처리하면 8자리의 암호를 생성할 수 있다.
 * 1. 8개의 숫자를 입력 받는다.
 * 2. 첫 숫자를 1 감소 -> 맨 뒤로 
 * 		다음 첫 숫자는 2 감소 -> 맨 뒤
 *  	그 다음 첫 숫자: 3을 감소 -> 맨 뒤로,
 *   	다음 첫 수 4감소->맨뒤
 *   	다음 첫수 5감소->맨뒤
 * 이를 한 사이클이라 한다.
 * 0이하 : 0, 암호.
[입력]
각 테케) 1) 테케 번호
	다음 줄) 8개의 숫자 
 
[출력]
#테케번호 <공백> 암호
 */
public class D3_1225_암호생성기_박봉균 {
	/** 큐 */
	static Queue<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(int t = 1; t <= 10; t++) { //10개 테스트 케이스에 대하여
			br.readLine();
			//초기화
			q.clear();
			
			//8개 숫자 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) 
				q.add(Integer.parseInt(st.nextToken())); 
			
			//암호 생성
			int front = q.peek(); //front 원소
			boolean flag = false; //암호 완성 여부
			while(true) {
				for(int i = 1; i <= 5; i++) {
					front = q.remove(); //첫 숫자 remove
					if(front - i <= 0) {
						q.add(0);
						flag = true;
						break;
					}
					q.add(front - i); //i(1~5) 빼서 맨 뒤로
				}
				if(flag) 
					break;
			}
			bw.write("#" + t + " ");
			for(int i = 0; i < 8; i++)
				bw.write(String.valueOf(q.remove())+ " ");
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}