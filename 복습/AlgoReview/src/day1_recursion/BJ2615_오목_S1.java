package day1_recursion;

import java.io.*;
import java.util.*;

/* <시간제한: 1초> 
 * 바둑판: 가로줄(위부터 1~19번), 세로줄(왼쪽부터 1~19번)
 * 가로, 세로, 대각선 방향 5알 연속 -> 승리. 6알 이상이 연속적 -> X
 * 바둑판의 상태가 주어질 때, 검은색이 이겼는지, 흰색이 이겼는지 또는 아직 승부가 결정되지 않았는지를 판단하시오.
[입력]
19*19 행렬(검:1, 흰:2, 빈칸:0, delim:공백)
[출력]
1) 검 승: 1, 흰 승: 2, 승부 결정 X: 0
2) 검/흰 이겼을 경우: 가장 왼쪽에 있는 바둑알(세로: 가장 위)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.
 */
class BJ2615_오목_S1 {
	/** 북동, 동, 남동, 남쪽 방향만 확인한다 */
	static final int[] dy = {-1, 0, 1, 1};
	static final int[] dx = {1, 1, 1, 0};
	
	/** 바둑판 정보 행렬 */
	static int[][] matrix = new int[19][19];
	
	/** 방문 체크 배열(좌표+방향+색) -> 6목 방지 */ 
	static boolean visited[][][][] = new boolean[19][19][4][4];
	
	/** 해당 시작점, 방향에 대하여 5개 연속인지 확인한다 */
	static int dfs(int y, int x, int d, int now, int cnt) {
		visited[y][x][d][now] = true;
		int ny = y + dy[d];
		int nx = x + dx[d];
		
		//오버언더 체크, 같은지 체크
		if (ny < 0 || ny >= 19 || nx < 0 || nx >= 19 || matrix[ny][nx] != now) {
			if (cnt == 5) {
				return now;
			}
			else {
				return 0;
			}
		}
		return dfs(ny, nx, d, now, cnt + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//바둑판 행렬 입력
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		
		//열 우선 (북동쪽인 경우, 왼쪽을 먼저 탐색해야 하기 때문!)
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 19; y++) {
				int now = matrix[y][x];
				if (now == 0) 
					continue;
				for (int d = 0; d < 4; d++) {
					//이미 확인했으면 넘어가기
					if (visited[y][x][d][now])
						continue;
					if (dfs(y, x, d, now, 1) != 0) {
						System.out.printf("%d\n%d %d\n", matrix[y][x], y + 1, x + 1);
						System.exit(0);
					}
				}
			}
		}

		System.out.println("0");
		br.close();
	}
}

