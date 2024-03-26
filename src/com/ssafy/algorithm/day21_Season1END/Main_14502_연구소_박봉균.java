package com.ssafy.algorithm.day21_Season1END;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 아이디어: 조합 : 3개까지는 중첩 for문으로 가능, DFS 완탐
 * 결과: 15228KB, 256ms
 */

/* <BJ14502_G4 : 연구소>
 *  N×M 연구소가 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 
 *  일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 *  연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하시오.
[입력]
1) 행크기 N, 열크기 M. (3 ≤ N, M ≤ 8)
N개 줄) 지도 행렬. (0: 빈 칸, 1: 벽, 2: 바이러스, 2 <= 바이러스 개수 <= 10, 빈 칸 개수: 3개 이상)
[출력]
안전 영역의 최대 크기
 */
public class Main_14502_연구소_박봉균 {
	/** 4방 방향벡터 */
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int N, M, matrix[][], ret;
	static boolean[][] visited;
	static List<Pair> wallList = new ArrayList<>(); // 벽 세울 수 있는 모든 빈칸
	static List<Pair> virusList = new ArrayList<>(); // 바이러스가 있는 모든 곳

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N][M];
		visited = new boolean[N][M];

		// 연구소 정보 행렬 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == 0)
					wallList.add(new Pair(i, j)); // 벽 놓을 수 있는 가용 공간
				if (matrix[i][j] == 2)
					virusList.add(new Pair(i, j)); // 바이러스 있는 공간
			}
		}

		// 조합 (3개 뽑기)
		for (int i = 0; i < wallList.size(); i++) {
			for (int j = i + 1; j < wallList.size(); j++) {
				for (int k = j + 1; k < wallList.size(); k++) {
					matrix[wallList.get(i).first][wallList.get(i).second] = 1;
					matrix[wallList.get(j).first][wallList.get(j).second] = 1;
					matrix[wallList.get(k).first][wallList.get(k).second] = 1;

					ret = Math.max(ret, solve()); // 모든 조합 경우의 수 중, 안전 영역 개수의 최대

					// 원상 복구!
					matrix[wallList.get(i).first][wallList.get(i).second] = 0;
					matrix[wallList.get(j).first][wallList.get(j).second] = 0;
					matrix[wallList.get(k).first][wallList.get(k).second] = 0;
				}
			}
		}

		// 안전 영역의 최대 크기 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int y, int x) {
		visited[y][x] = true;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M) // 오버, 언더 체크
				continue;

			if (matrix[ny][nx] == 0 && visited[ny][nx] == false) // 0이 빈칸, 이동 가능
				dfs(ny, nx);
		}
	}

	static int solve() {
		// 매 경우의 수마다 초기화!! visited 오염
		for (int i = 0; i < visited.length; i++)
			Arrays.fill(visited[i], false);

		for (Pair v : virusList)
			dfs(v.first, v.second); // 바이러스가 퍼진다 (dfs)

		int cnt = 0; // 안전 영역의 개수
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (matrix[i][j] == 0 && visited[i][j] == false) // 바이러스가 방문 X, 벽X(빈칸) : 안전영역
					++cnt;
			}
		}
		return cnt;
	}

	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first, second;

		Pair() {
		}

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
