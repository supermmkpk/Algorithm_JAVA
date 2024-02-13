package com.ssafy._1일1알골.algo_0209;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2098 [G1] : 외판원 순회
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: DP -> 기저사례, 메모이제이션, 로직, 초기화
 * 결과: 17232KB, 152ms
 */

/* <문제 요약>
 * 1 ~ N번 도시들이 있고, 이들 사이에는 길이 있거나 없다.  
 * 외판원이 임의의 한 도시에서 출발해 N개 도시를 모두 거쳐 원래의 도시로 돌아오는 경로를 계획한다. (한 번 갔던 데는 다시 갈 수 없으나, 첫==마지막은 인정)
 * 최소 비용의 경로로 가고자 한다.
 * 도시 i에서 도시 j로 가기 위한 비용 = W[i][j]. (W[i][j], W[j][i]은 다를 수 있다, W[i][i]=0, 갈 수 없는 경우: W[i][j]=0)
 * N과 비용 행렬이 주어질 때, 최소 비용의 경로를 구하라.
[입력]
1) 도시 수 N. (2 ≤ N ≤ 16) 
N개 줄) 비용 행렬. (0 <= Wij <= 1e6, 갈 수 없는 경우는 0)

[출력]
1) 최소 비용.
 */
public class Main_2098_외판원순회_박봉균 {
	//오답: public static final int INF = Integer.MAX_VALUE; 
	// --> 의도는 큰 값으로 해서 후보에서 빼는 것인데 오버플로우가 나서 오답!! 
	// --> 따라서, INF는 항상 1e9로 좀 여유 있게 설정!!!
	public static final int INF = (int) 1e9;

	/** 도시 수 N */
	static int N;
	/** 비용 행렬 */
	static int[][] matrix = new int[16][16];
	/** dp 메모이제이션 */
	static int[][] dp = new int[16][1 << 16];
	
	static int go(int now, int visited) {
		//기저사례
		if(visited == (1 << N) - 1) {
			if(matrix[now][0] == 0)
				return INF; //길이 없으면 최대 반환해서 경우의 수에서 빼버리기
			else
				return matrix[now][0]; //비용 반환
		}
		
		//메모이제이션
		if(dp[now][visited] != 0) 
			return dp[now][visited];
		
		//로직 - 순열(순서 상관 있음!)
		dp[now][visited] = INF;
		for(int i = 0; i < N; i++) {
			if((visited & 1 << i) != 0) //이미 방문
				continue;
			if(matrix[now][i] == 0) //길이 없는 경우
				continue;
			
			//현재->다음 비용 더하여 비교, 메모이제이션
			dp[now][visited] = Math.min(dp[now][visited], go(i, visited | 1 << i) + matrix[now][i]); 
		}
		return dp[now][visited];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //도시 수 N 입력
		
		//비용행렬 matrix[][] 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) 
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//초기화 : 0 default
		
		//외판원 순회, 최소 비용 출력
		bw.write(String.valueOf(go(0, 1)) + '\n');	
		bw.flush();
		bw.close();
		br.close();
	}
}
