package com.ssafy.algorithm.day10_Greedy;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ11000 [G5] : 강의실 배정
 * </pre>
 * @author 박봉균
 * 아이디어: 강의를 시간 순 저장(PQ) -> 종료시간 기준 PQ에 강의를 하나씩 넣으면서 가능한지 체크 
 *     -> 가능하면 front 제거, 해당 강의 추가(해당 강의실에 대해서는 이 강의 차례가 왔을 때 체크하면 됨)
 *     -> 가능하지 않으면 front 제거하지 않으며 진행
 *     -> PQ의 크기가 최소 강의실 수
 * 결과: 75024KB, 688ms
*/

/* <문제 요약>
 * Si(시작) ~ Ti(종료) N개 수업 있다. 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
 * 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
[입력]
1) N. (1 ≤ N ≤ 2e5)
N개 줄) Si, Ti. (0 ≤ Si < Ti ≤ 10^9)

[출력]
모든 수업이 가능한 최소 강의실 수.
 */
public class Main_11000_강의실배정_박봉균 {
	/** 수업 수 N */
	static int N;
	/** 강의 저장 PQ */
	static PriorityQueue<Lecture> lecture = new PriorityQueue<>();	
	/** 강의 종료 시간 기준 PQ */
	static PriorityQueue<Integer> room = new PriorityQueue<>();	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//수업 수 N 입력
		N = Integer.parseInt(br.readLine());
		
		//강의 정보 저장(오름차순)
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lecture.add(new Lecture(start, end));
		}
		
		room.add(lecture.remove().end); //최초 강의 추가
		//강의 모두 배정될 때까지 반복
		while(!lecture.isEmpty()) {
			if(room.peek() <= lecture.peek().start) //이 강의실에서 아다리가 맞으면 remove
				room.remove();
			
			room.add(lecture.remove().end); //강의 추가(remove한 강의의 강의실은 나중에 여기서 체크하면 됨)
		}
		
		//필요한 강의실 최소 개수 출력
		bw.write(String.valueOf(room.size()) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Lecture implements Comparable<Lecture> {
		int start;
		int end;
		Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Lecture l) {
			if(this.start == l.start)
				return this.end - l.end;
			else 
				return this.start - l.start;
		}
	}
}
/*
5
1 3
2 4
3 5
4 6
4 7
*/
