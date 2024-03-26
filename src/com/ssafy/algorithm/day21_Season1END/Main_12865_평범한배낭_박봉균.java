package com.ssafy.algorithm.day21_Season1END;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 아이디어: 배낭을 포함하거나 안 하거나! ==> 멱집합 구하자!! 근데...
 *     ==> N 너무 크다, 비트마스킹도 안 된다. 누적가치, 남은 가능 무게를 저장해 나가면서 동적계획법으로 접근하는 건 어떨까?  
 * 결과: 53020KB, 172ms
 */

/* <BJ12865_G5 : 평범한 배낭>
 * N개 물건이 있다.
 * 각 물건은 무게 W, 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
 * 배낭에는 최대 K만큼의 무게만을 넣을 수 있다.
 * 배낭에 넣을 수 있는 물건들의 가치의 최댓값은?
[입력]
1) 물건 수 N, 버틸 수 있는 무게 K. (1 ≤ N ≤ 100, 1 ≤ K ≤ 1e5)가 주어진다. 
N개 줄) 각 물건의 무게 W, 해당 물건의 가치 V. (1 ≤ W ≤ 1e5, 0 ≤ V ≤ 1e3)
[출력]
물건들의 가치합의 최댓값
 */
public class Main_12865_평범한배낭_박봉균 {
	/** 물건 수 N, 버틸 수 있는 무게 K */
	static int N, K;
	/** 물건 객체{weight, value} 배열 */
	static Thing[] things;
	/** dp값: 누적 가치. 상태값: 현재 고려중인 원소의 인덱스, 남은 무게 */
	static int[][] dp; 
	
	/**
	 * 동적 계획법으로 배낭에 포함할 물건을 정하고 누적가치 최댓값을 저장하는 함수.
	 * 포함할거냐, 안 할거냐 ==> 멱집합 구하자! (N 최대 100이므로 비트마스킹 불가!)
	 * @param cnt : 고려 중인 물건 인덱스
	 * @param remainWeight : 남은 무게
	 * @return : dp값 = 누적 가치 
	 */
	static int go(int cnt, int remainWeight) {
		//기저사례: 모든 물건 고려
		if(cnt == N) 
			return 0;
		
		//메모이제이션
		int ret = dp[cnt][remainWeight];
		if(ret != -1)
			return ret;
		
		//로직
		int newWeight = remainWeight - things[cnt].weight;
		if(newWeight >= 0) 
			ret = Math.max(ret, go(cnt + 1, newWeight) + things[cnt].value); //버틸 수 있으면 포함하거나
		
		return dp[cnt][remainWeight] = Math.max(ret, go(cnt + 1, remainWeight)); //안하거나(그대로)
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //물건 개수
		K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게
		things = new Thing[N + 4]; //물건 객체 배열
		dp = new int[N + 4][K + 4];
		
		//물건 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); //무게
			int V = Integer.parseInt(st.nextToken()); //가치
			
			things[i] = new Thing(W, V);
		}
		
		//dp 초기화
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		
		//동적 계획법으로 최대 가치 경우를 구하고, 출력
		bw.write(String.valueOf(go(0, K)) + '\n');
		bw.flush();
		bw.close();
		br.close();	
	}
	
	/** 물건 객체 클래스 */
	static class Thing {
		int weight, value;
		Thing() {}
		Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
}