package com.ssafy.algorithm.day13_0216_그래프2_BFS;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * SWEA [D4] : 파핑파핑 지뢰 찾기
 * </pre>
 * @author 박봉균
 * 아이디어: BFS 탐색하면서 주위 지뢰 없으면 확장. 있으면 하나씩 클릭해야 함
 * 결과: 36652KB, 202ms
 */

/* <요약>
 * 이 게임은 R*C 크기의 표를 이용하는 게임인데,
 * 각 칸에는 지뢰가 있을 수도 있고 없을 수도 있다.
 * 표의 각 칸을 클릭했을 때, 그 칸이 지뢰가 있는 칸이라면 ‘파핑 파핑!’이라는 소리와 함께 게임은 끝난다.
 * 지뢰가 없는 칸: 인접 8방에 대해 몇 개의 지뢰가 있는지가 0 ~ 8로 표시.
 * 0이라면 근처의 8방향에 지뢰가 없다는 것이 확정된 것이기 때문에 그 8방향의 칸도 자동으로 숫자를 표시해 준다.
 * 지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야 하는지 구하자.
[입력]
1) 테스트 케이스의 수 T
각 테케] 1) N(1 ≤ N ≤ 300) 표의 크기 = N*N.
    N개 줄) 길이가 N인 문자열 (‘*’: 지뢰, ‘.’: 지뢰X.)
[출력]
#테케번호 <공백> 최소 몇 번의 클릭을 해야 지뢰가 없는 모든 칸에 숫자가 표시될 것인지 출력한다.
 */
public class D4_1868_파핑파핑지뢰찾기_박봉균 {
	/** 8방 방향벡터 */
	public static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1};
	public static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

	/** 행,열 크기 */
	static int N;
	/** 표 정보 */
	static char[][] matrix; 
	/** 방문 체크 배열 */
	static boolean[][] visited;
	/** 결과 변수 : 클릭 횟수 */
	static int ret;
	
	/** 주위에 지뢰 있는지 체크하는 함수
	 * @param y : 행
	 * @param x : 열 
	 * @return : 존재하면 true, 존재하지 않으면 false
	 */
	static boolean check(int y, int x) {
		for(int d = 0; d < 8; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N)
				continue;
			if(matrix[ny][nx] == '*') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * BFS 탐색하며 주변에 지뢰가 없다면 계속해서 확장해나가는 함수
	 * @param sy : 시작 행
	 * @param sx : 시작 열
	 */
	static void bfs(int sy, int sx) {
		Queue<Pair> q = new ArrayDeque<>();
	
		visited[sy][sx] = true;
		q.add(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			int y = q.peek().first;
			int x = q.remove().second;
			
			for(int d = 0; d < 8; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				
				if(matrix[ny][nx] == '.' && visited[ny][nx] == false) {
					visited[ny][nx] = true;
					if(check(ny, nx) == false) //주변에 지뢰 없다면 확장(q.add)
						q.add(new Pair(ny, nx));
				}
 			}		
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테케 개수 T
		
		//T개 테케에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화!
			ret = 0;
			
			//행,열 크기 N 입력, 동적할당
			N = Integer.parseInt(br.readLine());
			matrix = new char[N][N];
			visited = new boolean[N][N];
			
			//표 정보 입력
			for(int i = 0; i < N; i++) {
				String given = br.readLine();
				for(int j = 0; j < N; j++)
					matrix[i][j] = given.charAt(j);
			}
			
			//주변에 지뢰 없으면(0) 연쇄적 확장
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(matrix[i][j] == '.' && check(i, j) == false && visited[i][j] == false) {
							bfs(i, j);
							++ret;
					}
				}
			} 
			
			//나머지는 클릭
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++ ) {
					if(matrix[i][j] == '.' && visited[i][j] == false)
						++ret;
				}
			}
			
			//클릭 횟수의 최소 출력
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');			
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}	
}
