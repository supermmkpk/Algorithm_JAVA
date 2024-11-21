package com.ssafy.algorithm.day13_0216_그래프2_BFS;
import java.io.*;
import java.util.*;

/**
 * BJ_1600[G3] : 말이 되고픈 원숭이
 * 가중치 같은 최단거리 -> BFS! + 상태값
*/
public class reviewBFS2 {
	//동작 수 이므로 말 이동이든 뭐든 한번 동작 가중치 = 1 -> BFS
	
	//12방 방향벡터(4부터 말 방향벡터)
	static final int[] dy = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static final int[] dx = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	
	static int K, W, H, toY, toX;
	static int[][] matrix;
	static int[][][] visited; //상태값: 현재 y, x, k

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		toY = H - 1;
		toX = W - 1;
		matrix = new int[H + 1][W + 1];
		visited = new int[H + 1][W + 1][K + 1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0, 0, K));
		br.close();
	}
	
	static int bfs(int sy, int sx, int sk) {
		Queue<Tuple> q = new ArrayDeque<>();
		visited[sy][sx][sk] = 1;
		q.add(new Tuple(sy, sx, sk));
		
		while(!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.peek().x;
			int k = q.remove().k;
			
			if(y == toY && x == toX) {
				return visited[y][x][k] - 1;
			}
			
			for(int d = 0; d < 12; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= H || nx < 0 || nx >= W || matrix[ny][nx] == 1) {
					continue;
				}
				
				if(d >= 4 && k > 0 && visited[ny][nx][k - 1] == 0) {
					//말 움직임
					q.add(new Tuple(ny, nx, k - 1));
					visited[ny][nx][k - 1] = visited[y][x][k] + 1;
				} else if(d < 4 && visited[ny][nx][k] == 0) {
					//이외 인접 이동
					q.add(new Tuple(ny, nx, k));
					visited[ny][nx][k] = visited[y][x][k] + 1;	
				}
			}
		}
		
		return visited[toY][toX][0] - 1;
	}
	
	static class Tuple {
		int y, x, k;
		Tuple() {}
		Tuple(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
	}

}
