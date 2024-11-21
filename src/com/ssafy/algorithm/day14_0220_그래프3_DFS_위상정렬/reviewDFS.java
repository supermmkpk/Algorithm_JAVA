package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.util.*;
import java.io.*;

/**
 * BJ_2636[G4] : 치즈
 * 좌표를 저장한다는 아이디어. 원하는 거 만나면 기록, 이전 분기로 돌아간다는 아이디어
 */
public class reviewDFS {
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	static int H, W, matrix[][], cheese;
	static boolean visited[][];
	static List<Coord> bound = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		matrix = new int[H + 1][W + 1];
		visited = new boolean[H + 1][W + 1];
		
		for(int i = 0 ; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(matrix[i][j] == 1) {
					cheese++;
				}
			}
		}
		
		//1시간마다 경계선 없어짐
		int time = 0;
		int prevCheese = cheese;
		while(true) {
			//초기화!
			for(int i = 0; i < visited.length; i++) {
				Arrays.fill(visited[i], false);
			}
			bound.clear();
			
			//경계선 찾기
			dfs(0, 0); 
			
			//경계선 없으면 치즈 없는 거 -> break
			if(bound.isEmpty()) { 
				break;
			}
			
			//녹기 전 기록
			prevCheese = cheese;
			
			//녹는다(1시간)
			for(Coord coord : bound) {
				matrix[coord.y][coord.x] = 0;
				cheese--;
			}
			++time;
			
			//녹은 결과 치즈 없으면 break
			if(cheese == 0) {
				break;
			}
		}
		
		System.out.println(time);
		System.out.println(prevCheese);
		br.close();
	}
	
	//경계선 dfs 
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		//경계선
		if(matrix[y][x] == 1) {
			bound.add(new Coord(y, x)); //좌표 저장
			return; //이전 분기로
		}
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				continue;
			}
			
			if(visited[ny][nx] == false) {
				dfs(ny, nx);
			}
		}
	}
	
	static class Coord {
		int y, x;
		Coord() {}
		Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
}
