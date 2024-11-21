package com.ssafy.algorithm.day2_0130_완탐;
import java.io.*;
import java.util.*;


/**
 *  BJ_14889[S1] : 스타트와 링크
 */
public class reviewPermuCombi {
	static int N, matrix[][], numStart[], numLink[];
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		numStart = new int[N/2];
		numLink = new int[N/2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
		
		combi(0, 0, 0);
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	//C(N , N/2) - 재귀+비트마스킹
	static void combi(int cnt, int start, int visited) {
		//종료조건
		if(cnt == N/2) {
			int idxStart = 0, idxLink = 0;
			for(int i = 0; i < N; i++) {
				if((visited & 1 << i) != 0) 
					numStart[idxStart++] = i;
				else 
					numLink[idxLink++] = i;
			}
			int sumStart = permu(numStart);
			int sumLink = permu(numLink);
			ret = Math.min(ret, Math.abs(sumStart - sumLink));
			return;
		}
		//조합
		for(int i = start; i < N; i++)
			combi(cnt + 1, i + 1, visited | 1 << i);
	}
	
	//P(N/2 , 2) - 이중for문
	static int permu(int[] input) {
		int sum = 0;
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				if(i == j)
					continue;
				sum += matrix[input[i]][input[j]];
			}
		}
		return sum;
 	}

}
