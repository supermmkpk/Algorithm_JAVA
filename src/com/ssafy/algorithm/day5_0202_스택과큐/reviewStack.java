package com.ssafy.algorithm.day5_0202_스택과큐;
import java.io.*;
import java.util.*;

/**
 * BJ_2493[G5] : 탑
 */
public class reviewStack {
	/** 탑 개수 N */
	static int N;
	/** {탑인덱스, 높이} 스택 */
	static Stack<Pair> stk = new Stack<>();
	/** 결과 배열 */
	static int[] ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //탑 개수 N 입력
		ret = new int[N]; //결과 배열 ret[] 동적할당

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int given = Integer.parseInt(st.nextToken()); //현재 높이 입력
			
			//현재 입력과 높이 비교(peek), 크거나 같으면 그 탑의 번호(인덱스+1)가 정답, 작으면 pop
			while(!stk.empty()) {
				if(given <= stk.peek().second) {
					ret[i] = stk.peek().first + 1; //탑 번호 결과배열에 저장(인덱스+1)
					break;
				}
				else {
					stk.pop(); //높이가 더 낮으면 pop
				}
			}
			
			if(stk.empty()) //송신 가능 탑이 없다면 0
				ret[i] = 0;
			
			stk.push(new Pair(i, given)); //입력 push
		}
		
		//결과 배열 출력
		for(int i = 0; i < N; i++) 
			bw.write(String.valueOf(ret[i]) + " ");
		bw.write('\n');
		
		//남은 데이터 출력 및  stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}



