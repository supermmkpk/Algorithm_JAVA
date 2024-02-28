package com.ssafy.algorithm.day20_0228_DP2;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 아이디어: DP
 * 결과: 11748KB, 80ms
 */

/* <BJ17070_G5 : 파이프 옮기기 1>
 * 집의 크기는 N×N의 격자판으로 나타낼 수 있다.
 * 행과 열의 번호는 1부터 시작한다. 각각의 칸은 빈 칸이거나 벽이다.
 * 파이프는 2개의 연속된 칸을 차지하는 크기이다. 파이프는 항상 빈 칸만 차지해야 한다.
 * 파이프를 밀 수 있는 방향은 →, ↘, ↓ . 회전은 45도만 회전시킬 수 있다.
 * 파이프가 가로: 2가지, 세로: 2가지, 대각선: 3가지.
 * 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 
 * 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
입력
1) 집의 크기 N(3 ≤ N ≤ 16)
N개의 줄) 집의 상태 (빈 칸: 0, 벽: 1)
[출력]
한쪽 끝을 (N, N)으로 이동시키는 방법의 수. 이동시킬 수 없는 경우, 0. 방법의 수는 항상 1,000,000보다 작거나 같다.
 */
public class Main_17070_파이프옮기기1_박봉균 {
	
	static int N, matrix[][];
	static int[][][] dp; //상태값: y, x, 방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//집 크기 N 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		dp = new int[N][N][3];

		//집 정보 입력 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//초기화
		for(int i = 0; i < dp.length; i++)
			for(int j = 0; j < dp[i].length; j++)
				Arrays.fill(dp[i][j], -1);
		
		//동적 계획법으로 주어진 조건을 만족하는 방법의 수 구하고 출력
		//초기에 모든 방향으로 분기하기 위해 대각(2) 방향으로 호출
		bw.write(String.valueOf(go(N - 1, N - 1, 2)) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * 동적계획법으로 거꾸로 접근해보자
	 * @param y : 파이프 시작 행
	 * @param x : 파이프 시작 행
	 * @param dir : 방향(0:가로, 1:세로, 2:대각)
	 */
	static int go(int y, int x, int dir) {
		//기저사례	
		if(y < 0 || x < 0) //벗어나면 실패
			return 0;
		if(y != N - 1 || x != N - 1) { //벽 있으면 실패
			if(isWall(y, x, dir))
				return 0;
		}
		if(y == 0 && x == 0 && dir == 0) //성공
			return 1;

		//메모이제이션
		int ret = dp[y][x][dir];
		if(ret != -1)
			return ret;
		
		//로직
		switch(dir) {
		case 0: //현재 가로 <- 가로, 대각
			ret = go(y, x - 1, 0) + go(y - 1, x - 1, 2);
			break;
		case 1: //현재 세로 <- 세로, 대각
			ret = go(y - 1, x, 1) + go(y - 1, x - 1, 2);
			break;
		case 2: //현재 대각 <- 가로, 세로, 대각
			ret = go(y, x - 1, 0) + go(y - 1, x, 1) + go(y - 1, x - 1, 2);
			break;
		}
		return dp[y][x][dir] = ret;
	}
	
	/**
	 * 파이프 끝 부분에 벽이 있는지 확인하는 함수
	 * @param y : 파이프 시작 행
	 * @param x : 파이프 시작 행
	 * @param dir : 방향(0:가로, 1:세로, 2:대각)
	 */
	static boolean isWall(int y, int x, int dir) {
		switch(dir) {
		case 0: 
			if(matrix[y][x + 1] == 1)
				return true;
			break;
		case 1:
	        if(matrix[y + 1][x] == 1)
	            return true;
	        break;
		case 2:
	        if(matrix[y + 1][x + 1] == 1 || matrix[y + 1][x] == 1 || matrix[y][x + 1] == 1)
	            return true;
	        break;
	    }
	    return false;
	}
}