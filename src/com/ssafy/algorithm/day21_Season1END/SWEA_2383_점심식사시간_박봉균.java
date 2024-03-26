package com.ssafy.algorithm.day21_Season1END;
import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * 결과: 24252KB, 142 ms
 */

/* <SWEA 2383 : 점심 식사 시간>
 * N*N 방에 사람들이 앉아 있다.
 * 이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간이다.
 *  1. 계단 입구까지 이동 시간
        - 사람이 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간.
        - 이동 시간(분) = | y1 - y0 | + | x1 - x0 |
    2.  계단을 내려가는 시간
        - 계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
        - 계단 위에는 동시에 최대 3명.
        - 이미 3명: 대기.
        - 계단 길이 K에 대하여, 내려가는데 K 분이 걸린다.
 * 전체 이동 완료 시간 최솟값을 구해보자
[제약 사항]
- 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.
- 초기에 입력으로 주어지는 사람의 위치와 계단 입구의 위치는 서로 겹치지 않는다.
[입력]
1) 테케 수 T
각 테케] 1) 행렬 크기 N.(4 ≤ N ≤ 10)
    N개 줄) 방 정보 행렬. (1: 사람, 2~: 계단의 입구, 1 ≤ 사람 수 ≤ 10, 2 ≤ 계단 길이 ≤ 10)
[출력]
#테케번호 <공백> 전체 이동 완료 최소 시간
 */
public class SWEA_2383_점심식사시간_박봉균 {
	
	static int N, matrix[][], nPersons, stair0L, stair1L;

	static List<Pair> persons = new ArrayList<>(); //사람 좌표 리스트
	static List<Pair> stairs = new ArrayList<>(); //계단 좌표 리스트 
	static PriorityQueue<Integer> pq0 = new PriorityQueue<>(); //계단0 도달시간 PQ
	static PriorityQueue<Integer> pq1 = new PriorityQueue<>(); //계단1 도달시간 PQ
	static int ret = Integer.MAX_VALUE; //전체 이동시간의 최소
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		//T개의 테케에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			persons.clear();
			stairs.clear();
			nPersons = 0;
			ret = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine()); //행렬 크기
			matrix = new int[N][N];
			
			//방 정보 행렬 입력
			int nStairs = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					
					//1: 사람, 2~: 계단의 입구
					if(matrix[i][j] == 1) {
						persons.add(new Pair(i, j));
						++nPersons;
					}
					else if(matrix[i][j] > 1) {
						stairs.add(new Pair(i, j));
						if(nStairs == 0)
							stair0L = matrix[i][j]; //계단0 길이
						else 
							stair1L = matrix[i][j]; //계단1 길이
						++nStairs; 
					}
				}
			}
			
			//1. 계단은 반드시 2개이므로 누가 어느 계단으로 갈지 멱집합 구하자!!
			for(int i = 0; i < (1<<nPersons); i++) {
				//해당 조합에 대하여...
				//초기화
				pq0.clear();
				pq1.clear();
				
				
				//비트를 확인하고, 각 사람의 도달 시간을 계단별로 오름차순으로 저장합니다.
				for(int j = 0; j < nPersons; j++) {
					//비트 0: 계단 0
					if((i & 1 << j) == 0) {
						int ETA = getDist(persons.get(j), stairs.get(0)) + 1;
						pq0.add(ETA);
						
					}
					//비트 1: 게단 1
					else {
						int ETA = getDist(persons.get(j), stairs.get(1)) + 1;
						pq1.add(ETA);
					}
				}
								
				//2. 계단 별로 독립 시행, 전체 완료 시간 구하기
				int time = Math.max(go(pq0, stair0L), go(pq1, stair1L));
				
				//전체 완료 시간 중 최소
				ret = Math.min(ret, time);
			}
			
			//전체 완료 시간의 최솟값 출력
			bw.write("#" + String.valueOf(t) + " ");
			bw.write(String.valueOf(ret) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int go(PriorityQueue<Integer> pq, int len) {
		//한번에 최대 3명.
		//도달 시간 기준으로 체크
		Queue<Integer> q = new ArrayDeque<>(); //계단 내려가는 중(종료 시간 저장)
		
		int start = -1;
		int end = -1;
		while(!pq.isEmpty()) {
			start = pq.peek(); //시작시간(계단 도달시간)
			end = pq.remove() + len; //이동 종료 시간
			
			//큐의 시간(종료시간) <= 체크 시간 이라면 이동 완료이므로 큐에서 제거.
			while(!q.isEmpty()) {
				int prevEnd = q.peek();
				
				if(prevEnd <= start)
					q.remove();
				else
					break;
			}
			
			//내려 가는 사람 3명이면 대기!
			if(q.size() == 3) {
				//지금 체크하는 시간 = start = 계단 도달 시간
				//큐의 front의 종료시간 = 현재 사람의 지연된 시작시간
				int prevEnd = q.remove();
				start = prevEnd;
				end = prevEnd + len;
				q.add(end);
			}
			else {
				//이외의 경우라면 그냥 계단 내려가기.
				q.add(end);
			}
		}
		
		//큐에 남아있다면 pop, 종료시간 갱신
		while(!q.isEmpty()) 
			end = q.remove();
		
		return end; //해당 계단의 이동 완료 시간을 반환
	}
	
	/** 계단까지 이동 시간(분) = | y1 - y0 | + | x1 - x0 | 으로 정의한다. */
	static int getDist(Pair p0, Pair p1) {
		return Math.abs(p1.first - p0.first) + Math.abs(p1.second - p0.second);
		
	}
	
	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first, second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
