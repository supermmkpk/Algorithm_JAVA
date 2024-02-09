package com.ssafy.algorithm.day8_0207_Tree_DFS;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ2563 [S5] : 색종이
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 *
 * 아이디어: 도화지를 0, 1 행렬처럼 표현. 검은 도화지 들어오면 이미 1이 아니라면 1로 바꿔주기. -> 검은 영역 = 1의 개수
 * 결과: 11608KB, 76ms
 */

/* <문제 요약>
 * 100*100 도화지가 있다.
 * 이 위에 10*10 검은색 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다.
 * 이러한 방식으로 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 검은 영역의 넓이를 구하라.
[입력]
1) 색종이의 수 N. (N <= 100)
N개 줄) 색종이를 붙인 위치 y, x. (y: 행, x:열, 도화지 밖으로 나가는 경우는 없다)

[출력]
1) 색종이가 붙은 검은 영역의 넓이.
 */
public class Main_2563_색종이_박봉균 {
	/** 도화지 행렬 (초기: 0)*/
	static int[][] paper = new int[104][104];
	/** 결과 변수 : 검은 칸 개수 */
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //색종이 수 N 입력
		
		//N개의 색종이에 대하여
		for(int t = 1; t <= N; t++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()); //색종이 영역의 시작 행
			int x = Integer.parseInt(st.nextToken()); //색종이 영역의 시작 열
			
			//10*10 색종이에 대하여
			for(int i = y; i < y + 10; i++) { 
				for(int j = x; j < x + 10; j++) {
					if(paper[i][j] == 0) { //빈칸이면 검은색으로 채웁니다.
						paper[i][j] = 1;
						ret++; //검은색 카운트 증가
					}
				}
			}
		}
		System.out.println(ret); //검은색 칸수 출력
	}
}
