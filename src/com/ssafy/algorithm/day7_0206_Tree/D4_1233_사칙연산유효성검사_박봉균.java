package com.ssafy.algorithm.day7_0206_Tree;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1233 [D4] : 사칙연산 유효성 검사
 * </pre>
 * 
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어 : 입력에서 자식 번호 입력 없으면 리프노드고 여기서 연산자가 나오면 계산 불가(0), else: 1
 * 결과: Pass, 0.11265s
 */
public class D4_1233_사칙연산유효성검사_박봉균 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//10개 테스트 케이스에 대하여
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine()); //정점 개수 N 입력
			boolean ret = true; //결과 변수
			for(int i = 0; i < N; i++) { //N개 줄에 대하여
				st = new StringTokenizer(br.readLine());
				
				st.nextToken();
				String check = st.nextToken(); //연산자인지 확인
				
				//두개 토큰 뒤에 토큰이 더 있으면 자식이 있으므로 리프노드가 아닙니다.
				if(st.hasMoreTokens()) 
					continue;
				
				//아니면 리프노드이고 여기서 연산자이면 계산 불가
				else if(check.equals("/")||check.equals("*")||check.equals("+")||check.equals("-")) 
					ret = false;
			}
			//결과 변수를 출력합니다.
			System.out.println("#" + t + " " + (ret == false ? 0 : 1));
		}
	}
}