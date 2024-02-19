package com.ssafy._1일1알골.algo_0218;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ11403 [S1] : 경로 찾기
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: DFS 탐색, 이미 경로 있는 거 알면 탐색 안하기.
 * 결과: 15172KB, 156ms
 */

/* <요약> (시간: 1초)
 * 가중치 없는 방향 그래프 G가 주어질 때, 모든 정점 (i, j)에 대해, i에서 j로 가는 길이가 양수인 경로가 있는지 구해보자.
[입력]
1) 정점 수 N. (1 ≤ N ≤ 100)
N개 줄) 그래프의 인접 행렬. (i행 i열은 항상 0이다)
[출력]
정답 행렬. (i -> j 경로 길이 > 0 : 1, else: 0)
 */
public class Main_11403_경로찾기_박봉균 {	
	/** 정점 수 N */
	static int N;
	/** 인접 행렬 */
	static int[][] matrix;
	/** 정점 방문체크 배열 */
	static boolean[] visited;
	/** 결과 행렬 */
	static int[][] ret;
	
	static void dfs(int now, int init) {
		for(int next = 0; next < N; next++) { 
			if(visited[next]) //방문한 정점이면 continue
				continue;
			if(ret[init][next] == 1) //경로 있는 거 이미 알면 continue
				continue;
			
			//(현재정점->다음정점) 간선 존재하면, (초기정점->다음정점) : 1 
			if(matrix[now][next] == 1) {
				ret[init][next] = 1;
				visited[next] = true; //방문체크
				dfs(next, init); //다음 정점 계속 탐색
				visited[next] = false; //원상복구
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//정점 수 N 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		visited = new boolean[N];
		ret = new int[N][N];

		//인접 행렬 matrix[][] 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				matrix[i][j] =  Integer.parseInt(st.nextToken());
		}
		
		//모든 정점에 대하여 DFS탐색
		for(int i = 0; i < N; i++) {
			if(ret[i][i] == 0)
				dfs(i, i);
		}
		
		//결과 행렬 출력
		for(int[] row : ret) {
			for(int col : row)
				bw.write(String.valueOf(col) + " ");
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
