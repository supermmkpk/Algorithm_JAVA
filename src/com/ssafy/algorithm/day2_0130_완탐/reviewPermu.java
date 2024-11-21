package com.ssafy.algorithm.day2_0130_완탐;
import java.io.*;
import java.util.*;

/*
 * BJ_15649[S3] : 순열
 * 순서를 고려하여 모두 뽑기
 */
public class reviewPermu {
	
	static int M, N, numbers[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		
		permu(0, 0);
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	//순열 : P(N, M) -> cnt, visited(방문체크!)
	static void permu(int cnt, int visited) {
		if(cnt == M) {
			for(int i : numbers) {
				sb.append(i + " "); 
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if((visited & 1 << i) != 0) {
				continue;
			}
			numbers[cnt] = i;
			permu(cnt + 1, visited | 1 << i);
		}
		
		
	}

   
}