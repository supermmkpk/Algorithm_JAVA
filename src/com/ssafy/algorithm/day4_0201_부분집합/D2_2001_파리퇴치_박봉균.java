package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 2001 [D2] : 파리 퇴치
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: 완탐. 파리채 크기에 해당하는 모든 부분행렬을 탐색하고 합의 최대를 구한다.
 * 결과: Pass, 0.10650s
 */

/*
 * N*N 배열 안의 숫자는 해당 영역에 존재하는 파리의 개수를 의미한다.
 * M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
 * 죽은 파리의 개수를 구하라.
[입력]
1) 테스트 케이스 수 T
각 테케] 1) N, M. (5 <= N <= 15, 2 <= M <= N)
		N개 줄) N*N 행렬. (각 영역 <= 30)


[출력]
각 줄) #테케번호 <공백> M*M최대파리수
 */
public class D2_2001_파리퇴치_박봉균 {
	/** 행렬 크기 N */
	static int N;
	/** 파리채 크기 M */
	static int M;
	/** 주어지는 행렬 */
	static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테스트케이스 수 입력
		
		//T개의 테스트케이스에 대하여
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			//행렬 크기 N, 파리채 크기 M 입력
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			matrix = new int[N][N]; //matrix[][] 동적할당
			
			//N*N 행렬 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//파리채 크기로 모든 경우 완탐하며 합의 최대를 구합니다.
			int ret = Integer.MIN_VALUE; //결과 저장 변수
			for(int i = 0; i <= N - M; i++) {
				for(int j = 0; j <= N - M; j++) {
					ret = Math.max(ret, sum(i, j));
				}
			}
			//출력 조건에 따라 출력
			System.out.println("#" + t + " " + ret);
		}
	}
	
	/**
	 * 특정 좌표에서 시작하여 특정 크기로 탐색하고 합을 반환합니다.
	 * @param sy : 시작 행
	 * @param sx : 시작 열
	 */
	static int sum(int sy, int sx) {
		int sum = 0;
		
		//파리채 크기의 부분행렬에 대하여 원소들의 총합을 구하고 반환합니다.
		for(int i = sy; i < sy + M; i++) {
			for(int j = sx; j < sx + M; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}
}
