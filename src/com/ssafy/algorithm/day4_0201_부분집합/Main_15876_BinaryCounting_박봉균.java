package com.ssafy.algorithm.day4_0201_부분집합;
import java.util.*;

/**
 * <pre>
 * BJ15876 [S5] : BinaryCounting
 * @author 박봉균
 * @since JDK1.8
 * 
 * 결과: 12876KB, 108ms
 */
/*
 * <문제 요약>
 * 돌아가면서 0부터 1씩 증가하면서 이진수의 한자리씩 말하기.
[입력]
1) 참가자 수 n <공백> 진수의 차례 k. (1 ≤ k ≤ n ≤ 100)

[출력]
말해야 하는 숫자 다섯 개를 띄어쓰기로 구분하여 한 줄에 출력한다.
 */
public class Main_15876_BinaryCounting_박봉균 {
	/** 결과 배열 */
	static int[] ret = new int[10];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //인원수 N
		int K = sc.nextInt(); //차례 K
		int last = N * 4 + K; //마지막 차례
		
		int num = 0; //현재 숫자
		int cnt = 1; //누적 인원 카운트
		int idx = 0; //결과 배열에서의 인덱스
		
		while(true) {
			String bin = Integer.toString(num, 2); //숫자를 이진수로 바꾸기(길이 구하기)
			for(int i = bin.length() - 1; i >= 0; i--) { //현재 숫자의 이진수의 길이에 대해 한명씩 말합니다.
				if((cnt - K) % N == 0) //진수의 자례라면
					ret[idx++] = (num >>> i) & 1; //i만큼 오른쪽 시프트(>>>) 후 해당 비트(&1)를 결과배열에 저장
				cnt++; //누적 카운트 증가
			}
			if(cnt > last) //마지막 차례였으면 종료
				break;
			num++; //현재 숫자 증가, loop
		}
		
		//출력
		for(int i = 0; i < 5; i++)
			System.out.print(ret[i] + " ");
		System.out.println();
	}
}