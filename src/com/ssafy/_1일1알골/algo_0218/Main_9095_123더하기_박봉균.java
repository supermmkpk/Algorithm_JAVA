package com.ssafy._1일1알골.algo_0218;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ9095 [S3] : 1, 2, 3 더하기
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 완탐 - 가다가... 합 같으면 cnt++, return / 합 넘어가면 return
 * 결과: 11516KB, 84ms
 */

/* <요약> (시간: 1초)
 * 정수 N이 주어질 때, 1, 2, 3의 합으로 나타내는 방법의 수를 구해보자.
[입력]
1) 테케 개수 T.
T개 줄) 정수 N. 1 <= N <= 10
[출력]
각 테케) 1, 2, 3의 합으로 나타내는 방법의 수 <개행>
 */
public class Main_9095_123더하기_박봉균 {
	/** 1,2,3의 합으로 만들어야 하는 숫자 N */
	static int N;
	/** 결과 변수: 경우의 수 */
	static int ret;
	
	/**
	 * 1, 2, 3만을 이용해 합을 N으로 만드는 경우의 수를 구하는 함수 
	 * @param start : 시작
	 * @param sum : 현재 합
	 */
	static void go(int sum) {
		//종료조건!
		//합 같으면 결과 증가, return
		if(sum == N) {
			++ret;
			return;
		}
		//합 넘어가면 그냥 return
		else if(sum > N)
			return;
		
		//1, 2, 3에 대해 각각 탐색합니다.
		for(int i = 1; i <= 3; i++) 
			go(sum + i);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine()); //테스트케이스 개수 T
		
		//T개의 테스트케이스에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화!!
			ret = 0;
			
			//합으로 만들어야 하는 숫자 N 입력
			N = Integer.parseInt(br.readLine());
			
			//경우의 수 구하기
			go(0);
			
			//경우의 수 출력
			bw.write(String.valueOf(ret) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
