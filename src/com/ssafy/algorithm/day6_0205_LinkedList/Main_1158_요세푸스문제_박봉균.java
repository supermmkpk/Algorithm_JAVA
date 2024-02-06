package com.ssafy.algorithm.day6_0205_LinkedList;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1158 [S4] : 요세푸스 문제
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: LinkedList 사용, 인덱스 벗어나면 size 빼주면서 진행(원)
 * 결과: 13000KB, 132ms
 */

/* <문제요약>
 * 1~N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
 *  1. 순서대로 K번째 사람을 제거한다. 
 *  2. 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 
 * 모두 제거될 때까지 반복한다. 
 * 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 
 *   Ex) (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
 * N과 K가 주어지면 (N, K)-요세푸스 순열을 구하라.
[입력]
1) N, K (1 ≤ K ≤ N ≤ 5,000)

[출력]
요세푸스 순열 출력.
 */
public class Main_1158_요세푸스문제_박봉균 {
	/** 사람 수 N */
	static int N;
	/** K번째 제거 */
	static int K;
	/** 연결리스트 */
	static LinkedList<Integer> list = new LinkedList<>();
	/** 결과 배열 */
	static int[] ret;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수 N 입력
		K = Integer.parseInt(st.nextToken()); // 순서(K번째) 입력
		ret = new int[N]; //결과 배열 ret[] 동적 할당
		
		//1 ~ N 숫자 넣기
		for(int i = 1; i <= N; i++)
			list.addLast(i);
		int prev = 0; //이전 인덱스
		int i = 0; //결과 배열의 인덱스
		
		//모두 제거될 때까지 반복합니다. 
		while(!list.isEmpty()) { 
			int idx = prev + K - 1; //이전 인덱스에서 K번째 인덱스를 구합니다.
			
			//범위를 벗어나면 size를 빼주어 범위 내로 맞춰줍니다.(원형)
			while(true) {
				if(idx >= list.size())
					idx -= list.size();
				else 
					break;
			}
			
			//해당 인덱스의 원소를 결과 배열에 넣고 제거합니다.
			ret[i++] = list.get(idx);
			list.remove(idx);
			
			prev = idx; //이전 인덱스 prev 갱신
		}
		
		//출력 조건에 따라 출력합니다.
		bw.write("<");
		i = 0;
		for(; i <  N - 1; i++) 
			bw.write(String.valueOf(ret[i]) + ", ");
		bw.write(String.valueOf(ret[i]) + ">\n");
		
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}
