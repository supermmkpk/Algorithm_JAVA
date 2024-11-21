package com.ssafy.algorithm.day13_0216_그래프2_BFS;
import java.io.*;
import java.util.*;

public class reviewBFS3 {
	static final int[] dy = { -1, 0, 1, 0 };
	static final int[] dx = { 0, 1, 0, -1 };

	static int N, M, matrix[][];
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while (true) {
			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}

			int connectedComponent = connected();
			if(connectedComponent == 0) {
				time = 0;
				break;
			} else if (connectedComponent >= 2) {
				break;
			}

			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			bfs(0, 0);
			++time;
		}

		System.out.println(time);
		br.close();
	}

	// 빙산 녹이기. 인접 칸들에 대해 레벨 별 탐색 -> BFS!!
	static void bfs(int sy, int sx) {
		Queue<Coord> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] != 0) {
					q.add(new Coord(i, j));
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.remove().x;

			int sea = 0;

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}

				if (visited[ny][nx] == false && matrix[ny][nx] == 0) {
					++sea;
				}
			}

			int newIce = matrix[y][x] - sea;

			if (newIce < 0) {
				matrix[y][x] = 0;
			} else {
				matrix[y][x] = newIce;
			}
		}

	}

	// connected component
	static int connected() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == false && matrix[i][j] != 0) {
					dfs(i, j);
					++cnt;
				}
			}
		}

		return cnt;
	}

	static void dfs(int y, int x) {
		visited[y][x] = true;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
				continue;
			}

			if (visited[ny][nx] == false && matrix[ny][nx] != 0) {
				dfs(ny, nx);
			}
		}
	}

	// 좌표
	static class Coord {
		int y, x;

		Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
