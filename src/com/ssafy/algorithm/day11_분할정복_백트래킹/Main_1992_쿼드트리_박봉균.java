package com.ssafy.algorithm.day11_분할정복_백트래킹;
import java.io.*;

/**
 * <pre>
 * BJ1992 [S1] : 쿼드 트리
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 압축 조건 차근차근 읽어보고 재귀함수 구현. 출력도 구현부에서 함께 고려
 * 결과: 12604KB, 84ms
 */

/* <문제 요약>
 * 흰 점 0, 검은 점 1로 이루어진 N*N 행렬에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 이를 압축하여 간단히 표현할 수 있다.
 * 행렬이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 1로만 되어 있으면 압축 결과는 "1"이 된다. 
 * 0과 1이 섞여 있으면 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개로 나누어 압축하게 되며, 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
 * 압축한 결과를 출력하자.
[입력]
1) N. (2의 제곱수이며, 1 ≤ N ≤ 64)
N개 줄) 길이 N 문자열. (0 또는 1)
[출력]
압축한 결과.
 */
public class Main_1992_쿼드트리_박봉균 {
	/** 행렬 크기 N */
	static int N;
	/** 주어지는 행렬 */
	static int[][] matrix;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//행렬 크기 N 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		
		//행렬 입력
		for(int i = 0; i < N; i++) {
			String given = br.readLine();
			for(int j = 0; j < N; j++)
				matrix[i][j] = given.charAt(j) - '0'; 
		}
		
		//압축하기
		go(N, 0, 0);
		
		//압축 결과 출력
		System.out.println(sb);
		br.close();
	}
	
	/**
	 * 압축을 진행하는 함수
	 * @param size : 현재 크기
	 * @param sy : 시작 행 인덱스
	 * @param sx : 시작 열 인덱스
	 */
	static void go(int size, int sy, int sx) {
		//종료조건1: 크기가 1
		if(size <= 1) {
			sb.append(matrix[sy][sx] == 0 ? 0 : 1);
			return;
		}
		
		//색깔 체크
		boolean white = false;
		boolean black = false;
		for(int i = sy; i < sy + size; i++) {
			for(int j = sx; j < sx + size; j++) {
				if(matrix[i][j] == 0)
					white = true;
				else
					black = true;
			}
		}
		
		//종료조건2: 다 0이거나 1
		if(!(white && black)) {
			sb.append(white ? 0 : 1);
			return;
		}
		
		//섞여있으면 재귀 들어가고: '('
		sb.append('(');
		
		//왼쪽 위,
		go(size/2, sy, sx);
		//오른쪽 위
		go(size/2, sy, sx + size/2);
		//왼쪽 아래
		go(size/2, sy + size/2, sx);
		//오른쪽 아래
		go(size/2, sy + size/2, sx + size/2);
		
		//나갈 때: ')' 
		sb.append(')');
	}
}
