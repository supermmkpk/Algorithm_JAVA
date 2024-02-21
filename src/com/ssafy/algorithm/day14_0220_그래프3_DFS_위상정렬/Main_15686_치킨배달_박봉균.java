package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ15686 [G5] : 치킨 배달
 * </pre>
 * @author 박봉균
 * 
 * 아이디어: 유지할 치킨집 조합 구해서 도시 치킨거리 계산. 치킨집, 집 좌표 저장하는 리스트 따로 관리
 * 결과: 	17448KB, 212ms
 */

/* <요약>
 * N×N 도시가 있다. 
 * 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. (r, c) : r행 c열. (1 <= r,c <= N)
 * 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리이다. (집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다). 
 * 도시의 치킨 거리: 모든 집의 치킨 거리의 합
 * 임의의 두 칸 (r1, c1)과 (r2, c2) 사이 거리 = |r1-r2| + |c1-c2|
 * 치킨집 중 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다.
 * 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될까?
[입력]
1) N, M. (2 ≤ N ≤ 50, 1 ≤ M ≤ 13)
N개 줄) 도시 정보 행렬 (0: 빈 칸, 1: 집, 2: 치킨집, 1<= 집의 개수 <= 2N, M <= 치킨집 개수<= 13
[출력]
1) 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력.
 */
public class Main_15686_치킨배달_박봉균 {
	/** 행,열 크기 N, 치킨집 최소 개수 M */
	static int N, M;
	/** 도시 정보 행렬 */
	static int[][] matrix;
	/** 치킨 집 좌표 저장 리스트 */
	static ArrayList<Pair> chickens = new ArrayList<>();
	/** 집 좌표 저장 리스트 */
	static ArrayList<Pair> homes = new ArrayList<>();
	/** 치킨 집 중 오픈할 가게의 인덱스를 저장하는 리스트 */
	static LinkedList<Integer> opens = new LinkedList<>();
	/** 결과 변수: 도시 치킨 거리의 최솟값 */
	static int ret = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행,열 크기 N, 치킨집의 최소 개수 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//도시 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)  {
				int given = Integer.parseInt(st.nextToken());
				
				//1이면 집
				if(given == 1)
					homes.add(new Pair(i, j));
				//2이면 치킨가게
				if(given == 2)
					chickens.add(new Pair(i, j));
			}
		}
		
		//열린 치킨 가게 인덱스의 조합을 구하고, 도시 치킨 거리의 최소를 구합니다.
		combi(0, 0);
	
		//최소 도시 치킨 거리 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 
	 * 열린 가게 인덱스의 조합을 구하는 함수
	 */
	static void combi(int start, int cnt) {
		if(cnt == M) {
			ret = Math.min(ret, getCityDist()); //도시 치킨 거리가 최소라면 갱신
			return;
		}
		
		//조합 구하기
		for(int i = start; i < chickens.size(); i++) {
			opens.add(i);
			combi(i + 1, cnt + 1);
			opens.removeLast();
		}
	}
	
	/**
	 * 열린 치킨 가게와 집 사이의 최소거리를 구하는 함수
	 */
	static int getCityDist() {
		int cityDist = 0;

		//모든 집에 대하여
		for(Pair home : homes) {
			//열린 가게와 거리 중 최소를 구합니다.
			int dist = Integer.MAX_VALUE;
			for(int open : opens) {
				int y = chickens.get(open).first; //열린 치킨 가게의 행
				int x = chickens.get(open).second; //열린 치킨 가게의 열
				dist = Math.min(dist, Math.abs(home.first - y) + Math.abs(home.second - x));
			}
			cityDist += dist; //도시 치킨 거리에 더해주기
		} 
		return cityDist;
	}

	/** 
	 * 좌표 저장 위한 Pair 클래스
	 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
