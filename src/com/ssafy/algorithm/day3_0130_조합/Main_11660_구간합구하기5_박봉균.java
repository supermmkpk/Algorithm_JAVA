package com.ssafy.algorithm.day3_0130_조합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ11660 [S1] : 구간합 구하기 5
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72678112"></a>
 * 
 * 아이디어:	- 누적합: (y,x) + (y-1,x) + (y,x-1) - (y-1,x-1)
			- 구간합: (y2,x2)누적합  - (y1,x2)누적합 - (y2,x1)누적합 + (y1,x1)누적합  
 * 결과: 125808KB, 888ms
 */

/* <문제 요약>
 * N*N 행렬에 대해, (y1, x1)부터 (y2, x2)까지 합을 구하라. (y, x) : y행 x열
 * Ex) (2, 2) ~ (3, 4)	
		1	2	3	4
		2	3	4	5	--> 3 4 5
		3	4	5	6		4 5 6
		4	5	6	7
		 => 합 = 3+4+5+4+5+6 = 27.
[입력]
1) 크기 N <공백> 합 구하는 횟수 M. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 
N개 줄) N*N 행렬 (<= 1,000)
M개 줄) 네 개의 정수 y1, x1, y2, x2. (y2 >= y1, x2 >= x1)

[출력]
M개 줄) (y1, x1) ~ (y2, x2)의 구간합.
 */
public class Main_11660_구간합구하기5_박봉균 {
	/** 누적합(인덱스: 1~N) */
	static int[][] preSum;
	/** 행렬 크기 N */
	static int N;
	/** 합 구하는 횟수 M */
	static int M;
	/** 구간합 구하기 위한 네 좌표 (y:행, x: 열)*/
	static int y1, x1, y2, x2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행렬크기 N, 합 구하는 횟수 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//행렬 입력 -> 누적합 초기값으로 저장(인덱스: 1 ~ N)
		preSum = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				preSum[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//누적합(인덱스: 1 ~ N) 구하기 
		// (y,x) + (y-1,x) + (y,x-1) - (y-1,x-1)
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				preSum[i][j] = preSum[i][j] + preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1];
				//System.out.printf("(%d,%d) 누적합 : %d\n", i, j, preSum[i][j]);
			}
		}
		
		//M개의 테스트 케이스에 대하여
		int ret = 0;
		for(int t = 1; t <= M; t++) { 
			//네 좌표 입력
			st = new StringTokenizer(br.readLine());
			y1 = Integer.parseInt(st.nextToken());
			x1 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			
			//구간합 구하기
			//(y2,x2)누적합  - (y1-1,x2)누적합 - (y2,x1-1)누적합 + (y1-1,x1-1)누적합 
			ret = preSum[y2][x2] - preSum[y1-1][x2] - preSum[y2][x1-1] + preSum[y1-1][x1-1];
			bw.write(String.valueOf(ret) + '\n');
		}
		
		//남은 데이터 출력 및  stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
