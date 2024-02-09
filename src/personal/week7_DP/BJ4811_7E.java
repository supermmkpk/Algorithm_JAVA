package personal.week7_DP;
import java.io.*;

/*
 * 종수 할아버지는 매일 약 반알을 먹는다.
 * 선영이는 할아버지에게 약이 N개 담긴 병을 주었다.
 * 첫째 날에 종수는 병에서 약 하나를 꺼낸다. 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
 * 다음 날부터 종수는 병에서 약을 하나 꺼낸다. (약은 한 조각 전체 일 수도 있고, 쪼갠 반 조각 일 수도 있다)
 * 반 조각이라면 그 약을 먹고, 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
 * 손녀에게 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다. 손녀는 할아버지에게 받은 문자를 종이에 기록해 놓는다.
 * 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다. 이때, 가능한 서로 다른 문자열의 개수는 총 몇 개일까?
[입력]
최대 1000개의 테스트 케이스.
각 테케] 1) 약의 개수 N. (N ≤ 30)
마지막 줄에는 0.

[출력]
각 테케에 대해 가능한 문자열의 개수.
 */

/** DP
 * 기저사례, 메모이제이션, 로직, 초기화
 */
public class BJ4811_7E {
	static int N;
	static long[][] dp = new long[31][31];
	
	static long go(int whole, int half) {
		//기저사례
		if(whole == 0 && half == 0) 
			return 1;
		
		//메모이제이션
		if(dp[whole][half] != 0)
			return dp[whole][half];
		
		//로직
		if(whole > 0) 
			dp[whole][half] += go(whole - 1, half + 1);
		if(half > 0)
			dp[whole][half] += go(whole, half - 1);
			
		return dp[whole][half];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			bw.write(String.valueOf(go(N, 0)) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
