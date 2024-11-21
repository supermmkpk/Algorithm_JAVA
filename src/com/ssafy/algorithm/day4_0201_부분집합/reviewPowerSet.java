package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;
import java.io.*;

/*
 * BJ_2961[S2] : 부분집합(멱집합)
 * 넣거나 말거나
 */
public class reviewPowerSet {	
	static int N; //재료 N개
	static Pair[] ingr;

	//신맛과 쓴맛의 차이 최솟값
	static int ret = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		ingr = new Pair[N];
		
		for(int i = 0; i < N; i++) {
			//신 쓴 
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ingr[i] = new Pair(s, b);		
		}
		
		//부분집합(멱집합)은 비트마스킹! -> 넣거나 말거나
		//안 될 경우, 방문체크 배열 만들고 (넣고 재귀, 빼고 재귀)를 반복한다.
		for(int i = 1; i < (1 << N); i++) {
			//현재 조합에 대하여
			
			//신맛S: 곱, 쓴맛B: 합
			int sourTotal = 1;
			int bitterTotal = 0;
			
			for(int j = 0; j < N; j++) {
				if((i & 1 << j) != 0) { //1(포함)
					sourTotal *= ingr[j].sour;
					bitterTotal += ingr[j].bitter;
				} 
			}
			
			
			//각 경우에 대한 처리(맛 차이의 최소 갱신)
			ret = Math.min(ret, Math.abs(sourTotal - bitterTotal));
		}
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();

	}
	
	static class Pair {
		int sour, bitter;
		Pair(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}
	
}
