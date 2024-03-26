package com.ssafy.algorithm.day21_Season1END;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 결과: 	11704KB, 76ms
 */

/* <BJ10971_S2 : 외판원 순회 2>
 * 1~N번 도시들이 있고, 이들 사이에는 길이 있거나 없다. 
 * 외판원이 임의의 한 도시에서 출발해 N개의 도시를 모두 거쳐 원래 도시로 돌아오는 순회 여행을 하려 한다. 단, 한 번 갔던 도시로는 다시 갈 수 없다.
 * 최소 비용 경로의 비용을 구하라.
 * 도시 i에서 도시 j로 가기 위한 비용은 행렬 W[i][j]형태로 주어진다. 비용은 대칭적이지 않다. W[i][i]는 항상 0이다.도시 i에서 도시 j로 갈 수 없는 경우 W[i][j]=0.
[입력]
1) 도시의 수 N. (2 ≤ N ≤ 10)
N개 줄) 비용 행렬. (0 <= 비용 <= 1e6, 갈 수 없는 경우: 0)
[출력]
순회에 필요한 최소 비용
 */
public class Main_10971_외판원순회2_박봉균 {
	static final int INF = (int)1e9;
	
	static int N, matrix[][];
	static int[][] dp; //상태값: 출발, 방문(visited 비트마스킹)
	
	static int go(int now, int visited) {
		//기저사례: 모두 순회 완료
		if(visited == (1 << N) - 1) {
			if(matrix[now][0] == 0) //처음으로의 길 없으면 불가능한 경우
				return INF;
			else
				return matrix[now][0]; //처음으로의 거리 반환(마지막으로 더해주기)
		}
		
		
		//메모이제이션
		int ret = dp[now][visited];
		if(ret != INF)
			return ret;
		
		//로직: 방문하지 않은 곳 가기
		for(int i = 0; i < N; i++) {
			if((visited & 1 << i) != 0)
				continue;
			if(matrix[now][i] == 0) //길 없는 경우
				continue;
			ret = Math.min(ret, go(i, visited | 1 << i) + matrix[now][i]);
		}
		
		return dp[now][visited] = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //도시 수(행렬 크기)
		matrix = new int[N][N]; //비용 행렬
		dp = new int[N][1 << N];
		
		//비용 행렬 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dp 초기화
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], INF);
		
		//외판원 순회, 최소 비용 출력
		bw.write(String.valueOf(go(0, 1)) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
