package com.ssafy.algorithm.day20_0228_DP2;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 아이디어: 코어 좌표 저장, 방향 별로 탐색 -> 가능한지 체크, 최소 선 길이 갱신
 * 결과: 24068KB, 1034ms
 */

/* <SWEA_1767 : 프로세서 연결하기>
 * 멕시노스는 N*N cell로 구성되어 있다.
 * 1개의 cell에는 1개의 Core 혹은 1개의 전선이 올 수 있다.
 * Core와 전원을 연결하는 전선은 직선으로만 설치, 교차X
 * 멕시노스의 가장자리에 위치한 Core는 이미 전원이 연결된 것으로 간주한다.
 * 최대한 많은 Core에 전원을 연결했을 때, 전선 길이의 합의 최소를 구하자.
[입력]
1) 테케 수 T.
각 테케] 1) N. (7 ≤  N ≤ 12)
    N개 줄) 멕시노스 행렬. (0: 빈 cell, 1: core, 1 <= Core수 <= 12, 전원이 연결되지 않는 Core가 존재할 수 있다)
[출력]
#테케번호 <공백> 정답
 */
class SWEA_1767_프로세서연결하기_박봉균 {
	/** 4방 방향벡터 */
	static final int dy[] = { -1, 0, 1, 0 };
	static final int dx[] = { 0, 1, 0, -1 };

	static int N, matrix[][];
	static int maxCnt = Integer.MIN_VALUE;
	static int ret = Integer.MAX_VALUE;

	/** 코어 좌표 리스트 */
	static ArrayList<Pair> cores = new ArrayList<>();

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테케 수 T

		// T개 테케에 대하여
		for (int t = 1; t <= T; t++) {
			// 초기화
			cores.clear();
			maxCnt = Integer.MIN_VALUE;
			ret = Integer.MAX_VALUE;

			// 행렬 크기 입력, 동적할당
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N];

			// 멕시노스 정보 행렬 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());

					if (matrix[i][j] == 1 && 0 < i && i < N - 1 && 0 < j && j < N - 1) // 가장자리 저장X
						cores.add(new Pair(i, j));
				}
			}

			// 프로세서 연결하기
			go(0, 0, 0);

			// 선의 최소 길이 출력
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void go(int idx, int cnt, int lenSum) {
		// 종료조건: 모든 코어 고려
		if (idx == cores.size()) {
			if (maxCnt < cnt) { // 코어 수 최대
				maxCnt = cnt;
				ret = lenSum;
			}
			if (maxCnt == cnt) // 코어 개수 같은 경우라면
				ret = Math.min(ret, lenSum); // 길이 합 최소

			return;
		}

		// 현재 코어의 좌표
		int y = cores.get(idx).first;
		int x = cores.get(idx).second;

		// 4방향에 대하여 연결 체크
		for (int d = 0; d < 4; d++) {
			int len = 0; // 길이
			int ny = y;
			int nx = x;

			// 해당 방향으로 가장자리까지 탐색(가능 여부, 길이 체크)
			while (true) {
				ny += dy[d];
				nx += dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) // 오버, 언더 체크
					break;

				// 다른 코어 만나면 불가능 -> break
				if (matrix[ny][nx] == 1) {
					len = 0;
					break;
				}

				len++;
			}

			// 선 길이만큼 기록(1)
			for (int i = 1; i <= len; i++) {
				int lineY = y + dy[d] * i;
				int lineX = x + dx[d] * i;
				matrix[lineY][lineX] = 1;
			}

			// 길이 0(불가능)이면 다음 탐색
			if (len == 0)
				go(idx + 1, cnt, lenSum);
			// 길이 > 0(가능)이면 길이, 코어 추가하여 다음 탐색
			else {
				go(idx + 1, cnt + 1, lenSum + len);

				// 원상복구
				for (int i = 1; i <= len; i++) {
					int lineY = y + dy[d] * i;
					int lineX = x + dx[d] * i;
					matrix[lineY][lineX] = 0;
				}
			}
		}
	}

	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first, second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
