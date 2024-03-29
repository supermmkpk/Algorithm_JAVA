package com.ssafy.Main;
import java.io.*;
import java.util.*;

/*
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
public class Main {
	/** 12방 방향벡터. (0~3: 상하좌우, 4~11: 말)  */
	static final int[] dy = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static final int[] dx = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1}; 

	static int K, W, H, visited[][][], matrix[][];

	static int bfs(int sy, int sx) {
		Queue<Tuple> q = new ArrayDeque<>();
		
		visited[sy][sx][K] = 1;
		q.add(new Tuple(sy, sx, K));
		
		while(!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.peek().x;
			int k = q.remove().k;
			if(y == H - 1 && x == W - 1) 
				return visited[y][x][k] - 1;
				
			for(int d = 0; d < 12; d++) {				
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= H || nx < 0 || nx >= W)
					continue;
				
				if(matrix[ny][nx] == 0) {
					if(4 <= d && k > 0 && visited[ny][nx][k - 1] == 0) {
						visited[ny][nx][k - 1] = visited[y][x][k] + 1;
						q.add(new Tuple(ny, nx, k - 1));
					}
					else if(d < 4 && visited[ny][nx][k] == 0) {
						visited[ny][nx][k] = visited[y][x][k] + 1;
						q.add(new Tuple(ny, nx, k));
					}
				}
			}
		}
		return visited[H - 1][W - 1][0] - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		matrix = new int[H][W];
		visited = new int[H][W][K + 1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) 
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
			
		bw.write(String.valueOf(bfs(0, 0)) + '\n');		
		bw.flush();
		bw.close();
		br.close();
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
