package com.ssafy.algorithm.day7_0206_Tree;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ16935 [G5] : 배열 돌리기 3
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/73043585"></a>
 * 
 * 아이디어: 행,열 크기 변경 가능하므로 최대크기의 tmp 배열에 연산 결과 저장(행,열수 갱신)하고 다시 원본 배열에 복사하는 과정 반복
 * 결과: 108204KB, 500ms
 */

/* <문제 요약>
 * 1. 상하 반전
 * 2. 좌우 반전
 * 3. 오른쪽 90도 회전
 * 4. 왼쪽 90도 회전
 * 5. 사분면에 대해 시계방향 회전
 * 6. 사분면에 대해 시계방향 회전
[입력]
1) 배열의 크기 N, M, 연산 수 R. (2 ≤ N, M ≤ 100, 1 ≤ R ≤ 1,000, N, M은 짝수)
N개 줄) 배열 A의 원소 Aij. (1 ≤ Aij ≤ 1e8)
마지막 줄) 수행해야 하는 연산 번호. (여러개: delim = <공백>)

[출력]
주어진 배열에 대해, 연산을 순서대로 수행한 결과 출력.
 */
public class Main_16935_배열돌리기3_박봉균 {
	/** 주어지는 배열 */
	static int[][] A;
	/** 행수 N, 열수 M, 연산수 R */
	static int N, M, R;
	/** 연산 시 사용할 임시 배열 */
	static int[][] tmp = new int[104][104];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행수 N, 열수 M, 연산수 R 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		//A[][] 동적 할당
		A = new int[N][M];
		
		//A[][] 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//연산 번호  cmd 입력
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int command = Integer.parseInt(st.nextToken());
			go(command);
		}
		
		//연산 결과 출력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				bw.write(String.valueOf(tmp[i][j]) + " ");
			bw.write('\n');
		}
		
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	/**
	 * 연산 번호에 대하여 연산 수행 후,A[][] 배열 갱신
	 * @param cmd : 연산 번호
	 */
	static void go(int cmd){
		
		switch(cmd) {
		
		//1. 상하 반전
		case 1:
			for(int i = 0; i < N; i++) 
				for(int j = 0; j < M; j++) 
					tmp[N - 1 - i][j] = A[i][j];
			
			break;
			
			
		//2. 좌우 반전
		case 2:
			for(int j = 0; j < M; j++) 
				for(int i = 0; i < N; i++) 
					tmp[i][M - 1 - j] = A[i][j];
			
			break;
		
			
		//3. 오른쪽 90도 회전
		case 3: 
			//N=6,M=6 : 1행(row)0열(col) -> 0행(col)4열(N-1-row)
			for(int i = 0; i < N; i++) 
				for(int j = 0; j < M; j++) 
					tmp[j][N - 1 - i] = A[i][j];
			
			//90도 회전했으므로 행수, 열수 반전 
			int swap = N;
			N = M;
			M = swap;
			break;
			
			
		//4. 왼쪽 90도 회전
		case 4:
			//N=6,M=6 : 1행(row)0열(col) -> 5행(M - 1 - col)1열(row)
			for(int i = 0; i < N; i++) 
				for(int j = 0; j < M; j++) 
					tmp[M - 1 - j][i] = A[i][j];
			
			//90도 회전했으므로 행수, 열수 반전 
			swap = N;
			N = M;
			M = swap;
			break;
			
			
		//5. 사분면에 대해 시계방향 회전
		case 5:
			//1 -> 2
			for(int i = 0; i < N/2; i++) 
				for(int j = 0; j < M/2; j++) 
					tmp[i][j + M/2] = A[i][j];
			
			//2 -> 3
			for(int i = 0; i < N/2; i++) 
				for(int j = M/2; j < M; j++) 
					tmp[i + N/2][j] = A[i][j];
			
			//3 -> 4
			for(int i = N/2; i < N; i++) 
				for(int j = M/2; j < M; j++) 
					tmp[i][j - M/2] = A[i][j];
			
			//4 -> 1
			for(int i = N/2; i < N; i++) 
				for(int j = 0; j < M/2; j++) 
					tmp[i - N/2][j] = A[i][j];
				
			break;
			
			
		//6. 사분면에 대해 시계방향 회전
		case 6:
			//1 -> 4
			for(int i = 0; i < N/2; i++) 
				for(int j = 0; j < M/2; j++) 
					tmp[i + N/2][j] = A[i][j];
			
			//4 -> 3
			for(int i = N/2; i < N; i++) 
				for(int j = 0; j < M/2; j++) 
					tmp[i][j + M/2] = A[i][j];
			
			//3 -> 2
			for(int i = N/2; i < N; i++) 
				for(int j = M/2; j < M; j++) 
					tmp[i - N/2][j] = A[i][j];
			
			//2 -> 1
			for(int i = 0; i < N/2; i++) 
				for(int j = M/2; j < M; j++) 
					tmp[i][j - M/2] = A[i][j];
			
			break;
		}
		
		/* 다시 A로 */
		A = new int[N][M];
		
		//갱신된 N(행수), M(열수)에 대하여 A <- tmp 로 복사 
		for(int i = 0; i < N; i++) 
			A[i] = Arrays.copyOf(tmp[i], M); 
	}
	
}
