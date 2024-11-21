package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ_10026[G5] : 적록색약
 * connected component -> DFS
 */
public class reviewDFS_ConnectedComponent {	 
	//적록색약 여부에 따라 탐색 로직 다르게
	 
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

