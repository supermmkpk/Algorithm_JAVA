package com.ssafy.algorithm.day3_0130_조합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ11659 [S3] : 구간합 구하기 4
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72682668"></a>
 * 
 * 아이디어: 누적합을 저장하고 구간합을 구한다.
 * 결과: 73452KB, 688ms
 */

/* <문제 요약>
 * 수 N개가 주어졌을 때, i ~ j 구간합을 구하라.
[입력]
1) 수의 개수 N <공백> 합을 구하는 횟수 M (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 100,000)
2) N개의 수. (1<= 수 <= 1,000)
M개 줄) 구간 i와 j (1 ≤ i ≤ j ≤ N)

[출력]
M개 줄) i ~ j 구간합. 
 */
public class Main_11659_구간합구하기4_박봉균 {
	/** 누적합 (인덱스: 1 ~ N) */
	static int[] preSum;
	/** 수의 개수  N, 합 구하는 횟수 M */
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//수의 개수 N, 합 구하는 횟수 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//수 저장
		preSum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			preSum[i] = Integer.parseInt(st.nextToken());
		
		//누적합 구하기 (인덱스: 1 ~ N)
		for(int i = 1; i <= N; i++) 
			preSum[i] = preSum[i] + preSum[i-1];
		
		//M개의 테스트케이스에 대하여
		for(int t = 1; t <= M; t++) {
			//구간의 시작(start), 끝(end) 입력
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			//구간 합(start~end) 구하고 출력
			bw.write(String.valueOf(preSum[end] - preSum[start - 1]) + '\n');
		}
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
