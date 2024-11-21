package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;
import java.io.*;

/*
 * BJ_2961[S2] : 부분집합(멱집합)
 * 넣거나 말거나
 */
public class reviewPowerSet2 {	
	static int N;
	static int ret = Integer.MAX_VALUE;
	static Pair[] ingr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		ingr = new Pair[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			ingr[i] = new Pair(sour, bitter);
		}
		
		// 멱집합(넣거나 말거나, 1개 이상)
		for(int i = 1; i < 1 << N; i++) {
			//현재 조합에 대하여
			int sourSum = 1;
			int bitterSum = 0;
			
			//각 재료 확인
			for(int j = 0; j < N; j++) {
				if((i & 1 << j) != 0) { //포함
					sourSum *= ingr[j].sour;
					bitterSum += ingr[j].bitter;
				} 
			}
			
			ret = Math.min(ret, Math.abs(sourSum - bitterSum));
		}
		
		System.out.println(ret);
		br.close();
	}
	
	static class Pair {
		int sour, bitter;
		Pair() {}
		Pair(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}

}
