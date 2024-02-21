package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2636 [G4] : 치즈
 * </pre>
 * @author 박봉균
 * 
 * 아이디어: DFS 탐색하면서 치즈 만나면 return(경계) -> 저장 -> 탐색 후 경계 0으로 바꿔주기
 * 결과: 13948KB, 108ms
 */

/* <요약>
 * 사각형 판이 있고, 위에 얇은 치즈가 놓여 있다. 
 * 가장자리에는 치즈X,  치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다. 
 * 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
 * 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하시오.
[입력]
1) 행크기  R, 열 크기 C. (R,C <= 100) 
2~) 행렬. (0: 치즈X, 1: 치즈O)
[출력]
1) 치즈가 모두 녹아서 없어지는 데 걸리는 시간
2) 모두 녹기 한 시간 전에 남아있는 치즈 칸 수
 */
public class Main_2636_치즈_박봉균 {
	/** 4방 방향벡터 */
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = { 0, 1, 0, -1};
	
	/** 판의 행크기  R, 열 크기 C */
	static int R, C;
	/** 주어지는 행렬 */
	static int[][] matrix;
	/** 방문 체크 배열 */
	static boolean[][] visited;
	/** 남은 치즈 수 */
	static int remain;
	/** 경계선 저장 리스트 */
	static ArrayList<Pair> bound = new ArrayList<>();
	
	/** 
	 * DFS 탐색하며 치즈 경계선을 찾는 함수 
	 */
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		//치즈 경계!!: 1이면 return
		if(matrix[y][x] == 1) { 
			bound.add(new Pair(y, x)); //경게의 좌표 저장
			--remain; //남은 치즈 수 감소
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			 
			if(visited[ny][nx] == false) {
				dfs(ny, nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//판의 행크기  R, 열 크기 C 입력, 동적할당
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		matrix = new int[R][C];
		visited = new boolean[R][C];
		
		//치즈 정보 입력
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				
				//치즈 수 기록
				if(matrix[i][j] == 1)
					++remain; 
			}
		}
		
		int time = 0; //시간
		int prevRemain = remain; //이전에 남은 치즈 수
		
		//치즈를 다 없앨 때까지
		while(remain > 0) {
			dfs(0, 0); //치즈 녹이기
			++time; //1초 경과
			
			//남은 치즈 수 기록
			if(remain > 0) 
				prevRemain = remain; 
			
			//치즈 경계선 0으로 바꿔주기
			for(Pair p : bound) 
				matrix[p.first][p.second] = 0;
			
			//다음 탐색을 위해 초기화
			for(int i = 0; i < visited.length; i++)
				Arrays.fill(visited[i], false);
			bound.clear();
		}
		
		//경과 시간 출력
		bw.write(String.valueOf(time) + '\n');
		//한 시간 전 남은 칸 수 출력
		bw.write(String.valueOf(prevRemain) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
