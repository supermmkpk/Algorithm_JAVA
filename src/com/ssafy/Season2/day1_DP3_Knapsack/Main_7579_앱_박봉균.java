package day1_DP3_Knapsack;

import java.io.*;
import java.util.*;

/*
 * 앱들이 활성화 되어 있다는 것은 화면에 보이지 않더라도 메인 메모리에 직전의 상태가 기록되어 있는 것을 말한다. 
 * N개 앱, A1, ..., AN이 활성화 되어 있다.
 * Ai는 각각 mi 바이트만큼의 메모리를 사용하고 있다.
 * Ai를 비활성화한 후에 다시 실행하고자 할 경우, 추가적으로 들어가는 비용(시간 등)을 ci. 
 * 사용자가 새로운 앱 B를 실행하고자 하여, 추가로 M 바이트의 메모리가 필요하다고 하자. 
 * 현재 활성화 되어 있는 앱 A1, ..., AN 중에서 몇 개를 비활성화 하여 M 바이트 이상의 메모리를 추가로 확보해야 하는 것이다. 
 * 그 중에서 비활성화 했을 경우의 비용 ci의 합을 최소화하여 필요한 메모리 M 바이트를 확보하는 방법을 찾자.
[입력]
1) 활성화 앱 수 N, 필요 메모리 M (1 ≤ N ≤ 100, 1 ≤ M ≤ 1e7)
2) 현재 활성화 되어 있는 앱 A1, ..., AN이 사용 중인 메모리의 바이트 수인 m1, ..., mN (1 ≤ m1, ..., mN ≤ 1e7)
3) 각 앱을 비활성화 했을 경우의 비용 c1, ..., cN (0 ≤ c1, ..., cN ≤ 100 , M ≤ m1 + m2 + ... + mN)
[출력]
M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용
 */
public class Main_7579_앱_박봉균 {
	static int N, M;
	static int memory[], cost[], dp[][];

	// 누적 비용에 대해 나올 수 있는 최대 메모리(DP)
	// dp값: 비활성 메모리, 상태값: 현재 확인 중인 앱, 남은 비활성 비용
	static int go(int cnt, int costRemain) { 		
		// 기저사례
		if (cnt == N) // 모두 고려하였다면
			return 0;

		// 메모이제이션
		int ret = dp[cnt][costRemain];
		if (ret != -1)
			return ret;

		// 로직
		int newRemain = costRemain - cost[cnt];
		if (newRemain >= 0) { // 가능하면
			ret = Math.max(ret, go(cnt + 1, newRemain) + memory[cnt]); // 비활성화하거나
		}
		return dp[cnt][costRemain] = Math.max(ret, go(cnt + 1, costRemain)); // 안하거나
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //활성화 앱 수 N
		M = Integer.parseInt(st.nextToken()); //필요 메모리 M
		memory = new int[N + 4];  //메모리
		cost = new int[N + 4]; //비활성화 비용

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int costSum = 0; // 모든 비용의 합
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}

		// dp 초기화
		dp = new int[N + 4][costSum + 4];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		// 가능한 비용에 대하여
		for (int i = 0; i <= costSum; i++) {
			// 최초로 비활성 메모리가 M이상이 되는 경우(최소비용)
			if (go(0, i) >= M) {
				bw.write(String.valueOf(i) + '\n'); // 출력
				break;
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
