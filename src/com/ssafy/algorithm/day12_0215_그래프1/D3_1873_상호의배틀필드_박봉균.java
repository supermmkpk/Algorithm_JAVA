package com.ssafy.algorithm.day12_0215_그래프1;
import java.util.*;
import java.io.*;

/**
 * SWEA 1873 [D3] : 상호의 배틀필드
 * @author 박봉균
 * 결과: 22596KB, 118 ms
 */

/* <요약>
 * 전차 하나뿐이며, 적이나 아군으로 만들어진 전차는 등장하지 않는다.
 * 전차는 사용자의 입력에 따라 H*W 게임 맵에서 다양한 동작을 한다.
<맵 legend>
.	평지(전차 가능)
*	벽돌 벽
#	강철 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
<입력 동작>
U	Up : 전차 방향 위쪽으로 바꾸고, 한 칸 위의 칸 '.' : 위 칸 이동.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
모든 입력을 처리하고 나면 게임 맵의 상태가 어떻게 되는지 구해보자.
[입력]
1) 테케 수 T.
각 테케] H, W (2 ≤ H, W ≤ 20)
    H개 줄) 길이 W 문자열(맵). (전차는 단 하나만 있다)
	다음 줄) 입력의 개수 N(0 < N ≤ 100)
	다음 줄) 길이 N 문자열(입력).
[출력]
#테케번호 <공백 > H개의 줄에 걸쳐 맵 출력.
 */
public class D3_1873_상호의배틀필드_박봉균 {
	/** 방향벡터 : 위, 오, 아래, 왼 */
	public static final int[] dy = {-1, 0, 1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	
	/** 행수 H, 열수 W, 입력수 N */
	static int H, W, N;
	
	static char[][] matrix;
	
	/** 현 위치 */
	static Pair loc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테케수
		
		//T개 테케에 대해
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			//행수 H, 열수 W, 입력수 N 입력, 동적할당
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			matrix = new char[H][W];
			
			//주어지는 행렬(맵) 입력
			for(int i = 0; i < H; i++) {
				String given = br.readLine();
				for(int j = 0; j < given.length(); j++) {
					matrix[i][j] = given.charAt(j);		
					if(matrix[i][j] == '^' || matrix[i][j] == 'v' 
							|| matrix[i][j] == '<' || matrix[i][j] == '>') 
						loc = new Pair(i, j);
				}	
			}
			
			//입력수 N 입력
			N = Integer.parseInt(br.readLine());

			//입력과 동시에 동작 수행 
			String given = br.readLine();
			for(int i = 0; i < given.length(); i++) 
				 move(given.charAt(i));
			
			//동작 수행 후 행렬 출력
			bw.write("#" + String.valueOf(t) + " ");
			for(char[] row : matrix) {
				for(char col : row)
					bw.write(col);
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 동작 수행 */
	static void move(char cmd) {
		//현 좌표
		int y = loc.first;
		int x = loc.second;
		//다음 좌표
		int ny = 0; 
		int nx = 0;
		
		switch(cmd) {
		case 'U':
			//한 칸 위의 칸 '.' : 위 칸 이동
			ny = y - 1;
			nx = x;
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				//전차 방향 위로 바꾸기
				matrix[y][x] = '^';
				break; 
			}
			if(matrix[ny][nx] == '.') {
				loc.first -= 1;
				//전차 방향 위쪽으로 바꾸기
				matrix[y][x] = '.';
				matrix[ny][nx] = '^';
			}
			else {
				//전차 방향 위로 바꾸기
				matrix[y][x] = '^';
			}
			break;
			
		case 'D':
			//한 칸 아래 '.' : 아래 이동
			ny = y + 1;
			nx = x;
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				//전차 방향 아래로 바꾸기
				matrix[y][x] = 'v';
				break; 
			}
			if(matrix[ny][nx] == '.') {
				loc.first += 1;
				//전차 방향 아래쪽으로 바꾸기
				matrix[y][x] = '.';
				matrix[ny][nx] = 'v';
			}
			else {
				//전차 방향 아래로 바꾸기
				matrix[y][x] = 'v';
			}
			break;
			
		case 'L':
			//한 칸 왼쪽 '.' : 왼쪽 이동
			ny = y;
			nx = x - 1;
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				//전차 방향 왼쪽로 바꾸기
				matrix[y][x] = '<';
				break; 
			}
			if(matrix[ny][nx] == '.') {
				loc.second -= 1;
				//전차 방향 왼쪽으로 바꾸기
				matrix[y][x] = '.';
				matrix[ny][nx] = '<';
			}
			else {
				//전차 방향 왼쪽으로 바꾸기
				matrix[y][x] = '<';
			}
			break;
		
		case 'R':
			//한 칸 오른쪽 '.' : 오른쪽 이동
			ny = y;
			nx = x + 1;
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				//전차 방향 오른쪽으로 바꾸기
				matrix[y][x] = '>';
				break; 
			}
			if(matrix[ny][nx] == '.') {
				loc.second += 1;
				//전차 방향 오른쪽으로 바꾸기
				matrix[y][x] = '.';
				matrix[ny][nx] = '>';
			}
			else {
				//전차 방향 오른쪽으로 바꾸기
				matrix[y][x] = '>';
			}
			break;
			
		//S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
		case 'S':
			if(matrix[y][x] == '^')
				dfs(y, x, 0);
			else if(matrix[y][x] == '>')
				dfs(y, x, 1);
			else if(matrix[y][x] == 'v')
				dfs(y, x, 2);
			else if(matrix[y][x] == '<')
				dfs(y, x, 3);
			break;
		}
	}
	
	/** 포탄 발사 동작 수행 */
	static void dfs(int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		//맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다 , 오버언더 체크
		if(ny < 0 || ny >= H || nx < 0 || nx >= W)
			return;
		
		//강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
		if(matrix[ny][nx] == '#')
			return;
		
		//부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
		if(matrix[ny][nx] == '*') {
			matrix[ny][nx] = '.';
			return;
		}		
		
		dfs(ny, nx, dir);
	}
	
	/** 좌표 저장 Pair 클래스 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}