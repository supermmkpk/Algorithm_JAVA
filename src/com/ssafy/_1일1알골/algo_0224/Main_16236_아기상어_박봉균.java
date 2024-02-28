package com.ssafy._1일1알골.algo_0224;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 결과: 	12532KB, 100ms
 */
/*
 * N×N 공간에 물고기 M마리, 아기 상어 1마리.
 * 각 칸에는 물고기가 최대 1마리.
 * 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 
 * 아기 상어의 초기 크기는 2, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
 * 자신의 크기<물고기: 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 
 * 자신의 크기>물고기: 먹을 수 있다. 
 * 크기==물고기: 먹을 수 없지만, 지나갈 수 있다.
 * 더 이상 먹을 수 있는 물고기가 없다면엄마 상어에게 도움을 요청.
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 먹을 수 있는 물고기>1: 가장 가까운 물고기를 먹으러 간다.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다. BFS
 * 거리가 가까운 물고기가 많다면, 가장 위, 여러마리라면, 가장 왼쪽
 * 먹는데 걸리는 시간은 없다 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 * 자신의 크기와 같은 수의 물고기를 먹을 때: 크기가 1 증가
 * 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는가?
입력
1) 공간의 크기 N(2 ≤ N ≤ 20)
N개 줄) 공간의 상태.
0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
[출력]
엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력
 */
public class Main_16236_아기상어_박봉균 {
	//위, 오, 아, 왼
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	static int N, matrix[][], visited[][], sharkSize, eat, fish, time;
	static Pair sharkPos;

	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //공간 크기
		matrix = new int[N][N]; //공간
		visited = new int[N][N]; //방문배열
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++)  {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				
				//아기상어 위치 기록, 초기화
				if(matrix[i][j] == 9) {
					sharkPos = new Pair(i, j);
					matrix[i][j] = 0;
					sharkSize = 2;
				}
				
				//1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
				if(1 <= matrix[i][j] && matrix[i][j] <= 6)
					++fish;
			}
		}
		
		while(true) {
			//초기화
			for(int i = 0; i < N; i++)
				Arrays.fill(visited[i], 0);
			
			//먹을 물고기 없으면 종료
			if(!bfs(sharkPos.first, sharkPos.second))
				break;
			
			int min = Integer.MAX_VALUE; //거리의 최소
			//물고기까지 거리의 최소 구하고, 좌표 기록
			for(int i = N - 1; i >= 0; i--) {
				for(int j = N - 1; j >= 0; j--) {
					if(min >= visited[i][j] && sharkSize > matrix[i][j] 
							&& matrix[i][j] != 0 && visited[i][j] != 0) {
						min = visited[i][j];
						sharkPos.first = i;
						sharkPos.second = j;
					}
				}
			}
			
			//먹는 거에 대한 로직
			if(++eat == sharkSize) {
				++sharkSize;
				eat = 0;
			}
			--fish;
			matrix[sharkPos.first][sharkPos.second] = 0; //먹으면 0
			
			time += visited[sharkPos.first][sharkPos.second] - 1; //이동시간(가중치:1초) 갱신
			if(fish <= 0) //먹을 물고기 없으면 종료
				break;
		}

		
		//시간 출력
		bw.write(String.valueOf(time) + '\n');
		bw.flush();
        bw.close();
        br.close();
       
	}

	/* BFS 최단경로 구하기 */ 
	static boolean bfs(int sy, int sx) {
		Queue<Pair> q = new ArrayDeque<>();
		boolean flag = false; //먹는지 체크
		int dist = Integer.MAX_VALUE; //거리
		
		visited[sy][sx] = 1;
		q.add(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			int y = q.peek().first;
			int x = q.remove().second;
			
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N) //오버 언더 체크
					continue;

				//거리가 가까운 물고기가 많다면, 가장 위, 여러마리라면, 가장 왼쪽
				//아기상어 >= 물고기: 지나갈 수 있음
				if(sharkSize >= matrix[ny][nx] && visited[ny][nx] == 0) {
					if(visited[y][x] > dist) //구한 거리보다 멀면 넘어가기
						continue;
					
					//아기상어 > 물고기: 먹을 수 있다. 
					if(sharkSize > matrix[ny][nx] && matrix[ny][nx] != 0) {
						dist = visited[y][x];
						flag = true; //먹음.
					}
					visited[ny][nx] = visited[y][x] + 1; //가중치(1초) 더해주기
	
					q.add(new Pair(ny, nx));
				}
			}
		}
		return flag;
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
