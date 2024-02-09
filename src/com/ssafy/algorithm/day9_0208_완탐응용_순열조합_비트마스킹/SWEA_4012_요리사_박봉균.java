package com.ssafy.algorithm.day9_0208_완탐응용_순열조합_비트마스킹;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * SWEA 4012 : 요리사
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 조합(비트마스킹) + 순열(이중for문) -> A,B요리맛 구하고 뺀 값의 절댓값의 최소 구하기
 * 결과: Pass, 0.14795s
 */

/*
 * 두 명의 손님에게 음식을 제공한다.
 * 최대한 비슷한 맛의 음식을 만들어 내야 한다.
 * N개의 식재료들을 N/2개씩 나누어 두 개의 요리를 하려 한다. (N은 짝수)
 * 각각을 A음식, B음식이라 하자.
 * 비슷한 음식: |A음식맛 - B음식맛|이 최소가 되도록 재료를 배분.
 * 식재료 i는 j와 같이 요리하면 시너지 Sij가 발생. (1 ≤ i ≤ N, 1 ≤ j ≤ N, i ≠ j)
 * 음식의 맛 = 조합의 시너지 Sij의 합.
 * Sij가 주어지고, A와 B음식을 만들 때, 맛의 차이가 최소가 되는 경우를 찾고 그 최솟값을 출력하라. 
[입력]
1) 테케 T.
각 테케] 1) 식재료의 수 N.
    N개 줄) N * N 행렬. (i, j 같은 경우: 0)
[출력]
#테케번호 <공백> 맛차이최솟값
*/
public class SWEA_4012_요리사_박봉균 {                                                                           
	/** 식재료의 수 N */
	static int N;
	/** 시너지 값 행렬 */ 
	static int[][] matrix; 
	/** 결과 변수 : 맛 차이의 최소 */
	static int ret = Integer.MAX_VALUE;
	/** A, B의 재료 조합 배열 */
	static int numsA[], numsB[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테케 수 T 입력
		
		//T개의 테케에 대하여
		for(int t = 1; t <= T; t++) { 
			//초기화
			ret = Integer.MAX_VALUE;
			
			//식재료 수 N 입력
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N]; //matrix[][] 동적할당
			
			//matrix[][] 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//조합 배열 동적할당 및 조합을 구하여 음식맛 차이의 최솟값을 구합니다.
			numsA = new int[N/2];
			numsB = new int[N/2];
			combi(0, 0, 0);
			
			//음식맛 차이의 최솟값 출력
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');
		}
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** C(N, N/2) - 비트마스킹 */
	static void combi(int cnt, int start, int visited) {
		//종료조건
		if(cnt == N/2) { 
			int idxA = 0, idxB = 0;
			int tasteA = 0, tasteB = 0; //A,B 음식맛
			for(int i = 0; i < N; i++) {
				if((visited & 1 << i) != 0) { //1이라면 A의 재료입니다.
					numsA[idxA++] = i;
					tasteA = permu(numsA); //재료 내에서 시너지의 합을 구합니다.
				}
				else {
					numsB[idxB++] = i; //0이라면 A의 재료입니다.
					tasteB = permu(numsB); //재료 내에서 시너지의 합을 구합니다.
				}
			}
			ret = Math.min(ret , Math.abs(tasteA - tasteB)); //음식맛 차이의 최소
			return;
		}
		
		//조합 구하기
		for(int i = start; i < N; i++)
			combi(cnt + 1, i + 1, visited | 1 << i);
	}
	
	/** P(N,2) - 이중for */
	static int permu(int[] input) {
		int sum = 0; //시너지의 합
		
		//주어진 조합에서 순열을 구하고 시너지의 합을 구합니다.
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				if(i == j)
					continue;
				sum += matrix[input[i]][input[j]];
			}
		}
		return sum; //시너지 합 반환
	}
}
