package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ4963 [S2] : 섬의 개수
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 *
 * 아이디어: connected component -> DFS!!
 * 결과: 13480KB, 116ms
 */

/* <요약>
 * 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
 * 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
 * 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
 * 정사각형으로 이루어져 있는 섬과 바다 지도가 주어질 때, 섬의 개수를 세자.
[입력]
각 테케] 1) 지도의 너비 w, 높이 h가 주어진다. (0 < w,h <= 50)
    h개 줄) 지도 행렬(1: 땅, 0: 바다)
전체 입력의 마지막 줄] 0 0
[출력]
각 테케) 섬의 개수 <개행>
 */
public class Main_4963_섬의개수_박봉균 {	
	/** 8방 방향벡터 */
	public static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static final int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	/** 행 크기 H, 열 크기 W */
	static int H, W;
	/** 지도 행렬 */
	static int[][] matrix;
	/** 방문 체크 배열 */
	static boolean[][] visited;
	/** 결과 변수: 섬의 개수 */
	static int ret;
	
	/** DFS 탐색 */
	static void dfs(int y, int x) {
		//방문 체크
		visited[y][x] = true;
		
		for(int d = 0; d < 8; d++) {
			//다음 방향
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			//오버,언더(벗어나는지) 체크
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) 
				continue;
			 
			//갈 수 있다면 간다(탐색한다)
			if(matrix[ny][nx] == 1 && visited[ny][nx] == false)
				dfs(ny, nx);
		}
	}

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while(true) {
			//초기화!!
			ret = 0;
			st = new StringTokenizer(br.readLine());
			
			//H, W 입력, 동적할당 (0 0이면 종료)
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(H == 0 && W == 0) 
				break;
			matrix = new int[H][W];
			visited = new boolean[H][W];
			
			//지도 정보 입력
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) 
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//DFS 탐색하고 connected component 개수 구하기
			//방문체크 하므로 탐색의 횟수가 connected component 개수가 된다.
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(matrix[i][j] == 1 && visited[i][j] == false) {
						dfs(i, j);
						++ret;
					}
				}
			}
			
			//섬의 개수 출력
			bw.write(String.valueOf(ret) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
