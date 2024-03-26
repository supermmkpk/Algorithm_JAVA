package com.ssafy._1일1알골.algo_0311;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 아이디어: 시작시준 오름차순, 종료기준 내림차순 정렬 후 그리디
 * 결과: 49620KB, 484ms
 */

/* < BJ2457_G3 : 공주님의 정원>
 * 총 N개의 꽃이 있는 데, 꽃은 모두 같은 해에 피어서 같은 해에 진다. 
 * 하나의 꽃은 피는 날과 지는 날이 정해져 있다. 
 * 4, 6, 9, 11월: 30일 / 1, 3, 5, 7, 8, 10, 12: 31일 / 2: 28일
 * N개의 꽃들 중에서 다음의 두 조건을 만족하는 꽃들을 선택하고 싶다.
 *  1. 공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다
 *  2. 정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다. 
 * 3월 1일 ~ 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때, 선택한 꽃들의 최소 개수를 구하시오. 
[입력]
1) 꽃들의 총 개수 N (1 ≤ N ≤ 1e5)
N개 줄)각 꽃이 피는 날짜, 지는 날짜. (날짜는 월, 일로 표현된다) 
[출력]
꽃들의 최소 개수.(두 조건을 만족하는 꽃들을 선택할 수 없다면 0)
 */
public class Main_2457_공주님의정원_박봉균 {
	static final int FROM = getDay(3, 1);
	static final int TO = getDay(12, 1);
	
	static int N;
	static PriorityQueue<Pair> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //N개 꽃
		
		//N개 꽃 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int fromM = Integer.parseInt(st.nextToken());
			int fromD = Integer.parseInt(st.nextToken());
			int toM = Integer.parseInt(st.nextToken());
			int toD = Integer.parseInt(st.nextToken());
			
			int from = getDay(fromM, fromD);
			int to = getDay(toM, toD);
			pq.add(new Pair(from, to));
		}
		
		
		int max = 0; //현재 구간에서 최대 종료일
		int prevEnd = FROM; //이전 구간의 종료일
		int cnt = 0; //꽃 개수
		while(prevEnd < TO) {
			boolean flag = false; //가능 여부
			
			while(!pq.isEmpty()) {
				if(pq.peek().from > prevEnd) 	//연결 되지 않는 경우(불가능한 경우)
					break;
				
				if(max < pq.peek().to) { //가능한 구간 중 최대 종료일
					flag = true;
					max = pq.peek().to;
				}
				pq.remove();
			}
			
			if(flag) {
				prevEnd = max;
				cnt++;
			}
			else 
				break;
		}
		
		if(max < TO) //조건 불만족 : 0
			bw.write("0\n");
		else
			bw.write(String.valueOf(cnt) + '\n'); //꽃 최소개수 출력
		
		bw.flush();
		bw.close();
		br.close();
	}

	//일자로 변환하는 함수
	static int getDay(int m, int d) {
		switch(m) {
		case 1:
			return d;
		case 2:
			return 31 + d;
		case 3:
			return 31 + 28 + d;
		case 4:
			return 31*2 + 28 + d;
		case 5:
			return 31*2 + 30 + 28 + d;
		case 6:
			return 31*3 + 30 + 28 + d;
		case 7:
			return 31*3 + 30*2 + 28 + d;
		case 8:
			return 31*4 + 30*2 + 28 + d;
		case 9:
			return 31*5 + 30*2 + 28 + d;
		case 10:
			return 31*5 + 30*3 + 28 + d;
		case 11:
			return 31*6 + 30*3 + 28 + d;
		case 12:
			return 31*6 + 30*4 + 28 + d;
		default:
			return 0;
		}
	}
	
	//시작, 종료 저장 위한 Pair 클래스
	static class Pair implements Comparable<Pair> {
		int from, to;
		Pair(int from, int to) {
			this.from = from;
			this.to = to;
		}
		//종료일 기준 내림차순, 시작일 기준 오름차순
		public int compareTo(Pair o) {
			if(this.from == o.from) 
				return Integer.compare(o.to, this.to);
			else 
				return Integer.compare(this.from, o.from);
		}
	}
}

