package com.ssafy.algorithm.day2_0130_완탐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * BJ_11660[S1] : 구간합 구하기 5
 * 
 */
public class reviewPreSum {

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
