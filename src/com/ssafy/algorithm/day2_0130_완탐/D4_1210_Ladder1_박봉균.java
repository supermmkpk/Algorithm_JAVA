package com.ssafy.algorithm.day2_0130_완탐;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * SWEA 1210 [D4] : Ladder1
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: 	- 목적지로부터 역행하여 출발지를 구한다.
 * 			- 왼쪽, 오른쪽 체크하여 사다리 있으면 열을 갱신한다.(있다면 행 안에서 처리, 없으면 행에 대해서는 계속 진행: 행 정보는 중요하지 않다)
 * 결과: Pass
 */

/* <문제 요약>
 * 사다리 타기를 한다.
 * 행렬로 주어진 사다리에 대해서, 지정된 도착점(2)에 대응되는 출발점 반환하라. 
 * 사다리: 1, 도착점: 2
[입력]
각 테케(10개)
	1) 테케 번호
	100개 줄) 100*100 행렬
[출력]
#테케번호 <공백> 출발점의열
 */
public class D4_1210_Ladder1_박봉균 {
	/** 사다리를 저장하는 2차원 배열 */
	static int[][] matrix = new int[100][100];
	/** 목적지의 열 (행:99) */
	static int destX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int t = 1; t <= 10; t++) { //10개의 테스트케이스에 대하여
			//테스트케이스 번호 입력(for문 t 사용하므로 무의미)
			br.readLine();
			//사다리 정보 입력
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 2) 
						destX = j; //목적지 열 기록
					matrix[i][j] = tmp;
				}
			}
			
			//목적지부터 위로 역행
			int x = destX;
			for(int y = 98; y > 0; y--) { //첫,마지막 행은 무의미
				int nxLeft = x - 1; //왼쪽 확인
				int nxRight = x + 1; //오른쪽 확인
				if(nxLeft >= 0 && matrix[y][nxLeft] == 1) { //오버,언더, 사다리 여부 체크
					x--; //검증 후 열 감소(왼쪽 이동)
					while(true) {
						nxLeft = x - 1; //왼쪽 확인
						if(nxLeft < 0 || matrix[y][nxLeft] == 0) //오버,언더, 사다리 여부 체크
							break;
						x--; //검증 후 열 감소(왼쪽 이동)
					}
				}
				if(nxRight < 100 && matrix[y][nxRight] == 1) { //오버,언더, 사다리 여부 체크
					x++; //검증 후 열 증가(오른쪽 이동)
					while(true) {
						nxRight = x + 1; //오른쪽 확인
						if(nxRight >= 100 || matrix[y][nxRight] == 0) //오버,언더, 사다리 여부 체크
							break;
						x++; //검증 후 열 증가(오른쪽 이동)
					}
				}
			}
			//출력 조건에 따라 출발지의 열번호 출력
			bw.write("#" + t + " " + x + '\n'); 
		}
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
