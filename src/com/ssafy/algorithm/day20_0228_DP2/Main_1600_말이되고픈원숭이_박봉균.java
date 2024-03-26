package com.ssafy.algorithm.day20_0228_DP2;
import java.io.*;
import java.util.*;

/**
 * 
 * @author 박봉균
 * 아이디어: 단순 BFS 시, 시간초과
 *     ==> DP+BFS : visited 배열(방문체크+거리)에 움직임 K 메모이제이션
 * 결과: 52976KB, 492ms
 */


/* <BJ1600_G3 : 말이 되고픈 원숭이>
 * 원숭이는 말의 움직임을 유심히 살펴보고 그대로 따라 하기로 하였다.
 * 말: 격자판에서 체스의 나이트와 같은 이동방식. 장애물을 뛰어넘을 수 있다.
 * 원숭이: K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 대각X
 * 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다 => 가중치1(동일) ==> BFS!!
 * (0,0) -> (W-1, H-1) 최단거리는?
[입력]
1) K (0 <= K <= 30)
2) 열크기 W, 행크기 H. (1 <= W,H <= 200)
H개 줄) 행렬 (0:빈칸, 1: 장애물) 
[출력]
원숭이의 동작수의 최솟값을 출력한다. (갈 수 없는 경우: -1)
 */
public class Main_1600_말이되고픈원숭이_박봉균 {
	/** 12방 방향벡터. (0~3: 상하좌우, 4~11: 말)  */
	static final int[] dy = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static final int[] dx = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1}; 
	
	static int K, W, H, visited[][][], matrix[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine()); //말처럼 이동가능 횟수
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); //열 크기
		H = Integer.parseInt(st.nextToken()); //행 크기
		matrix = new int[H][W];
		visited = new int[H][W][K + 1]; //방문체크+거리
		
		//격자판 입력
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) 
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
			
		//BFS 탐색하고 최단 경로를 출력
		bw.write(String.valueOf(bfs(0, 0)) + '\n');		
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** BFS 탐색. visited 배열에 움직임 횟수 인덱스 추가 */
	static int bfs(int sy, int sx) {
		Queue<Tuple> q = new ArrayDeque<>();
		
		visited[sy][sx][K] = 1;
		q.add(new Tuple(sy, sx, K));
		
		while(!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.peek().x;
			int k = q.remove().k;
			if(y == H - 1 && x == W - 1) //탐색 성공
				return visited[y][x][k] - 1; //거리 반환
				
			for(int d = 0; d < 12; d++) {				
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= H || nx < 0 || nx >= W) //오버,언더 체크
					continue;
				
				if(matrix[ny][nx] == 0) { //방문한 적이 없다면(갱신X)
					//말처럼 움직일 수 있다면 k감소, 방문
					if(4 <= d && k > 0 && visited[ny][nx][k - 1] == 0) {
						visited[ny][nx][k - 1] = visited[y][x][k] + 1;
						q.add(new Tuple(ny, nx, k - 1));
					}
					//이외의 경우, 인접한 상하좌우 방문, k그대로
					else if(d < 4 && visited[ny][nx][k] == 0) {
						visited[ny][nx][k] = visited[y][x][k] + 1;
						q.add(new Tuple(ny, nx, k));
					}
				}
			}
		}
		return visited[H - 1][W - 1][0] - 1;
	}
	
	
	
    
	static class Tuple {
		int y, x, k;
		Tuple(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
	}
}
