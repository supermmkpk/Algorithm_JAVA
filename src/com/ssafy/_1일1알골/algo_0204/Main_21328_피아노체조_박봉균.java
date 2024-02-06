package com.ssafy._1일1알골.algo_0204;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ21328 : 피아노 체조
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72925785"> </a>
 * 
 * 아이디어: 부분배열(구간)을 다룬다 -> 누적합
 * 결과: 73488KB, 652ms
 */

/* <문제 요약>
 * N개의 악보, 1번 ~ N번까지의 번호로 부른다. 각 악보: 1 ~ 1e9(int OK) 난이도(높을수록 어려움). 
 * 피아노 체조: 1 ≤ x ≤ y ≤ N 을 만족하는 x, y를 골라 x번부터 y번까지를 순서대로 연주하는 것
 * 현재 악보가 다음 악보보다 어렵다면 실수를 한다. 즉, i번 난이도 > (i + 1)번 난이도 이면 실수. 
 * 실수하는 곡의 수는?
[입력]
1) 악보의 개수 N. (1 ≤ N ≤ 100,000)
2) 1번 ~ N번 난이도.
3) 질문의 개수 Q. (1 ≤ Q ≤ 100,000)
Q개 줄) 두 정수 x, y. (1 ≤ x ≤ y ≤ N)

[출력]
x번부터 y번까지 순서대로 연주할 때, 몇 개의 악보에서 실수하게 될지 0 이상의 정수로 출력.
 */
public class Main_21328_피아노체조_박봉균 {
	/** 악보의 개수 N */
	static int N;
	/** 누적 실수 배열 (인덱스: 1 ~ N)*/
	static int[] mistakeSum;
	/** 질문의 개수 Q */
	static int Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //악보의 개수 N 입력
		mistakeSum = new int[N + 1]; //mistakeSum[] 동적 할당
		
		//난이도 입력 -> 누적합 배열
		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken()); //이전 입력값
		int given = 0; //현재 입력값
		for(int i = 1; i < N; i++)  {
			given = Integer.parseInt(st.nextToken());
			if(prev > given) //현재 악보가 다음 악보보다 어렵다면
				mistakeSum[i] = mistakeSum[i - 1] + 1; //갱신
			else 
				mistakeSum [i] = mistakeSum[i-1]; //아니면 그대로
			
			prev = given; //prev 갱신
		}
			
		//질문의 개수 Q 입력
		Q = Integer.parseInt(br.readLine()); 
		
		//Q개의 질의에 대하여
		for(int t = 1; t <= Q; t++) { //1e5
			//구간 입력
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			//실수 횟수 출력(end에서 실수 안함)
			bw.write(String.valueOf(mistakeSum[end - 1] - mistakeSum[start - 1]) + '\n'); 
		}
		//데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();	
	}
}
