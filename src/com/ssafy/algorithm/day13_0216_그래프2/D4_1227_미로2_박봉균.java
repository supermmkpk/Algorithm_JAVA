package com.ssafy.algorithm.day13_0216_그래프2;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1227 [D4] : 미로2
 * </pre>
 * @author 박봉균
 * 아이디어: BFS 탐색, 도착점 만나면 return.
 * 결과: 25760KB, 140ms
 */

/* <요약>
 * 100*100 행렬의 미로에서 출발점으로부터 도착점까지 갈 수 있는 길이 있는지 판단해보자.
[입력]
10개 테케] 1) 테케 번호
    다음 줄) 행렬. (1: 벽, 0: 길, 2: 출발점, 3: 도착점)
[출력]
#테케번호 <공백> 도달 가능 여부(1 or 0)
 */
public class D4_1227_미로2_박봉균 {
	/** 4방 방향벡터 */
	public static final int[] dy = {-1, 0, 1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	
	/** 행,열 수 */
	static final int N = 100;
	/** 미로 행렬 */
	static int[][] matrix = new int[104][104];
	/** 방문 체크 배열 */
	static boolean[][] visited = new boolean[104][104];
	/** 결과 변수 : 도착점 만나면 1, 아니면 0 */
	static int ret;

	/** BFS 탐색 */
	static void bfs (int sy, int sx) {
		Queue<Pair> q = new ArrayDeque<>();
		
		visited[sy][sx] = true;
		q.add(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			int y = q.peek().first;
			int x = q.remove().second;
			
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				
				if(matrix[ny][nx] != 1 && visited[ny][nx] == false) {
					visited[ny][nx] = true;
					if(matrix[ny][nx] == 3) { //도착점 만나면 결과=1, return
						ret = 1;
						return;
					}
					q.add(new Pair(ny, nx));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//10개 테케에 대하여
		for(int t = 1; t <= 10; t++) {
			br.readLine();
			//초기화
			ret = 0;
			for(int i = 0; i < visited.length; i++)
				Arrays.fill(visited[i], false);
			
			//미로 행렬 입력
			Pair start = new Pair(); //시작 좌표
			Pair end = new Pair(); //도착 좌표
			for(int i = 0; i < N; i++) {
				String given = br.readLine();
				System.out.println(given.length());
				for(int j = 0; j < given.length(); j++) {
					matrix[i][j] = given.charAt(j) - '0';
					if(matrix[i][j] == 2) { //시작점
						start.first = i;
						start.second = j;
					}
					if(matrix[i][j] == 3) { //도착점
						end.first = i;
						end.second = j;
					}						
				}
			}
			
			//시작점(start)부터 BFS 탐색을 수행합니다.
			bfs(start.first, start.second);
			
			//BFS 탐색의 결과(도착점 도착여부)를 출력합니다.
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
		
		Pair() {}
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}	
}
