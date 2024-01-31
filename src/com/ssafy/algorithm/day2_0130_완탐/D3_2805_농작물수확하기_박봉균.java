package com.ssafy.algorithm.day2_0130_완탐;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 2805 [D3] : 농작물 수확하기
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 가운데 행 고정, 각 열 탐색. dy 방향벡터로 위 아래 탐색. 분기점 기준으로  위 아래 탐색 범위 증가->감소.
 * 결과: Pass
 */

/* 문제 요약
 * N X N 크기 농장(N은 홀수, <= 49)
 * 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능
 * N과 농작물의 가치(0~5)가 주어질 때, 얻을 수 있는 수익은 얼마인지 구하라.
[입력]
1) 테케 개수 T
각 테케) N <개행> 농작물의 가치(행렬)

[출력]
#테케번호 <공백> 수익
 */
public class D3_2805_농작물수확하기_박봉균 {
	/** 농장 한변 길이 N */ 
	static int N;
	/** 농장 정보(가치) 행렬 */
	static int[][] matrix;
	/** 방향 벡터(위아래) */
	public static final int[] dy = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 입력
		
		for(int t = 1; t <= T; t++) { //T개의 테스트 케이스에 대하여
			//초기화!!
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N]; //matrix[][] 동적 할당
			for(int i = 0; i < N; i++) {
				String given = br.readLine();
				for(int j = 0; j < N; j++)
					matrix[i][j] = given.charAt(j) - '0'; //농장 정보 입력
			}
			
			int half = N / 2; //분기점
			int y = half; //행 고정(가운데) -> 행은 고정하고 열 별로 위아래 방향벡터 이용하여 탐색!
			int n = 0; //위아래 탐색의 범위
			int sum = 0; //수익의 합
			for(int x = 0; x < N; x++) {
				sum += matrix[y][x]; //해당 행, 열의 가치를 수익에 더합니다 
				//분기점 이전이라면 위아래 탐색의 범위 증가(n 증가)
				if(x < half) { 
					for(int i = 1; i <= n; i++) { //각 범위에 대해
						for(int d = 0; d < 2; d++) { //위와 아래 방향에 대하여
							int ny = y + i * dy[d]; //방향벡터에 범위를 곱합니다
							sum +=  matrix[ny][x]; //해당 칸의 가치를 수익에 더합니다
						}
					}
					n++; //범위 증가
				}
				//분기점 이후라면 위아래 탐색의 범위 감소(n 감소)
				else {
					for(int i = 1; i <= n; i++) { //각 범위에 대해
						for(int d = 0; d < 2; d++) { //위와 아래 방향에 대하여
							int ny = y + i * dy[d]; //방향벡터에 범위를 곱합니다
							sum += matrix[ny][x]; //해당 칸의 가치를 수익에 더합니다
						}
					}
					n--; //범위 감소
				}
			}
			//출력 조건에 따라 총 수익 출력
			bw.write("#" + t + " " + sum + '\n');
		}
		
		//남은 데이터를 출력하고, stream을 닫습니다.
		bw.flush(); 
		bw.close();
		br.close();
	}
}
