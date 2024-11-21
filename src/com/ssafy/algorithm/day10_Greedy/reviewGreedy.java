package com.ssafy.algorithm.day10_Greedy;
import java.io.*;
import java.util.*;

/**
 * BJ_1931[S1] : 회의실 배정
 * 완탐 -> 그리디 -> DP
 */
public class reviewGreedy {
	static int N;
	static int ret = 0;
	static PriorityQueue<Meeting> pq = new PriorityQueue<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new Meeting(start, end));
		}
		
		Meeting prev = new Meeting();
		if(!pq.isEmpty()) {
			//첫 회의
			++ret;
			prev = pq.remove(); 
		}
		
		while(!pq.isEmpty()) {
			if(prev.end <= pq.peek().start) {
				//가능한 경우
				++ret;
				prev = pq.remove();
			} else {
				//불가능한 경우
				pq.remove();
			}
		}
		
		System.out.println(ret);
		br.close();
	}
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		Meeting() {}
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting m) {
			if(m.end == this.end) {
				// 동일하면 시작 기준 정렬
				return Integer.compare(this.start, m.start); 
			} 
			// 끝 시간 기준 오름차순 정렬
			return Integer.compare(this.end, m.end);
		}
		
	}
	
}
