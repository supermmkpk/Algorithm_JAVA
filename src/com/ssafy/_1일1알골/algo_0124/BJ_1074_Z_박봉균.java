package com.ssafy._1일1알골.algo_0124;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1074 : Z
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72311291"></a>
 *
 * 결과: "맞았습니다!!", 14244KB, 124ms
 * 오답: 재귀 접근으로 인한 시간 초과. -> 반복문(사분면 체크 아이디어)로 재접근.
 * 		=> 하단 코드 참조.
 */

/*
 * 2^N × 2^N 행렬을 Z모양으로 탐색하려 한다.
 * N > 1 : 2^(N-1) * 2^(N-1)로 4등분 한 후, 재귀적으로 순서대로 방문한다. -> 종료조건! : N==1
 * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하라.
[입력]
1) N, r, c (1 ≤ N ≤ 15) , (0 ≤ r, c < 2^N) -> 행,열 번호는 0부터.
	
[출력]
r행 c열을 몇 번째로 방문했는지 출력.
 */
public class BJ_1074_Z_박봉균 {

	public static void main(String[] args) throws IOException {
		//입력 조건에 따라 N, r, c를 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		//앞의 원소 개수를 저장할 변수입니다.(앞 원소 개수 = 방문 순서)
		int sum = 0;
		//2^N * 2^N 행렬에서 사분면을 체크하며 r행 c열을 몇 번째로 방문하는지 확인합니다.
		//i : 현 사분면의 한변 길이, 사분면의 한변 길이가 1일 때까지 반복합니다.
		//계속 4등분하므로 한변 길이는 /2
		for(int i = (int)Math.pow(2,N); i > 0; i /= 2) { 
			//4등분한 각 구역의 원소 개수를 계산합니다.
			int tmpSum = i * i / 4;
			//System.out.println(i +":" +tmpSum + ", " + sum); //디버깅 코드
			
			//사분면 체크
			//sum, r, c 갱신
			if(r < i/2 && c < i/2) { //1사분면(왼쪽 위)
				sum += tmpSum * 0; //그대로
			}
			else if(r < i/2 && c >= i/2) { //2사분면(오른쪽 위)
				c -= i/2; //다음 과정을 위해 c(열 번호)를 갱신합니다. 
				sum += tmpSum * 1; //2사분면의 시작은 1사분면 원소 개수입니다.
			}
			else if(r >= i/2 && c < i/2) { //3사분면(왼쪽 아래)
				r -= i/2; //다음 과정을 위해 r(행 번호)를 갱신합니다. 
				sum += tmpSum * 2; //3사분면의 시작은 1사분면+2사분면 원소 개수입니다.
			}
			else if(r >= i/2 && c >= i/2) { //4사분면(오른쪽 아래)
				//다음 과정을 위해 r(행 번호), c(열 번호)를 갱신합니다. 
				r -= i/2; 
				c -= i/2;  
				sum += tmpSum * 3; //4사분면의 시작은 1사분면+2사분면+3사분면 원소 개수입니다.
			}
		}
		//r행 c열의 방문 순서를 출력합니다.
		System.out.println(sum);
		
		br.close(); //stream을 닫습니다.
	}
}



/** 
 * 오답 코드(재귀적 접근) 
 */

/*
public class Main {

	//Z모양 방향벡터를 생성합니다.
	public static final int[] dy = {0, 0, 1, 1};
	public static final int[] dx = {0, 1, 0, 1};
	static int N, r, c, cnt, ret;

	//재귀적으로 Z모양으로 탐색하는 함수입니다.
	//sy: 시작행번호, sx: 시작 열번호, n: 한변 길이
	static void goZ(int sy, int sx, int n) {
		//종료 조건!!
		if(n <= 2) { //2*2가 되었을때 Z탐색하고 탐색마다 cnt를 증가시킵니다.
			for(int d = 0; d < 4; d++) { //4방향에 대하여
				int ny = sy + dy[d];
				int nx = sx + dx[d];
				if(ny == r && nx == c) { //r행, c열과 일치하면 현cnt를 결과 변수 cnt에 저장하고 종료.
					ret = cnt; 
					return;
				}
				cnt++; //탐색 시, cnt 증가.
			}
			return;
		}
		//4등분하여 각 구역에 대하여 같은 과정을 반복합니다.
		goZ(sy, sx, n/2);
		goZ(sy, sx + n/2, n/2);
		goZ(sy + n/2, sx, n/2);
		goZ(sy + n/2, sx + n/2, n/2);
		return;
	}
	
	public static void main(String[] args) throws IOException {
		//입력 조건에 따라 N, r, c를 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//행렬은 2^N*2^N이며, Z탐색을 수행합니다.
		goZ(0, 0, (int) Math.pow(2, N));
		//결과 출력.
		System.out.println(ret);
	}
}
*/
