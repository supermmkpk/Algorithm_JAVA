package com.ssafy.algorithm.day13_0216_그래프2_BFS;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ4963 [S2] : 섬의 개수
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: connected component 구하는 문제!
 *     ==> connected component -> DFS이지만, BFS로도 가능하다. 다만, 코드가 덜 간결하다.
 * 결과: 13664KB, 128ms
 */

/* <요약> (시간: 1초)
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
	/** 8방 방향벡터 : 위, 오위, 오, 오아래, 아래, 왼아래, 왼, 왼위 */
	public static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	/** 지도의 행 크기 H, 열 크기 W */
	static int H, W;
	/** 지도 행렬 */
	static int[][] matrix;
	/** 방문체크 배열 */
	static boolean[][] visited;
	/** 결과 변수 : 섬의 개수 */
	static int ret;
	/** BFS용 큐 */
	static Queue<Pair> q = new ArrayDeque<>();
	
	/** BFS */
	static void bfs(int sy, int sx) {
		visited[sy][sx] = true;
		q.add(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			int y = q.peek().first;
			int x = q.remove().second;
			
			for(int d = 0; d < 8; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= H || nx < 0 || nx >= W)
					continue;
				
				if(matrix[ny][nx] == 1 && visited[ny][nx] == false) {
					visited[ny][nx] = true;
					q.add(new Pair(ny, nx));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//입력으로 0 0 들어올 때까지 각 테케에 대하여
		while(true) {
			//초기화!!
			ret = 0;
			st = new StringTokenizer(br.readLine());
			
			//지도의 열 크기 W, 행 크기 H 입력, 동적 할당. 0 0 들어오면 종료.
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W == 0 && H == 0)
				break;
			matrix = new int[H][W];
			visited = new boolean[H][W];
			
			//지도 정보 입력
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) 
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//각 점에서 BFS 수행. BFS탐색 후 돌아오면 섬 개수 증가. 
			//visited 체크하기 때문에 BFS 탐색 횟수는 connected component(섬 개수)와 같다.
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(matrix[i][j] == 1 && visited[i][j] == false) {
						bfs(i, j);
						++ret;
					}
				}
			}
			//해당 테케 섬의 개수 출력, 개행
			bw.write(String.valueOf(ret) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 좌표룰 큐에 넣기 위한 Pair 클래스 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
