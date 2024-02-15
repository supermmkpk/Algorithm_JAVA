package com.ssafy.algorithm.day12_0215_그래프1;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * SWEA 1247 [D5] : 최적 경로
 * </pre>
 * @author 박봉균
 * 아이디어: 회사 -> 10개 고객 위치의 순열 중 최소 이동 거리 -> 집
 * 결과: 21060KB, 1694ms
 */

/* <요약>
 * 회사와 집의 위치, 각 고객 위치는 (y, x)로 주어진다. (0 ≤ y ≤ 100, 0 ≤ x ≤ 100)
 * (y1, x1) , (y2, x2) 사이 거리 = |y1-y2| + |x1-x2|
 * 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 최단 경로의 이동거리를 찾자.
[입력]
1) 테케 수 T
각 테 케]1) 고객의 수 N. (2 ≤ N ≤ 10) 이다.이 주어진다. 
    2: N+2개 좌표) 회사 좌표, 집 좌표, N명 고객 좌표. (y <공백> x,  0 ≤ y,x ≤ 100)
[출력]
#테케번호 <공백> 최단 경로의 이동거리
 */
public class D5_1247_최적경로_박봉균 {
	public static final int INF = (int) 1e9;
	
	/** 고객 수 */
	static int N;
	/** 주어지는 고객 좌표 */
	static Pair[] givenLoc;
	/** 시작 좌표(회사), 끝 좌표(집) */
	static Pair start, end;
	/** 순열 고객 좌표 */
	static Pair[] loc;
	/** 결과 변수 : 최소 이동거리 */
	static int ret = INF;
	
	/**
	 * 갈 순서 정하고, 최소 이동 거리 구하는 함수
	 * @param cnt : 순열 요소 수
	 * @param visited : 방문체크(비트마스킹)
	 */
	static void permu(int cnt, int visited) {
		//순열 모두 구했다면 이동거리 구하고 최소 갱신
		 if(cnt == N) { 
			 //시작 -> 첫고객
			 int sum = Math.abs(start.first - loc[0].first) + Math.abs(start.second - loc[0].second);
			 
			 //첫고객 -> ... -> 마지막 고객
			 for(int i = 1; i < N; i++) 
				 sum += Math.abs(loc[i].first - loc[i-1].first) + Math.abs(loc[i].second - loc[i-1].second);
			 
			 //마지막 고객 -> 집
			 sum += Math.abs(end.first - loc[N-1].first) + Math.abs(end.second - loc[N-1].second);
			 //최소 갱신
			 ret = Math.min(ret, sum);
			 return;
		 }
		
		 //순열 구하기
		for(int i = 0; i < N; i++) {
			if((visited & (1 << i)) != 0)
				continue;
			loc[cnt] = givenLoc[i]; 
			permu(cnt + 1, visited | (1 << i));
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테케 개수 T 입력
		
		//T개의 테케에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			ret = INF;

			//고객 수 N 입력, 동적할당
			N = Integer.parseInt(br.readLine());
			givenLoc = new Pair[N];
			loc = new Pair[N];
			
			/* 좌표 정보 입력 */
			st = new StringTokenizer(br.readLine());
			//시작: 회사
			start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			//끝: 집
			end = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			//givenLoc: 고객
			for(int i = 0; i < N; i++) {
				int givenY = Integer.parseInt(st.nextToken());
				int givenX = Integer.parseInt(st.nextToken());
				givenLoc[i] = new Pair(givenY, givenX);
			}
			
			//갈 순서 정하고, 최소 이동 거리 구하기
			permu(0, 0);
			
			//테케 별 최소 이동 거리 출력
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');
		}
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
