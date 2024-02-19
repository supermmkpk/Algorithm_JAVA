package com.ssafy._1일1알골.algo_0219;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ10026 [G5] : 적록색약
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: connected component -> DFS, 적록색약 여부에 따라 탐색 로직 다르게
 * 결과: 12656KB, 96ms
 */

/* <요약>
 * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다
 * N×N인 그리드의 각 칸에 R, G, B 중 하나를 색칠한 그림이 있다. 
 * 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 
 * 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
 * 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하시오.
[입력]
1) N이 주어진다. (1 ≤ N ≤ 100)
N개 줄) 그림 행렬.(R, G, B)
[출력]
적록색약이 아닌 사람이 봤을 때의 구역의 개수 <공백> 적록색약인 사람이 봤을 때의 구역의 수
 */
public class Main_10026_적록색약_박봉균 {
	/** 4방 방향벡터 */
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	/** 행렬 크기 */
	static int N;
	/** 행렬 */
	static char[][] matrix;
	/** 방문체크 배열 */
	static boolean[][] visited;
	/** 결과 변수 : retNotBlind : 색약X, retBlind: 색약 */
	static int retNotBlind, retBlind;
	
	/** 색약X DFS */
	static void dfsNotBlind(int y, int x) {
		visited[y][x] = true;
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= N || nx < 0 || nx >= N)
				continue;
			
			//색 같은 구역 탐색
			if(matrix[ny][nx] == matrix[y][x] && visited[ny][nx] == false) {
				dfsNotBlind(ny, nx);
			}
		}
	}
	
	/** 색약 DFS */
	static void dfsBlind(int y, int x) {
		visited[y][x] = true;
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= N || nx < 0 || nx >= N)
				continue;
			
			//색 같은 구역 탐색하되, R, G는 같은 구역으로 본다.
			if((matrix[ny][nx] == matrix[y][x] ||
					(matrix[ny][nx] == 'R' && matrix[y][x] == 'G') || (matrix[ny][nx] == 'G' && matrix[y][x] == 'R')) 
				&& visited[ny][nx] == false) {
				dfsBlind(ny, nx);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//행렬 크기 N 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		matrix = new char[N][N];
		visited = new boolean[N][N];
		
		//색 정보 행렬 입력
		for(int i = 0; i < N; i++) {
			String given = br.readLine();
			for(int j = 0; j < given.length(); j++)
				matrix[i][j] = given.charAt(j);
		}
		
		//색약X DFS
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] == false) {
					dfsNotBlind(i, j);
					++retNotBlind; //구역 개수 체크
				}
			}
		}
		
		//방문 체크 배열 다시 초기화
		for(int i = 0; i < visited.length; i++)
			Arrays.fill(visited[i], false);
		
		//색약 DFS
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] == false) {
					dfsBlind(i, j);
					++retBlind; //구역 개수 체크
				}
			}
		}
		
		//색약 여부에 따라 볼 수 있는 영역 출력
		bw.write(String.valueOf(retNotBlind) + " ");
		bw.write(String.valueOf(retBlind) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}

}
