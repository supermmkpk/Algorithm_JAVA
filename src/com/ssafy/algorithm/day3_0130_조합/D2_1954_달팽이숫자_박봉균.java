package com.ssafy.algorithm.day3_0130_조합;
import java.util.*;

/**
 * <pre>
 * SWEA 1954 [D2] : 달팽이 숫자
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: N*N까지 재귀적으로 4방 탐색을 한다.
 * 결과: Pass
 */

/* <문제 요약>
 * 달팽이: 1 ~ N*N까지 숫자가 시계방향으로 이루어져 있다.
 * 정수 N을 입력 받아 달팽이를 출력하라.
[입력]
1)테케 개수 T
T개 줄) N. (1 ≤ N ≤ 10)

[출력]
각 테케) #테케번호(1부터) <개행>
  		달팽이 행렬 출력
*/
public class D2_1954_달팽이숫자_박봉균 {
	/** 방향벡터(오른->아래->왼->위) */
	public static final int[] dy = {0, 1, 0, -1};
	public static final int[] dx = {1, 0, -1, 0};
	
	/** 달팽이를 저장하는 2차원 배열 */
	static int [][] matrix;
	/**달팽이 크기 N */ 
	static int N;
	/** 달팽이 현재 수를 나타내는 cnt */
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 개수 T 입력
		int T = sc.nextInt();
		
		//T개의 테스트 케이스에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			N  = sc.nextInt();
			matrix = new int[N][N]; //matrix[][] 동적 할당

			matrix[0][0] = 1; //달팽이의 초기값
			cnt = 1; //시작하는 수: 1
			
			//달팽이 숫자를 만듭니다
			snail(0,0);
			
			//달팽이 숫자를 출력합니다.
			System.out.println("#" + t);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) 
					System.out.print(matrix[i][j] + " ");
				System.out.println();
			}
		}
	}
	
	/**
	 * 달팽이 모양으로 재귀적으로 탐색 함수(오른->아래->왼->위)
	 * @param y : 시작 행번호
	 * @param x : 시작 열번호
	 */
	static void snail(int y, int x) {
		//종료조건!! : N*N 도달 시, 종료
		if(cnt == N * N) 
			return;
		
		for(int d = 0; d < 4; d++) { //4방향에 대해 
			while(true) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || ny >= N || nx < 0 || nx >=  N || matrix[ny][nx] != 0) //오버,언더, 방문했는지 검증(0 아니면 다른 방향)
					break;
				matrix[ny][nx] = ++cnt; //검증 되었으면 값 저장(전위 증가)
				//검증 후 y,x 값 갱신
				y = ny;
				x = nx;
			}
		}
		snail(y, x); //4방향 다 돌면, 같은 과정 반복(다시 4방 탐색)
	}
}
