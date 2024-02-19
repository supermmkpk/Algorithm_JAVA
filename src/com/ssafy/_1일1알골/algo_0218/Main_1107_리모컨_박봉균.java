package com.ssafy._1일1알골.algo_0218;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1107 [G5] : 리모컨
 * </pre>
 * @author 박봉균
 * 아이디어: 그리디(각 자리 숫자 별로 최근접 숫자 고르기) - 지역적 최적해 -> 전역 최적해 증명 못해서 실패
 *     ==> 완탐으로 모든 숫자 체크하기
 * 결과: 69796KB, 332ms
 */

/* 
 * 리모컨에는 0~9, +, - 가 있다. + 누르면 채널++, - 누르면 채널--. 채널 0에서 -를 누른 경우, 채널이 변하지 않고, 채널은 무한대 만큼 있다.
 *  지금 보고 있는 채널은 100번이고,  N번으로 이동하려 한다.
 *  어떤 버튼이 고장났는지 주어질 때, 버튼을 최소 몇 번 눌러야 할까?
[입력]
1) 이동하려고 하는 채널 N (0 ≤ N ≤ 5e5)
2) 고장난 버튼의 개수 M (0 ≤ M ≤ 10).
3) 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.
[출력]
1) 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력.
 */
public class Main_1107_리모컨_박봉균 {
	public static final int MAX = (int) 1e6;

	/** 이동하고자 하는 번호 N, 고장난 버튼 수 M */
	static int N, M;
	/** 버튼 사용가능 여부 (0~9번) */
	static boolean[] buttons = new boolean[10];
	/** 결과 변수 : 버튼 누르는 최소 횟수 */
	static int ret = MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 이동하고자 하는 번호 N, 고장난 버튼 수 M 입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Arrays.fill(buttons, true);
		
		//고장난 버튼 있을 경우, 입력
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				buttons[Integer.parseInt(st.nextToken())] = false;
		}

		/* 모든 숫자를 탐색하면서, 누르는 횟수가 최소가 되는 경우를 구합니다. */
		int ret = Math.abs(N - 100); // +, - 만 사용하는 경우로 초기화
		for (int i = 0; i <= MAX; i++) {
			String strI = String.valueOf(i);

			//고장난 버튼이 포함되면 불가능한 경우 -> continue
			boolean broken = false;
			for (int j = 0; j < strI.length(); j++) {
				if (buttons[strI.charAt(j) - '0'] == false) {
					broken = true;
					break;
				}
			}
			if(broken)
				continue;
			
			
			//가능한 경우, 결과 최소로 갱신
			ret = Math.min(ret, Math.abs(N - i) + strI.length());
		}
		// 결과(버튼 누르는 최소 횟수) 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
