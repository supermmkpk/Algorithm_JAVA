package com.ssafy.algorithm.day10_Greedy;
import java.util.*;

public class MeetingRoomTest {
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting m) {
			if(this.end == m.end)
				return this.start - m.start;
			else	
				return this.end - m.end;
		}
		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Meeting[] meetings = new Meeting[N];
		
		for (int i = 0; i < N; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(meetings);
		
		//회의 선택을 최대로 하고 선택된 회의들의 내용을 출력
		List<Meeting> list = new ArrayList<>();
		list.add(meetings[0]); //첫 회의 선택
		for(int j = 1; j < N; j++) { //j: 고려하는 회의
			if(list.get(list.size() - 1).end <= meetings[j].start) {
				list.add(meetings[j]);
			}
		}
		System.out.println(list.size());
		for (Meeting meeting : list) {
			System.out.println(meeting);
		}
	}
}

//10 1 4 3 5 1 6 5 7 3 8 5 9 6 10 8 11 2 13 12 14