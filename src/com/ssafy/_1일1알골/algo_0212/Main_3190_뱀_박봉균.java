package com.ssafy._1일1알골.algo_0212;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ3190 [G4] : 뱀
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 규칙에 따라 머리, 꼬리 이동, 몸통 기록은 visited배열로. 게임 종료 시, 시간 반환
 * 결과: 11748KB, 80ms
 */

/* <문제 요약>
 * 사과를 먹으면 뱀 길이가 늘어난다.
 * 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
 * NxN 행렬에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. (1행,1열)에서 시작하고 초기 길이는 1. 처음에 오른쪽을 향한다.
 * 매 초마다 다음 규칙을 따르며 이동.
 * 1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
 * 2. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
 * 3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 * 4. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 사과의 위치와 뱀의 이동경로가 주어질 때, 이 게임이 몇 초에 끝나는지 계산하라.
[입력]
1) 보드의 크기 N. (2 ≤ N ≤ 100) 
2) 사과의 개수 K. (0 ≤ K ≤ 100)
K개 줄) 사과의 위치 (행 열) 사과의 위치는 모두 다르며, 1행 1열에는 사과가 없다.
다음 줄) 뱀의 방향 변환 횟수 L. (1 ≤ L ≤ 100)
L개 줄) 뱀의 방향 변환 정보(정수 X와 문자 C: 시작하고 X초가 지난 뒤 왼쪽('L') 또는 오른쪽('D')로 90도 방향을 회전. 1 <= X <= 1e4, 방향 전환 정보는 X가 증가하는 순으로 주어진다.)

[출력]
게임이 몇 초에 끝나는지 출력한다.
 */
public class Main_3190_뱀_박봉균 {
	/** 방향벡터(위, 오, 아래, 왼) */
	public static final int[] dy = {-1, 0, 1, 0}; 
	public static final int[] dx = {0, 1, 0, -1};
	
	/** 행렬 크기 N, 사과개수 K, 방향 전환 횟수 L */
	static int N, K, L;
	/** 방문배열(몸통 표시) */
	static boolean[][] visited;
	/** 주어지는 행렬(1: 사과) */
	static int[][] matrix;
	/** {초, 방향} 배열 */
	static Pair[] dir; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//행렬 크기 N, 사과 개수 K 입력, 배열 동적할당
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		visited = new boolean[N][N];
		
		//사과 위치 입력(1)
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int inRow = Integer.parseInt(st.nextToken()) - 1;
			int inCol = Integer.parseInt(st.nextToken()) - 1;
			matrix[inRow][inCol] = 1;
		}
		
		//방향 개수 L 입력, 방향배열 dir[] 동적할당
		L = Integer.parseInt(br.readLine());
		dir = new Pair[L];
		
		//방향 정보 저장{초, 방향}
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int inSec = Integer.parseInt(st.nextToken());
			char inDir = st.nextToken().charAt(0);
			
			if(inDir == 'L') //L: 왼쪽 90도
				dir[i] = new Pair(inSec, -1);
			else if(inDir == 'D') //R: 오른쪽 90도
				dir[i] = new Pair(inSec, +1);
		}
		
		//게임 종료 시간 출력
		bw.write(String.valueOf(move()) + '\n');
		
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
		
	/** 뱀을 규칙에 따라 이동시키고, 게임 종료 시, 시간(초)를 반환하는 함수 */
	static int move() {
		visited[0][0] = true; //초기화
		int time = 0; //시간
		int d = 1; //머리 방향 (초기: 오른쪽(1))
		int headY = 0, headX = 0; //머리 좌표(초기: 0,0)
		int tailY = 0, tailX = 0; //꼬리 좌표(초기: 0,0)
		int idx = 0; //꼬리 방향 인덱스
		int dT = 1; //꼬리 방향
		int tailMove = 0; //꼬리 이동횟수
		
		for(Pair p : dir) {
			while(time < p.first) { //방향 전환 전까지 같은 방향으로 진행(1초마다)
				time++; 
				
				//머리 이동
				headY = headY + dy[d];
				headX = headX + dx[d];

				if(headY < 0 || headY >= N || headX < 0 || headX >= N) //행렬을 벗어나면 종료
					return time;
				if(visited[headY][headX] == true) //자신의 몸통과 만나면 종료
					return time;
				visited[headY][headX] = true; //방문 체크
				
				//꼬리 이동
				//사과X - 꼬리위치 갱신
				if(matrix[headY][headX] == 0) { 
					visited[tailY][tailX] = false; //길이 줄이기
					
					//꼬리의 이동 횟수를 체크하고 이에 따라 방향 전환(머리 방향과 다름)
					if(idx < L && tailMove == dir[idx].first) {
						dT += dir[idx++].second;
						if(dT < 0)
							dT += 4;
						else if(dT >= 4)
							dT -= 4;
					}
					tailMove++;
					
					//꼬리 이동, 갱신
					tailY = tailY + dy[dT];
					tailX = tailX + dx[dT];
					if(tailY < 0 || tailY >= N || tailX < 0 || tailX >= N) //행렬을 벗어나면 종료
						return time;
					visited[tailY][tailX] = true;
				}
				else { 
					//사과O: 사과 없애고, 이동X
					matrix[headY][headX] = 0;
					visited[tailY][tailX] = true;
				}
			}
			d += p.second; //방향 전환
			//인덱스 벗어나는 경우, 조정해줍니다.
			if(d < 0)
				d += 4;
			else if(d >= 4)
				d -= 4;
		}
		
		//이후 방향 전환이 없으므로 같은 방향으로 게임 종료 시까지 진행
		while(true) {
			time++; //매초마다 이동
			
			//머리 이동
			headY = headY + dy[d];
			headX = headX + dx[d];
			if(headY < 0 || headY >= N || headX < 0 || headX >= N) //행렬을 벗어나면 종료
				return time;
			if(visited[headY][headX] == true) //자신의 몸통과 만나면 종료
				return time;
			visited[headY][headX] = true;
			
			//꼬리 이동
			if(matrix[headY][headX] == 0) { //사과X - 꼬리위치 갱신
				visited[tailY][tailX] = false; //길이 줄이기
				
				if(idx < L && tailMove == dir[idx].first) { //꼬리 방향
					dT += dir[idx++].second;
					if(dT < 0)
						dT += 4;
					else if(dT >= 4)
						dT -= 4;
				}
				tailMove++;
				
				
				tailY = tailY + dy[dT];
				tailX = tailX + dx[dT];
				if(tailY < 0 || tailY >= N || tailX < 0 || tailX >= N) //행렬을 벗어나면 종료
					return time;
				visited[tailY][tailX] = true;
			}
			else {
				//사과O - 사과 없애기, 이동X
				matrix[headY][headX] = 0;
				visited[tailY][tailX] = true;
			}
		}
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
