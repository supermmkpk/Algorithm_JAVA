package com.ssafy.algorithm.day10_Greedy;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ10973 [S3] : 이전 순열
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 결과: 14740KB, 124ms
 */
public class Main_10973_이전순열_박봉균 {
	static int N;
	static int[] given;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		given = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			given[i] = Integer.parseInt(st.nextToken());
		
		if(!prevPermutation(given))
			bw.write("-1\n");
		else {
			for(int i : given)
				bw.write(String.valueOf(i) + " ");
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean prevPermutation(int[] arr) {
		//step1: 뒤에서부터 낮은 곳 찾기(교환 위치(i-1))
		int i = N - 1;
		while(i > 0 && arr[i-1] <= arr[i])
			i--;
		if(i == 0)
			return false;
		
		//step 2: 교환 값 찾기(작은 값 중 최대)
		int j = i - 1;
		while(j + 1 <= N - 1 && arr[i - 1] >= arr[j + 1])
			j++;
		
		//step 3 : 교환
		swap(arr, i - 1, j);
		
		//step 4: 0~N 오름차순 정렬
		int k = N - 1;
		while(i < k)
			swap(arr, i++, k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}