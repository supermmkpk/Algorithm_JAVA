package com.ssafy.algorithm.day10_Greedy;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ10972 [S3] : 다음 순열
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 결과: 14804KB, 124ms
 */

/* <문제 요약>
 * 1 ~ N 수로 이루어진 순열이 있다.
 * 사전순으로 다음에 오는 순열을 구하시오.
[입력]
1) N(1 ≤ N ≤ 1e4)
2) 순열.
[출력]
주어진 순열의 다음에 오는 순열을 출력. (사전 순 마지막인 경우: -1).
 */
public class Main_10972_다음순열_박봉균 {
	/** 숫자 개수(1~N) */
	static int N;
	/** 주어진 순열 */
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

		//step 0: 정렬
		
		if(!nextPermutation(given))
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
	
	static boolean nextPermutation(int[] arr) {
		//step 1: 꼭대기 찾기(교환 지점은 꼭대기 이전(i-1))
		int i = N - 1;
		while(i > 0 && arr[i - 1] >= arr[i])
			i--;
		if(i == 0) //현 순열이 제일 크므로(내림차순) 마지막 순열
			return false;
		
		//step 2: 교환 값 찾기
		int j = N - 1;
		while(arr[i - 1] >= arr[j])
			j--;
		
		//step 3: 교환하기
		swap(arr, i - 1, j);
		
		//step 4: i~끝 오름차순 정렬하기
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