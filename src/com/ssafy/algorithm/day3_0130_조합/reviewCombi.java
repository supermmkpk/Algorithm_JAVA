package com.ssafy.algorithm.day3_0130_조합;
import java.io.*;
import java.util.*;


/*
 * BJ_15650[S3] : 조합
 * 순서를 고려하지 않으며 뽑기
 */
public class reviewCombi {
	static int N, M, ret[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ret = new int[M];
		
		combi(1, 0);
		
		System.out.println(sb.toString());
		br.close();
	}
	
	// 조합 : C(N, M) -> start, cnt, visited X
	static void combi(int start, int cnt) {
		// 종료 조건
		if(cnt == M) {
			for(int i : ret) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 일단 나 다음부터
		for(int i = start; i <= N; i++) {
			ret[cnt] = i;
			combi(i + 1, cnt + 1);
			
		}
	}
	
}
