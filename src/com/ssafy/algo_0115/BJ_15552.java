package com.ssafy.algo_0115;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ15552_빠른A+B
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 *
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 * 
[입력]
1) 테스트케이스 수 T (T <= 1e6)
T개 줄) 두 정수 A, B (1<= A,B <= 1000)

[출력]
각 테스트케이스마다 A+B를 한 줄에 하나씩 순서대로 출력
 */
public class BJ_15552 {

	public static void main(String[] args) throws IOException {
		
		//빠른 입출력을 위해 BufferedReader 및 BufferedWriter를 선언합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); //첫 줄에서 테스트케이스의 수를 입력 받습니다.
		for(int t = 1; t <= T; t++) { //T개의 테스트케이스에 대하여
			//a, b를 입력 받습니다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			bw.write(String.valueOf(a + b)); //a+b 출력.
			bw.newLine(); //개행
		}
		bw.flush(); //남은 데이터 출력
		bw.close(); //stream을 닫습니다.
		
	}

}
