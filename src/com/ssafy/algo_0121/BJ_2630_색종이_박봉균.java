package com.ssafy.algo_0121;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2630 : 색종이 만들기
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72082869"></a>
 *
 * 결과: "맞았습니다!!", 15668KB, 156ms
 */

/*
 * 모두 같은 색으로 칠해져 있지 않으면 가로 세로로 중간 부분을 잘라서 똑같은 크기의 네 개의 N/2 × N/2 색종이로 나눈다. 
 * 나누어진 종이  각각에 대해서도 같은 방법으로 네 개의 색종이로 나눈다.
 * 이 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
 * 모든 과정 수행 후, 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하라.

[입력]
1) 전체 종이의 한 변의 길이 N. (N = 2, 4, 8, 16, 32, 64, 128 중 하나)
N개 줄) 색종이의 각 칸의 색 (0: 하얀색, 1: 파란색)

[출력]
1) 잘라진 햐얀색 색종이 개수
2) 파란색 색종이의 개수
 */
public class BJ_2630_색종이_박봉균 {
	//전체 종이의 색을 저장합니다.
	static int[][] a = new int[132][132];
	//파란색, 하얀색의 개수를 저장하는 변수입니다.
	static int blue = 0;
	static int white = 0;

	public static void main(String[] args) throws IOException {
		//전체 종이의 각 칸의 색을 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //한변의 길이 N을 입력 받습니다.
		for(int i = 0; i < N; i++) { //각 행에 대하여
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken()); //각 칸(열)의 색을 입력 받습니다. 
			}
		}
		//색종이 자르기를 수행합니다.
		go(0, 0, N);
	
		//출력 조건에 따라 하얀색, 파란색 색종이의 개수를 출력합니다.
		System.out.println(white);
		System.out.println(blue);
	}

	/**
	 * 색이 모두 같지 않으면 크기가 1이 될 때까지 4등분하는 함수입니다.(재귀)
	 * @param sy 시작하는 행번호
	 * @param sx 시작하는 열번호
	 * @param n 현재 색종이의 크기(한변 길이)
	 */
	static void go(int sy, int sx, int n) {
		int color = a[sy][sx]; //첫 칸의 색을 저장합니다.
		//종료조건
		if(n == 1) { //크기가 1이라면
			//색을 기록하고 return
			if(color == 1) {
				blue++;
			}
			else {
				white++;
			}
			return;
		}
		//각 칸에 대하여
		for(int i = sy; i < sy + n; i++) {
			for(int j = sx; j < sx + n; j++) {
				if(a[i][j] != color) { //색이 다른 것이 존재하면
					//4등분한 각 색종이에서 동일한 과정을 수행합니다.
					go(sy,sx,n/2);
					go(sy, sx + n/2, n/2);
					go(sy + n/2, sx, n/2);
					go(sy + n/2, sx + n/2, n/2);
					return;
				}
			}
		}
		
		//색이 모두 같다면, 색을 기록하고 return
		if(color == 1) {
			blue++;
		}
		else {
			white++;
		}
		return;
	}
	
}
