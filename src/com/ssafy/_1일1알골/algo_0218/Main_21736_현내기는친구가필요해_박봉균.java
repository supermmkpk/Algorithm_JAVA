package com.ssafy._1일1알골.algo_0218;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ21736 [S2] : 헌내기는 친구가 필요해
 * <pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: DFS 탐색
 * 결과: 43776KB, 752ms
 * 
 */

/* <요약> (시간: 2초)
 * 캠퍼스는 N * M 크기이며 캠퍼스에서 이동하는 방법은 벽이 아닌 상하좌우로 이동하는 것이다.  단, 캠퍼스의 밖으로 이동할 수는 없다.
 * 캠퍼스에서 도연이가 만날 수 있는 사람의 수를 구해보자.
[입력]
1) N, M (1 <= N <= 600, 1 <= M <= 600)
N개 줄) 캠퍼스의 정보. (O: 빈 공간, X: 벽, I: 도연, P: 사람)
[출력]
도연이가 만날 수 있는 사람의 수 (아무도 만나지 못한 경우: TT)
 */
public class Main_21736_현내기는친구가필요해_박봉균 {
	/** 방향벡터 : 위, 오, 아래, 왼 */
	public static final int[] dy = {-1, 0, 1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	
	/** 캠퍼스 행 N, 열 M */
	static int N, M;
	/** 캠퍼스 */
	static char[][] matrix;
	/** 방문체크 배열 */
	static boolean[][] visited;
	/** 결과 변수 : 만난 사람 수 */
	static int ret;
	
	/** DFS */
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= N || nx < 0 || nx >= M)
				continue;
			
			if(visited[ny][nx] == false) {
				//사람 만나면 결과(만난 사람 수) 증가, 계속 탐색
				if(matrix[ny][nx] == 'P') { 
					++ret;
					dfs(ny, nx);
				}
				else if(matrix[ny][nx] == 'O')
					dfs(ny, nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행 크기 N, 열 크기 M 입력, 동적할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new char[N][M];
		visited = new boolean[N][M];
		
		//캠퍼스 정보 입력('I'라면 시작위치(도연))
		int startY = 0;
		int startX = 0;
		for(int i = 0; i < N; i++) {
			String given = br.readLine();
			for(int j = 0; j < given.length(); j++) {
				matrix[i][j] = given.charAt(j);
				if(matrix[i][j] == 'I') {
					startY = i;
					startX = j;
				}
					
			}
		}
		
		//탐색
		dfs(startY, startX);
		
		//결과(만난 사람 수) 출력. 만난 사람 없다면 TT
		bw.write((ret <= 0 ? "TT" : String.valueOf(ret)) +'\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
