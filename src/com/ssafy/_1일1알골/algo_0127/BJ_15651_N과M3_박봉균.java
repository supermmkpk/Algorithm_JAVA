package com.ssafy._1일1알골.algo_0127;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ15651 : N과 M (3)
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/72467051"></a>
 *
 * 결과: "맞았습니다!!", 165792KB, 624ms
 */

/*
 * 1부터 N까지 자연수 중에서 M개를 고른 수열을 구하라.(중복 가능)
[입력]
1) 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

[출력]
각 줄) 조건을 만족하는 수열 출력. (중복되는 수열X, 사전 순 증가 순서)
*/
public class BJ_15651_N과M3_박봉균 {
	
	public static void main(String[] args) throws IOException {
		//입출력을 위해 BufferedReader 및 BufferedWriter를 선언합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//N,M 입력.
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] cnt = new int[M]; //각 열의 카운트
		int[] breakPnt = new int[M]; //각 열에서 숫자를 갱신할 수 있는 분기점 
		int val = 1;
		for(int col = M - 1; col >= 0; col--) {
			//각 열의 분기점은 N^(M-1-col)
			cnt[col] = breakPnt[col] = val;
			val *= N;
		}
		
		boolean exit = false; //종료 여부
		while(true) {
			for(int j = 0; j < M; j++) {
				//카운트와 분기점을 나누어 출력 값을 구해 출력.
				bw.write(String.valueOf(cnt[j] / breakPnt[j]) + " ");
				cnt[j]++;
				if(cnt[j] / breakPnt[j] > N) { //카운트 갱신 후 값이 N을 벗어나면,	
					if(j == 0) { //첫 열이라면 이번 행까지 출력하고 종료.
						exit = true;
						continue;
					}
					cnt[j] = breakPnt[j]; //카운트 초기화.
				}
			}
			bw.write('\n');
			if(exit) {
				break;
			}
		}
		//데이터 출력, stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
