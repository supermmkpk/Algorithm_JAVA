package com.ssafy._1일1알골.algo_0123;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ17829 : 222-풀링
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72258829"></a>
 *
 * 결과: "맞았습니다!!", 103180KB, 680ms
 */

/*
 * 1. 행렬을 2×2 정사각형으로 나눈다.
 * 2. 각 정사각형에서 2번째로 큰 수만 남긴다. ( a4 ≤ a3 ≤ a2 ≤ a1 중 a2)
 * 2번 과정에 의해 행렬의 크기가 줄어들게 된다.
 * 이 과정을 반복했을 때, 마지막 남은 1×1의 값을 구하라.
[입력]
1) N(2 ≤ N ≤ 1024) (N=2^K, 1 ≤ K ≤ 10)
N개 줄)각 행의 원소 N개 (-10,000 <= <= 10,000) 

[출력]
마지막에 남은 수
 */
public class BJ_17829_222풀링_박봉균 {
	public static final int[] dy = {0, 0, 1, 1};
	public static final int[] dx = {0, 1, 1, 0};
	
	/**
	 * 2*2로 나눈 각 구역에서 2번째 큰수를 구하고 2번째로 큰 수만 남긴 행렬을 반환하는 함수.
	 * @param matrix : 시작 행렬
	 * @return result : 2번째 큰 수만 남긴 행렬
	 */
	static int[][] pull(int[][] matrix) {
		//행렬의 한변의 길이를 담는 변수입니다.
		int n = matrix[0].length;
		//종료 조건!
		if(n == 1) { //1×1이면 현재 행렬을 반환하고 종료합니다.
			return matrix;
		}
		//결과를 담을 2차원 배열입니다.
		int[][] result = new int[n/2][n/2];
		for(int y = 0; y < n; y += 2) { //2y행에 대하여
			for(int x = 0; x < n; x += 2) { //2x열에 대하여
				//2*2구역의 4개의 수를 담을 임시 배열입니다.
				int[] nums = new int[4];
				for(int d = 0; d < 4; d++) { //4개 수에 대하여
					int ny = y + dy[d];
					int nx = x + dx[d];
					nums[d] = matrix[ny][nx]; //임시 배열에 저장합니다.
				}
				Arrays.sort(nums); //임시 배열을 정렬합니다.
				result[y/2][x/2] = nums[2]; //2번째 큰수를 결과 배열에 저장합니다.
			}
		}
		return pull(result); //구한 결과 행렬에 대하여 같은 과정을 수행합니다.
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//한 변의 길이 N을 입력 받습니다.
		int N = Integer.parseInt(br.readLine()); 
		
		//입력 받을 N*N 행렬을 저장하는 2차원 배열을 생성합니다.
		int[][] inMatrix = new int[N][N];
		for(int i = 0; i < N; i++) { //N개 행에 대하여
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				inMatrix[i][j] = Integer.parseInt(st.nextToken()); //각 열의 값을 저장합니다.
			} 
		}
		
		//222풀링을 수행합니다.
		int[][] ret = pull(inMatrix);
		//마지막 남은 수를 출력합니다.
		System.out.println(ret[0][0]);
		
		br.close(); //stream을 닫습니다.
	}
}
