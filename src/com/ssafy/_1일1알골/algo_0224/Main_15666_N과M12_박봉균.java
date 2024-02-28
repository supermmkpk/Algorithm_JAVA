package com.ssafy._1일1알골.algo_0224;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 결과: 15268KB, 104ms
 */

/*
 * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하시오.
 * N개의 자연수 중 M개를 고른 수열(같은 수 중복 OK, 비내림차순)
 * A1 ≤ A2 ≤ ... ≤ Ak-1 ≤ Ak
[입력]
1) N, M. (1 ≤ M ≤ N ≤ 8)
N개 줄) 수. (1<= 수 <= 1e4)
[출력]
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
중복되는 수열을 여러 번X, 수열은 사전 순으로 증가하는 순서
 */
public class Main_15666_N과M12_박봉균 {
	
	static int N, M, nums[];
	static HashSet<Integer> set = new HashSet<>();
	static ArrayList<Integer> input;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M]; //중복 조합 결과
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			set.add(Integer.parseInt(st.nextToken())); //중복 제거하여 받기
		
		input = new ArrayList<>(set); //리스트로 변환
		Collections.sort(input); //사전식 출력 위한 정렬
		
		//중복 조합 구하기
		combi(0, 0);
		
		bw.write(sb.toString());
		bw.flush();
        bw.close();
        br.close();
	}
	
	//중복 조합
	static void combi(int cnt, int start) {
		if(cnt == M) {
			//결과 출력
			for(int i : nums) 
				sb.append(i + " ");
			sb.append('\n');
			return;
		}
		
		//중복 조합 구하기
		for(int i = start; i < input.size(); i++) {
			nums[cnt] = input.get(i);
			combi(cnt + 1, i); //start 나부터
		}
	}
}
