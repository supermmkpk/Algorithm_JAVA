package com.ssafy.algorithm.day1_0129_재귀;

import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * @since JDK17
 */

/*
 * 가로줄과 세로줄에 1~19까지 번호가 붙어있는 오목판에 흰 바둑알과 검정
 * 바둑알을 교대로 놓아서 먼저 정확히 5개의 같은 색 알이 연속적으로 일직선(직선, 대각선포함)을 이루면 이기는 게임이다. 
 * 하지만 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다.
 * 입력으로 주어진 바둑알의 정보를 체크하여 검정과 흰색 바둑알 중 어떤 색의 바둑알이
 * 이겼는지, 또는 승부가 결정되지 않았는지를 판단하는 프로그램을 작성하세요.(동시에 이기는경우는 없다)
[입력]
검은 바둑알: 1, 흰 바둑알: 2, 알이 놓이지 않는 자리: 0으로 표시 
[출력]
1) 승부가 결정되지 않았다면: 0, 검은 색이 이기면: 1, 흰 색이 이기면: 2
승부가 결정 -> 2) 이긴 5개의 바둑알 중 가장 왼쪽/위에 있는 바둑알의 (가로줄, 세로줄) 번호를 순서대로 출력한다.
(대각선이나 가로: 왼쪽, 세로: 위쪽)
 */
public class BJ_2615_오목_박봉균 {
	//8방 방향벡터
	public static final int[] dy = {-1,-1,0,1,1,1,0,-1};
	public static final int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int N = 19;
	static int[][] matrix = new int[N][N];
	static int retY, retX; //결과 좌표
	
	static int dfs(int sy, int sx) {
		if(matrix[sy][sx] == 0) {
			return 0;
		}
		for(int d = 0; d < 8; d++) {//8방향에 대하여
			int ny = sy + dy[d];
			int nx = sx + dx[d];
			
			int Bcnt = 0; //검은색 카운트
			int Wcnt = 0; //흰색 카운트
			int cur = matrix[sy][sx];
			int py = sy - dy[d];
			int px = sx - dx[d];
			if(py >= 0 && py < N && px >= 0 && px < N) {
				if(matrix[py][px] == cur) {
					break;
				}
			}
			for(int i = 0; i < 4; i++) {
				System.out.printf("(%d, %d), Bcnt: %d, Wcnt: %d, sy: %d, sx: %d\n", ny, nx, Bcnt, Wcnt, sy, sx);	
				if(ny < 0 || ny >= N || nx < 0 || nx >= N) //오버,언더 체크
					break;
				/*if(matrix[prevY][prevX] != matrix[ny][nx]) { //색 다르면 초기화 후 계속 진행
					Bcnt = 0; 
					Wcnt = 0;
					retY = ny;
					retX = nx;
				}*/
				if(matrix[ny][nx] == cur) {
					if(cur == 1) { //검은 바둑알
						Bcnt++; //카운트 증가
					}
					else if(cur == 2) { //흰 바둑알
						Wcnt++; //카운트 증가
					}
				}
				int nextY = ny+dy[d];
				int nextX = nx+dx[d];
				if(Bcnt == 4) { 
					retY = sy;
					retX = sx;
					if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) //오버,언더 체크
						return 1;
					if(matrix[nextY][nextX] != cur)
						return 1;
				}
				else if(Wcnt == 4) { 
					retY = sy;
					retX = sx;
					if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) //오버,언더 체크
						return 2;
					if(matrix[nextY][nextX] != cur)
						return 2;
				}
				
				//갱신
				ny = ny + dy[d];
				nx = nx + dx[d];
			}
			
		}
		return 0; //먼저 5개 되는 경우 없으면 0 반환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		//각 좌표에 대하여 탐색, 이기는 경우 나오면 출력 후 종료
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				result = dfs(i,j);
				if(result == 0) {
					continue;
				}
				else if(result == 1) {
					System.out.printf("%d\n%d %d\n", result, retY+1, retX+1);
					return;
				}
				else if(result == 2) {
					System.out.printf("%d\n%d %d\n", result, retY+1, retX+1);
					return;
				}
			}
		}
		if(result == 0) {
			System.out.println(0);
		}
	}
}

/*
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0
0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 2 2 2 2 2 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 */

