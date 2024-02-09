package personal.week6_이분탐색_LIS;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ6236 [S2] : 용돈 관리
 * @author 박봉균
 * 
 * 아이디어: - 무조건 넣고 "K원" 인출! : 하루 사용 금액 중 최대 ~ 가능한 최대 금액 사이에서 이분탐색 
 *     - M번 이하에서 K원 인출해서 가능하면 된다. (나머지 횟수는 그냥 인출하면 되니까 : 모자라는 게 문제(즉, M번 넘는게 문제))
 * 결과: 20736KB, 212ms
 */

/* <문제 요약>
 * 현우는 N일 동안 사용할 금액을 계산하였고, M번만 통장에서 돈을 빼서 쓰기로 하였다. 
 * K원을 인출하며(고정), 이것으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라면 남은 금액은 통장에 넣고 다시 K원 인출. 
 * 현우는 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액을 통장에 집어넣고 다시 K원을 인출할 수 있다.
 * 필요한 최소 금액 K를 계산하라.
[입력]
1) N, M. (1 ≤ N ≤ 1e5, 1 ≤ M ≤ N)
N개 줄) i번째 날에 이용할 금액. (1 ≤ 금액 ≤ 1e4)

[출력]
통장에서 인출해야 할 최소 금액 K.
 */
public class BJ_6236_6C {
	/** 이용할 금액 배열 */
	static int[] money;
	/** 이용 금액 중 최댓값 */
	static int mx;
	/** 일수 N, 인출 횟수 M */
	static int N, M;
	/** 하한 lo, 상한 hi, 중간 mid */
	static int lo, hi, mid;
	/** 결과 저장 변수 */
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		money = new int[N];
		
		mx = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(br.readLine());
			mx = Math.max(mx, money[i]);
		}
		lo = mx; hi = (int) 1e9; //hi = 1e5 * 1e4
		
		//이분 탐색
		while(lo <= hi) {
			mid = (lo + hi) / 2;
			if(check(mid)) { //가능! : 더 작은 금액은?
				hi = mid - 1;
				ret = mid;
			}
			else //불가! : 더 큰 금액에서 다시 탐색
				lo = mid + 1;
		}
		
		System.out.println(ret);
	}
	
	static boolean check(int mid) { 
		int K = mid; //인출 금액 K
		int cnt = 1; //인출 횟수
		
		for(int i = 0; i < N; i++) { //N일 동안
			if(mid - money[i] < 0) { //사용 가능하지 않으면
				mid = K; //인출
				cnt++; //인출 카운트 증가
			}
			//돈 쓰기
			mid -= money[i];
		}
		return (cnt <= M);
	}

}
