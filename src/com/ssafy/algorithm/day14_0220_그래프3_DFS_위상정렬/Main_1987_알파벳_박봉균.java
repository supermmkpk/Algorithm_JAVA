package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1987 [G4] : 알파벳
 * </pre>
 * @author 박봉균
 *
 * 아이디어: DFS 탐색, 알파벳 방문 체크(최대 체킹 목적이므로 return하면 원복) -> 매개변수로 비트마스킹
 * 결과: 	11948KB, 712ms
 */

/* <요약> 
 * R*C 행렬 보드가 있다. 
 * 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸(1행 1열)에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 말이 최대한 몇 칸을 지날 수 있는지를 구하보자. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
[입력]
1) R, C. (1 ≤ R,C ≤ 20
R개 줄) C개의 대문자 알파벳들이 빈칸 없이 주어진다.
[출력]
말이 지날 수 있는 최대의 칸 수
 */
public class Main_1987_알파벳_박봉균 {
	/** 4방 방향벡터 */
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	/** 행크기 R, 열크기 C */
	static int R, C;
	/** 주어지는 행렬 */
	static char[][] matrix;
	/** 결과 변수 : 최대 이동 칸수 */
	static int ret = Integer.MIN_VALUE;
	
	/**
	 * DFS로 탐색하며 최대 이동 칸수를 구하는 함수
	 * @param y : 행
	 * @param x : 열
	 * @param cnt : 이동 칸 수
	 * @param visited : 알파벳 방문 여부(비트마스킹)
	 */
	static void dfs(int y, int x, int cnt, int visited) {
		//이동 횟수의 최대 갱신
		ret = Math.max(ret, cnt); 
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			
			//해당 알파벳 방문하지 않았다면 계속해서 탐색
			if((visited & 1 << (matrix[ny][nx] - 'A')) == 0) {
				//이동 횟수 증가, 알파벳 방문 여부 체크하면서 다음 탐색
				//전체 탐색이 아니라 최대 이동 칸수를 구하는 것이기 때문에 visited 오염되면 안된다.
				dfs(ny, nx, cnt + 1, visited | 1 << (matrix[ny][nx] - 'A')); 
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행크기 R, 열크기 C 입력, 동적할당
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		matrix = new char[R][C];
		
		//행렬(보드) 정보 입력
		for(int i = 0; i < R; i++) {
			String given = br.readLine();
			for(int j = 0; j < C; j++) 
				matrix[i][j] = given.charAt(j);
		}
		
		//DFS 탐색, 최대 이동 칸수 기록
		dfs(0, 0, 1, 1 << (matrix[0][0] - 'A'));
		
		//최대 이동 칸수 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
