package personal;

import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        결과: 14060KB, 1088ms
 */
/*
 * <BJ15684_G3 : 사다리 조작>
 * <시간제한: 2초>
 * 사다리 게임: N개의 세로선과 M개의 가로선이 있다.
 * 인접한 세로선 사이에는 가로선을 놓을 수 있는데, 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H이고, 모든 세로선이 같은
 * 위치를 갖는다.
 * 가로선은 인접한 두 세로선을 연결해야 한다. 단, 두 가로선이 연속하거나 서로 접하면 안 된다.
 * 사다리에 가로선을 추가해서, i번 세로선의 결과가 i번이 나오기 위해 추가해야 하는 가로선의 최소 개수를 구하시오.
 * [입력]
 * 1) 세로선 수 N, 가로선 수 M, 가로선을 놓을 수 있는 위치 수 H. (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤
 * (N-1)×H)
 * M개 줄) 가로선의 정보 a, b. (1 ≤ a ≤ H, 1 ≤ b ≤ N-1) (b번 세로선과 b+1번 세로선을 a번 점선 위치에서
 * 연결했다는 의미)
 * 가로 점선 번호: 위부터 1~, 세로선: 왼쪽부터 1~
 * [출력]
 * i번 세로선의 결과가 i번이 나오도록 추가해야 하는 가로선 개수의 최솟값. (정답이 3보다 큰 값,불가능한 경우: -1)
 */
public class Main_15684_사다리조작_박봉균 {
	/** 상한 */
	static final int INF = (int) 1e9;

	static int N, M, H;
	static boolean visited[][]; // a번 가로선으로 b -- b+1 있는지
	static int ret = INF;

	// i->i인지 체크
	static boolean check() {
		for (int i = 1; i <= N; i++) {
			int start = i;
			for (int j = 1; j <= H; j++) {
				if (visited[j][start])
					start++;
				else if (visited[j][start - 1])
					start--;
			}
			if (start != i)
				return false;
		}

		return true;
	}

	// 완탐, 백트래킹
	static void go(int now, int cnt) {
		if (cnt > 3 || cnt >= ret)
			return;
		if (check()) {
			ret = Math.min(ret, cnt);
			return;
		}

		// 1~H 위치에 대하여
		for (int i = now; i <= H; i++) {
			// 1~N-1 세로선에 대하여
			for (int j = 1; j < N; j++) {
				// 이어지는지 체크
				if (visited[i][j - 1] || visited[i][j] || visited[i][j + 1])
					continue;
				visited[i][j] = true;
				go(i, cnt + 1);
				visited[i][j] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로선 수 N,
		M = Integer.parseInt(st.nextToken()); // 가로선 수 M
		H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치 수 H
		visited = new boolean[H + 4][N + 4]; // 가로선 정보 배열

		// 가로선 정보 배열 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			visited[a][b] = true;
		}

		// 사다리 조작
		go(1, 0);

		// 정답 출력.(3 초과 시, -1)
		bw.write(String.valueOf(ret == INF ? -1 : ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
