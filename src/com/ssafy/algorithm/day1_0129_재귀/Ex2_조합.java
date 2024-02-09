package com.ssafy.algorithm.day1_0129_재귀;

import java.util.LinkedList;

public class Ex2_조합 {
	static int N;
	static int R;
	
	//C(n,r) 값 - 재귀
	static int combi(int n, int r) {
		if(r == 1) {
			return n;
		}
		if(n == r) {
			return 1;
		}
		return combi(n-1, r-1) + combi(n-1, r);
	}
	
	//C(N,R) - 백트래킹
	static void combi2(int start, LinkedList<Integer> list) {
		if(list.size() == R) {
			return;
		}
		for(int i = start + 1; i < N; i++) {
			list.addLast(i);
			combi2(i, list);
			list.removeLast();
		}
	}

	public static void main(String[] args) {
		
	}
}
