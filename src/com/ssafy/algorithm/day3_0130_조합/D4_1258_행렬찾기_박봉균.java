package com.ssafy.algorithm.day3_0130_조합;
import java.util.*;
import java.io.*;

/**
 * SWEA 1258 [D4] : 행렬 찾기
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: 	- DFS connected component 사용.
 * 			- 방향 별 최초 탐색 개수로 행, 열 구하기.(오른쪽: 열, 아래: 행)
 * 			- 부분 행렬 객체 만들어서 출력 조건에 맞게 정렬해서 출력하기.
 * 결과: Pass, 0.13781s
 */

/* <문제 요약>
 * 화학 물질 용기가 n x n으로 배열되어 있다.(빈 용기: 0, 화학 물질의 종류에 따라 1~9)
 * 1. 화학 물질이 담긴 용기들끼리 각각의 사각형을 이루고 있다. 또한, 이 사각형 내부에는 빈 용기가 없다. -> connected component
 * 2. 화학 물질이 담긴 용기들로 이루어진 사각형들은 각각 차원(가로의 용기 수 x 세로의 용기 수)이 다르다.
 * 용기들에 대한 2차원 배열에서 행렬(화학 물질이 든 용기들로 이루어진 사각형)들을 찾아내고 출력하라.

[입력]
1) 테케 개수
각 테케) 1) n (1 <= n <= 100) 
		다음 n줄) n x n 행렬

[출력]
#테케 <공백> 부분 행렬 개수와 그 뒤를 이어 행렬들의 행과 열의 크기를 출력한다.
(행과 열을 곱한 값의 크기가 작은 순서대로 출력한다.)
(크기가 같을 경우 행이 작은 순으로 출력한다.)
 */
public class D4_1258_행렬찾기_박봉균 {
	/** 4방 방향벡터 */
	public static final int[] dy = {-1, 0, 1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	
	/** 행렬 크기 */
	static int N;
	/** 행렬 저장 2차원 배열 */
	static int[][] matrix = new int[104][104];
	/** 방문 여부 체크 배열 */
	static boolean[][] visited;
	/** 부분 행렬의 개수 */
	static int cnt;
	
	/** 부분행렬의 행수 */
	static int row = 1;
	/** 부분행렬의 열수 */
	static int col = 1;
	/** 탐색 시, 이전 탐색 방향 기록 */
	static int prevD = 0;
	/** 아래쪽으로 방향 전환한 횟수 */
	static int yFlag = 0;
	/** 오른쪽으로 방향 전환한 횟수 */
	static int xFlag = 0;
	/**
	 * DFS로 탐색, 방향 전환, 방향벡터 값을 이용하여 행수, 열수를 구하는 함수 
	 * @param sy : 시작 행번호
	 * @param sx : 시작 열번호
	 */
	static void dfs(int sy, int sx) {
		visited[sy][sx] = true;	//방문 체크	
		
		for(int d = 0; d < 4; d++) {
			int ny = sy + dy[d];
			int nx = sx + dx[d];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) //오버, 언더 체크
				continue;
			if(matrix[ny][nx] != 0 && visited[ny][nx] == false) { //방문 가능하면
				if(d == 1 && prevD != d) //아래로 방향 전환 시, yFlag 증가 
					yFlag++;
				else if(d == 2 && prevD != d) //오른쪽으로 방향 전환 시, xFlag 증가 
					xFlag++;
				if(d == 1 && yFlag <= 1)  //최초 오른쪽 방향 이동이라면 열 개수 증가
					col++;
				else if(d == 2 && xFlag <= 1) //최초 아래 방향 이동이라면 행 개수 증가
					row++; 
				
				prevD = d; //이전 이동방향 기록
				dfs(ny, nx); //다음 칸에서 탐색
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); //테스트케이스 수 T
		
		//T개 테스트케이스에 대해
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); //행렬 크기 N 입력
			//초기화
			visited = new boolean[N][N]; //방문 배열 동적 할당
			cnt = 0; //부분행렬 개수 초기화
			
			//행렬 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/** 부분행렬을 저장하는 리스트 */
			ArrayList<SubMatrix> list = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == false && matrix[i][j] != 0) { //방문 가능
						dfs(i, j); //DFS -> Connected Component(부분행렬) 탐색
						list.add(new SubMatrix(row, col)); //부분행렬을 리스트에 추가
						yFlag = 0; xFlag = 0; //행이동, 열이동 횟수 초기화
						row = 1; col = 1; //행수, 열수 초기화
						prevD = 0; //이전 방향벡터 기록 초기화
						++cnt; //부분행렬 개수 증가
					}
				}
			}
			//부분행렬 객체(SubMatrix)의 정렬 규칙에 따라 정렬
			Collections.sort(list); 
			
			//출력 조건에 따라 출력
			bw.write("#" + t + " " + cnt + " ");
			for(SubMatrix s : list) {
				bw.write(String.valueOf(s.getRow()) + " " + String.valueOf(s.getCol()) + " ");
			}
			bw.newLine();
		}
		//남은 데이터 출력, stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}

/** 
 * 부분행렬 객체 클래스
 */
class SubMatrix implements Comparable<SubMatrix> {
	private int row;
	private int col;
	
	public SubMatrix(int row, int col) {
		this.row = row;
		this.col = col;
	}	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int size() {
		return row * col;
	}
	
	/**
	 * 정렬 규칙
	 * 1. 크기가 작은 순서대로
	 * 2. 크기가 같을 경우 행이 작은 순으로
	 */
	@Override
	public int compareTo(SubMatrix s) {
		if(this.size() < s.size()) {
			return -1;
		}
		else if(this.size() == s.size()) {
			if(this.row < s.row)
				return -1;
			else if(this.row == s.row)
				return 0;
			else 
				return 1;
		}
		else {
			return 1;
		}
	}
}
/*
1
9
1 1 3 2 0 0 0 0 0
3 2 5 2 0 0 0 0 0
2 3 3 1 0 0 0 0 0
0 0 0 0 4 5 5 3 1
1 2 5 0 3 6 4 2 1
2 3 6 0 2 1 1 4 2
0 0 0 0 4 2 3 1 1
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
*/
