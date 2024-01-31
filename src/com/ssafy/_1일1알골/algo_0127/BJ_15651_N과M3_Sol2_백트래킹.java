package com.ssafy._1일1알골.algo_0127;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15651 : N과 M (3)
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72467722"></a>
 *
 * 결과: "맞았습니다!!", 188272KB, 640ms
 */

/*
 * 1부터 N까지 자연수 중에서 M개를 고른 수열을 구하라.(중복 가능)
[입력]
1) 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

[출력]
각 줄) 조건을 만족하는 수열 출력. (중복되는 수열X, 사전 순 증가 순서)
*/
public class BJ_15651_N과M3_Sol2_백트래킹 {
	static int N, M;
	static LinkedList<Integer> result = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static void go() throws IOException {
		//종료조건
		if(result.size() == M) {
			for(int i : result) {
				bw.write(String.valueOf(i) + " ");
			}
			bw.write('\n');
			return;
		}
		for(int i = 1; i <= N; i++) {
			result.addLast(i);
			go();
			result.removeLast();
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
        
		go();
        bw.flush();
        bw.close();
        br.close();
	}
}
