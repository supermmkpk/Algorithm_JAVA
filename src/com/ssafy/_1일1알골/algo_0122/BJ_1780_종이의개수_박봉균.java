package com.ssafy._1일1알골.algo_0122;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1780 : 종이의 개수
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72187395"></a>
 *
 * 결과: "맞았습니다!!", 315408KB, 1092ms
 */

/*
 * N×N 종이 : 각 칸에는 -1, 0, 1 중 하나
 * 1. 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * (1)이 아닌 경우, 종이 9등분, 각각의 잘린 종이에 대해서 (1)의 과정을 반복.
 * -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구하라.
[입력]
1) N (1 ≤ N ≤ 3^7, N은 3k 꼴)

[출력]
1) -1로만 채워진 종이의 개수
2) 0으로만 채워진 종이의 개수
3) 1로만 채워진 종이의 개수
 */
public class BJ_1780_종이의개수_박봉균 {
	
	//주어진 행렬을 저장하는 2차원 배열입니다.
	static int[][] matrix;
	//-1, 0, 1의 개수를 기록하는 변수입니다.
	static int minus1 = 0;
	static int zero = 0;
	static int plus1 = 0;
	
	public static void main(String[] args) throws IOException {
		//입출력을 위해 BufferedReader 및 BufferedWriter를 선언합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); //한변의 길이 N 입력 받습니다.
		
		//N*N 행렬을 생성합니다.
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {//N개 행에 대하여
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()); //각 칸의 수를 입력 받습니다.
			}
		}
		
		//종이 자르기를 수행합니다.
		go(0, 0, N);
		
		//출력 조건에 따라 -1, 0, 1의 개수를 출력합니다.
		bw.write(String.valueOf(minus1));
		bw.newLine();
		bw.write(String.valueOf(zero));
		bw.newLine();
		bw.write(String.valueOf(plus1));
		bw.newLine();
		bw.flush();
		
		//stream을 닫습니다.
		br.close();
		bw.close();
	}

	/**
	 * 종이가 모두 같은 수이거나 한 칸(최소 크기)일 때까지 종이를 9등분으로 자르는 함수입니다.
	 * @param sy : 시작하는 행 번호
	 * @param sx : 시작하는 열 번호
	 * @param n : 현재 종이의 크기
	 */
	static void go(int sy, int sx, int n) {
		//첫 칸의 수를 저장하는 변수입니다.
		int now = matrix[sy][sx];
		//종료조건
		if(n == 1) {
			switch(now) {
			case -1:
				minus1++;
				return;
			case 0: 
				zero++;
				return;
			case 1: 
				plus1++;
				return;
			default: 
				return;
			}
		}
		for(int i = sy; i < sy + n; i++) {
			for(int j = sx; j < sx + n; j++) {
				if(matrix[i][j] != now) { //현재와 다른 수가 있다면
					//9등분하고, 같은 과정을 수행합니다.
					go(sy, sx, n/3);
					go(sy, sx + n/3, n/3);
					go(sy, sx + 2*n/3, n/3);
					go(sy + n/3, sx, n/3);
					go(sy + n/3, sx + n/3, n/3);
					go(sy + n/3, sx + 2*n/3, n/3);
					go(sy + 2*n/3, sx, n/3);
					go(sy + 2*n/3, sx + n/3, n/3);
					go(sy + 2*n/3, sx + 2*n/3, n/3);
					return;
				}
			}
		}
		//모두 같은 수라면 숫자를 기록하고 종료합니다.
		switch(now) {
		case -1:
			minus1++;
			return;
		case 0: 
			zero++;
			return;
		case 1: 
			plus1++;
			return;
		default: 
			return;
		}
	}
}
