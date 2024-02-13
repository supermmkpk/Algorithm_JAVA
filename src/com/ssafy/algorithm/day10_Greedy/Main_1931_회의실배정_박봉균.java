package com.ssafy.algorithm.day10_Greedy;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ1931 [S1] : 회의실 배정
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 종료시간 기준 오름차순
 * 결과: 42604KB, 472ms
 */

/* <문제 요약>
 * 한 개의 회의실을 N개 회의에 사용하려 한다. 
 * 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. (회의의 시작시간과 끝나는 시간이 같을 수도 있다)
[입력]
1) 회의의 수 N(1 ≤ N ≤ 1e5)
N개 줄) 회의의 시작시간, 종료시간. (0 <= 시간 <= 2^31 - 1)
[출력]
회의의 최대 개수.
 */
public class Main_1931_회의실배정_박봉균 {
	/** 회의 개수 N */
	static int N;
	/** 회의 정보 저장 PQ */
	static PriorityQueue<Meeting> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//회의 개수 N 입력
		N = Integer.parseInt(br.readLine());
		
		//회의 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new Meeting(start, end));
		}
		
		//종료 시간 기준 PQ에서 이전 종료 이후의 front 선택 
		int cnt = 1;
		int prev = pq.remove().end; //이전 종료시간
		while(!pq.isEmpty()) {
			if(prev > pq.peek().start) //이전 종료 시간 이전: remove
				pq.remove();
			else { //이전 종료 시간 이후: 카운트 증가
				prev = pq.remove().end;
				cnt++;
			}
		}
		
		//가능한 회의 최대 개수 출력
		bw.write(String.valueOf(cnt) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Meeting implements Comparable<Meeting> {
		int start; 
		int end;
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		/** 정렬 규칙: 종료시간 기준 오름차순, 같을 경우 시작시간 기준 오름차순 */
		@Override
		public int compareTo(Meeting m) {
			if(this.end == m.end)
				return this.start - m.start;
			else
				return this.end - m.end;
		}
	}
}
